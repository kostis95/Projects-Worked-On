package hva.nl.bloqs.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import hva.nl.bloqs.BluetoothDeviceData;
import hva.nl.bloqs.R;
import hva.nl.bloqs.BluetoothDeviceRVAdapter;
import hva.nl.bloqs.ble.BleDevicesScanner;
import hva.nl.bloqs.ble.BleManager;
import hva.nl.bloqs.ble.BleUtils;

/**
 * Created by Frank on 1-6-2016.
 */
public class PeripheralActivity extends AppCompatActivity implements BleManager.BleManagerListener, BleUtils.ResetBluetoothAdapterListener  {

    // Constants
    private final static String TAG = PeripheralActivity.class.getSimpleName();
    private final static long kMinDelayToUpdateUI = 200;    // in milliseconds
    private static final String kGenericAttributeService = "00001801-0000-1000-8000-00805F9B34FB";
    private static final String kServiceChangedCharacteristic = "00002A05-0000-1000-8000-00805F9B34FB";

    // Activity request codes (used for onActivityResult)
    private static final int kActivityRequestCode_EnableBluetooth = 1;
    private static final int kActivityRequestCode_Settings = 2;
    private static final int kActivityRequestCode_ConnectedActivity = 3;

    // UI
    private Button mScanButton;
    private TextView mNoDevicesTextView;
    private ScrollView mDevicesScrollView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AlertDialog mConnectingDialog;
    private RecyclerView mRecyclerView;
    private BluetoothDeviceRVAdapter mRecyclerViewAdapter;

    private long mLastUpdateMillis;

    // Data
    private BleManager mBleManager;
    private boolean mIsScanPaused = true;
    private BleDevicesScanner mScanner;

    private ArrayList<BluetoothDeviceData> mScannedDevices;
    private BluetoothDeviceData mSelectedDeviceData;
    private String mLatestCheckedDeviceAddress;

    private DataFragment mRetainedDataFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peripheral);

        requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION }, 5);

        // Init variables
        mBleManager = BleManager.getInstance(this);
        restoreRetainedDataFragment();


        // UI
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerViewAdapter = new BluetoothDeviceRVAdapter(mScannedDevices);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        mScanButton = (Button)findViewById(R.id.scanButton);
        mNoDevicesTextView = (TextView)findViewById(R.id.noDevicesTextView);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mScannedDevices.clear();
                startScan(null, null);

                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        // Setup when activity is created for the first time
        if (savedInstanceState == null) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean autoResetBluetoothOnStart = sharedPreferences.getBoolean("pref_resetble", false);


            // Check if bluetooth adapter is available
            final boolean wasBluetoothEnabled = manageBluetoothAvailability();
            final boolean areLocationServicesReadyForScanning = manageLocationServiceAvailabilityForScanning();

            // Reset bluetooth
            if (autoResetBluetoothOnStart && wasBluetoothEnabled && areLocationServicesReadyForScanning) {
                BleUtils.resetBluetoothAdapter(this, this);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mBleManager.setBleListener(this);

        // Autostart scan
        if (BleUtils.getBleStatus(this) == BleUtils.STATUS_BLE_ENABLED) {
            // If was connected, disconnect
            mBleManager.disconnect();

            // Force restart scanning
            if (mScannedDevices != null)   // Fixed a weird bug when resuming the app (this was null on very rare occasions even if it should not be)
                mScannedDevices.clear();

            startScan(null, null);
        }

        // Update UI
        updateUI();
    }

    @Override
    public void onPause() {
        if (mScanner != null && mScanner.isScanning()) {
            mIsScanPaused = true;
            stopScanning();
        }

        super.onPause();
    }

    @Override
    public void onStop() {
        if (mConnectingDialog != null) {
            mConnectingDialog.cancel();
            mConnectingDialog = null;
        }

        super.onStop();
    }

    @Override
    public void onDestroy() {

        BleUtils.cancelBluetoothAdapterReset();

        saveRetainedDataFragment();

        if (mConnectingDialog != null)
            mConnectingDialog.cancel();

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == kActivityRequestCode_ConnectedActivity) {
            if (resultCode < 0) {
                Toast.makeText(this, R.string.scan_unexpecteddisconnect, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == kActivityRequestCode_EnableBluetooth) {
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth was enabled, resume scanning
                resumeScanning();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog = builder.setMessage(R.string.dialog_error_no_bluetooth)
                        .setPositiveButton(R.string.dialog_ok, null)
                        .show();

            }
        }
    }

    private boolean manageBluetoothAvailability() {
        boolean isEnabled = true;

        int errorMessageId = 0;
        final int bleStatus = BleUtils.getBleStatus(getBaseContext());
        switch (bleStatus) {
            case BleUtils.STATUS_BLE_NOT_AVAILABLE:
                errorMessageId = R.string.dialog_error_no_ble;
                isEnabled = false;
                break;
            case BleUtils.STATUS_BLUETOOTH_NOT_AVAILABLE:
                errorMessageId = R.string.dialog_error_no_bluetooth;
                isEnabled = false;
                break;
            case BleUtils.STATUS_BLUETOOTH_DISABLED:
                isEnabled = false;

                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, kActivityRequestCode_EnableBluetooth);
                break;
        }

        if (errorMessageId > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.setMessage(errorMessageId)
                    .setPositiveButton(R.string.dialog_ok, null)
                    .show();
        }

        return isEnabled;
    }

    private boolean manageLocationServiceAvailabilityForScanning() {
        boolean areLocationServiceReady = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int locationMode = Settings.Secure.LOCATION_MODE_OFF;
            try {
                locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            areLocationServiceReady = locationMode != Settings.Secure.LOCATION_MODE_OFF;

            if (!areLocationServiceReady) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog = builder.setMessage(R.string.dialog_error_nolocationservices_requiredforscan_marshmallow)
                        .setPositiveButton(R.string.dialog_ok, null)
                        .show();
            }
        }

        return areLocationServiceReady;
    }

    private void connect(BluetoothDevice device) {
        boolean isConnecting = mBleManager.connect(this, device.getAddress());
        if (isConnecting) {
            showConnectionStatus(true);
        }
    }

    private void showConnectionStatus(boolean enable) {
        showStatusDialog(enable, R.string.scan_connecting);
    }

    private void showGettingUpdateInfoState() {
        showConnectionStatus(false);
        showStatusDialog(true, R.string.scan_gettingupdateinfo);
    }

    private void showStatusDialog(boolean show, int stringId) {
        if (show) {

            // Remove if a previous dialog was open (maybe because was clicked 2 times really quick)
            if (mConnectingDialog != null) {
                mConnectingDialog.cancel();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(stringId);

            // Show dialog
            mConnectingDialog = builder.create();
            mConnectingDialog.setCanceledOnTouchOutside(false);

            mConnectingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mBleManager.disconnect();
                        mConnectingDialog.cancel();
                    }
                    return true;
                }
            });
            mConnectingDialog.show();
        } else {
            if (mConnectingDialog != null) {
                mConnectingDialog.cancel();
            }
        }
    }

    // region Actions


    public void onClickScan(View view) {
        boolean isScanning = mScanner != null && mScanner.isScanning();
        if (isScanning) {
            stopScanning();
        } else {
            startScan(null, null);
        }
    }

    public void onClickDeviceConnect(final View view) {
        stopScanning();

        final int scannedDeviceIndex = (Integer) view.getTag();
        if (scannedDeviceIndex < mScannedDevices.size()) {
            mSelectedDeviceData = mScannedDevices.get(scannedDeviceIndex);
            BluetoothDevice device = mSelectedDeviceData.device;

            mBleManager.setBleListener(PeripheralActivity.this);           // Force set listener (could be still checking for updates...)

            if (mSelectedDeviceData.type == BluetoothDeviceData.kType_Uart) {      // if is uart, show all the available activities
                connect(device);
            }
        } else {
            Log.w(TAG, "onClickDeviceConnect index does not exist: " + scannedDeviceIndex);
        }
    }

    // endregion

    // region Scan

    private void startScan(final UUID[] servicesToScan, final String deviceNameToScanFor) {
        Log.d(TAG, "startScan");

        // Stop current scanning (if needed)
        stopScanning();

        // Configure scanning
        BluetoothAdapter bluetoothAdapter = BleUtils.getBluetoothAdapter(getApplicationContext());
        if (BleUtils.getBleStatus(this) != BleUtils.STATUS_BLE_ENABLED) {
            Log.w(TAG, "startScan: BluetoothAdapter not initialized or unspecified address.");
        } else {
            mScanner = new BleDevicesScanner(bluetoothAdapter, servicesToScan, new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                    final String deviceName = device.getName();

                    if (deviceName == null)
                        return;

                    BluetoothDeviceData previouslyScannedDeviceData = null;
                    if (deviceNameToScanFor == null || (deviceName != null && deviceName.equalsIgnoreCase(deviceNameToScanFor))) {       // Workaround for bug in service discovery. Discovery filtered by service uuid is not working on Android 4.3, 4.4
                        if (mScannedDevices == null) mScannedDevices = new ArrayList<>();       // Safeguard

                        // Check that the device was not previously found
                        for (BluetoothDeviceData deviceData : mScannedDevices) {
                            if (deviceData.device.getAddress().equals(device.getAddress())) {
                                previouslyScannedDeviceData = deviceData;
                                break;
                            }
                        }

                        BluetoothDeviceData deviceData;
                        if (previouslyScannedDeviceData == null) {
                            // Add it to the mScannedDevice list
                            deviceData = new BluetoothDeviceData();
                            mScannedDevices.add(deviceData);
                        } else {
                            deviceData = previouslyScannedDeviceData;
                        }

                        deviceData.device = device;
                        deviceData.rssi = rssi;
                        deviceData.scanRecord = scanRecord;
                        decodeScanRecords(deviceData);

                        // Update device data
                        long currentMillis = SystemClock.uptimeMillis();
                        if (previouslyScannedDeviceData == null || currentMillis - mLastUpdateMillis > kMinDelayToUpdateUI) {          // Avoid updating when not a new device has been found and the time from the last update is really short to avoid updating UI so fast that it will become unresponsive
                            mLastUpdateMillis = currentMillis;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI();
                                }
                            });
                        }
                    }
                }
            });

            // Start scanning
            mScanner.start();
        }

        // Update UI
        updateUI();
    }

    private void stopScanning() {
        // Stop scanning
        if (mScanner != null) {
            mScanner.stop();
            mScanner = null;
        }

        updateUI();
    }

    private void resumeScanning() {
        if (mIsScanPaused) {
            startScan(null, null);
            mIsScanPaused = mScanner == null;
        }
    }

    // endregion

    private void decodeScanRecords(BluetoothDeviceData deviceData) {
        // based on http://stackoverflow.com/questions/24003777/read-advertisement-packet-in-android
        final byte[] scanRecord = deviceData.scanRecord;

        ArrayList<UUID> uuids = new ArrayList<>();
        byte[] advertisedData = Arrays.copyOf(scanRecord, scanRecord.length);
        int offset = 0;
        deviceData.type = BluetoothDeviceData.kType_Unknown;

        // Check if is an iBeacon ( 0x02, 0x0x1, a flag byte, 0x1A, 0xFF, manufacturer (2bytes), 0x02, 0x15)
        final boolean isBeacon = advertisedData[0] == 0x02 && advertisedData[1] == 0x01 && advertisedData[3] == 0x1A && advertisedData[4] == (byte) 0xFF && advertisedData[7] == 0x02 && advertisedData[8] == 0x15;

        // Check if is an URIBeacon
        final byte[] kUriBeaconPrefix = {0x03, 0x03, (byte) 0xD8, (byte) 0xFE};
        final boolean isUriBeacon = Arrays.equals(Arrays.copyOf(scanRecord, kUriBeaconPrefix.length), kUriBeaconPrefix) && advertisedData[5] == 0x16 && advertisedData[6] == kUriBeaconPrefix[2] && advertisedData[7] == kUriBeaconPrefix[3];

        if (isBeacon) {
            deviceData.type = BluetoothDeviceData.kType_Beacon;

            // Read uuid
            offset = 9;
            UUID uuid = BleUtils.getUuidFromByteArrayBigEndian(Arrays.copyOfRange(scanRecord, offset, offset + 16));
            uuids.add(uuid);
            offset += 16;

            // Skip major minor
            offset += 2 * 2;   // major, minor

            // Read txpower
            final int txPower = advertisedData[offset++];
            deviceData.txPower = txPower;
        } else if (isUriBeacon) {
            deviceData.type = BluetoothDeviceData.kType_UriBeacon;

            // Read txpower
            final int txPower = advertisedData[9];
            deviceData.txPower = txPower;
        } else {
            // Read standard advertising packet
            while (offset < advertisedData.length - 2) {
                // Length
                int len = advertisedData[offset++];
                if (len == 0) break;

                // Type
                int type = advertisedData[offset++];
                if (type == 0) break;

                // Data
//            Log.d(TAG, "record -> lenght: " + length + " type:" + type + " data" + data);

                switch (type) {
                    case 0x02: // Partial list of 16-bit UUIDs
                    case 0x03: {// Complete list of 16-bit UUIDs
                        while (len > 1) {
                            int uuid16 = advertisedData[offset++] & 0xFF;
                            uuid16 |= (advertisedData[offset++] << 8);
                            len -= 2;
                            uuids.add(UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", uuid16)));
                        }
                        break;
                    }

                    case 0x06:          // Partial list of 128-bit UUIDs
                    case 0x07: {        // Complete list of 128-bit UUIDs
                        while (len >= 16) {
                            try {
                                // Wrap the advertised bits and order them.
                                UUID uuid = BleUtils.getUuidFromByteArraLittleEndian(Arrays.copyOfRange(advertisedData, offset, offset + 16));
                                uuids.add(uuid);

                            } catch (IndexOutOfBoundsException e) {
                                Log.e(TAG, "BlueToothDeviceFilter.parseUUID: " + e.toString());
                            } finally {
                                // Move the offset to read the next uuid.
                                offset += 16;
                                len -= 16;
                            }
                        }
                        break;
                    }

                    case 0x0A: {   // TX Power
                        final int txPower = advertisedData[offset++];
                        deviceData.txPower = txPower;
                        break;
                    }

                    default: {
                        offset += (len - 1);
                        break;
                    }
                }
            }

            // Check if Uart is contained in the uuids
            boolean isUart = false;
            for (UUID uuid : uuids) {
                if (uuid.toString().equalsIgnoreCase(UartInterfaceActivity.UUID_SERVICE)) {
                    isUart = true;
                    break;
                }
            }
            if (isUart) {
                deviceData.type = BluetoothDeviceData.kType_Uart;
            }
        }

        deviceData.uuids = uuids;
    }


    private void updateUI() {
        // Scan button
        boolean isScanning = mScanner != null && mScanner.isScanning();
        mScanButton.setText(getString(isScanning ? R.string.scan_scanbutton_scanning : R.string.scan_scanbutton_scan));

        // Show list and hide "no devices" label
        final boolean isListEmpty = mScannedDevices == null || mScannedDevices.size() == 0;
        mNoDevicesTextView.setVisibility(isListEmpty ? View.VISIBLE : View.GONE);
        //mDevicesScrollView.setVisibility(isListEmpty ? View.GONE : View.VISIBLE);

        // devices list
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    // region BleManagerListener

    @Override
    public void onConnected() {

    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void onDisconnected() {
        showConnectionStatus(false);
    }

    @Override
    public void onServicesDiscovered() {
        // Enable generic attribute service
        final BluetoothGattService genericAttributeService = mBleManager.getGattService(kGenericAttributeService);
        if (genericAttributeService != null) {
            Log.d(TAG, "kGenericAttributeService found. Check if kServiceChangedCharacteristic exists");

            final UUID characteristicUuid = UUID.fromString(kServiceChangedCharacteristic);
            final BluetoothGattCharacteristic dataCharacteristic = genericAttributeService.getCharacteristic(characteristicUuid);
            if (dataCharacteristic != null) {
                Log.d(TAG, "kServiceChangedCharacteristic exists. Enable indication");
                mBleManager.enableIndication(genericAttributeService, kServiceChangedCharacteristic, true);
            } else {
                Log.d(TAG, "Skip enable indications for kServiceChangedCharacteristic. Characteristic not found");
            }
        } else {
            Log.d(TAG, "Skip enable indications for kServiceChangedCharacteristic. kGenericAttributeService not found");
        }

        Intent peripheralIntent = getIntent();

        // Launch activity
        showConnectionStatus(false);
        Intent intent = new Intent(PeripheralActivity.this, SimulatorActivity.class);
        intent.putExtra("rssi", mSelectedDeviceData.rssi); // for Beacon
        intent.putExtra("mode", peripheralIntent.getStringExtra("mode"));
        intent.putExtra("scenario", peripheralIntent.getIntExtra("scenario", -1));
        startActivityForResult(intent, kActivityRequestCode_ConnectedActivity);
    }

    @Override
    public void onDataAvailable(BluetoothGattCharacteristic characteristic) {

    }

    @Override
    public void onDataAvailable(BluetoothGattDescriptor descriptor) {

    }

    @Override
    public void onReadRemoteRssi(int rssi) {

    }

    // endregion

    // region ResetBluetoothAdapterListener

    @Override
    public void resetBluetoothCompleted() {
        Log.d(TAG, "Reset completed -> Resume scanning");
        resumeScanning();
    }

    // endregion

    // region Helpers

    //endregion


    // region DataFragment

    public static class DataFragment extends Fragment {
        private ArrayList<BluetoothDeviceData> mScannedDevices;
        private String mLatestCheckedDeviceAddress;
        private BluetoothDeviceData mSelectedDeviceData;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }


    private void restoreRetainedDataFragment(){
        FragmentManager fm = getFragmentManager();
        mRetainedDataFragment = (DataFragment)fm.findFragmentByTag(TAG);

        if (mRetainedDataFragment == null) {

            mRetainedDataFragment = new DataFragment();
            fm.beginTransaction().add(mRetainedDataFragment, TAG).commitAllowingStateLoss();

            mScannedDevices = new ArrayList<>();
        } else {
            mScannedDevices = mRetainedDataFragment.mScannedDevices;
            mLatestCheckedDeviceAddress = mRetainedDataFragment.mLatestCheckedDeviceAddress;
            mSelectedDeviceData = mRetainedDataFragment.mSelectedDeviceData;
        }
    }

    private void saveRetainedDataFragment() {
        mRetainedDataFragment.mScannedDevices = mScannedDevices;
        mRetainedDataFragment.mLatestCheckedDeviceAddress = mLatestCheckedDeviceAddress;
        mRetainedDataFragment.mSelectedDeviceData = mSelectedDeviceData;
    }

    // endregion
}
