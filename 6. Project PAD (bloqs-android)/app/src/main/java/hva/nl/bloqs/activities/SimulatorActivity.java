package hva.nl.bloqs.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.items.BottomSheetMenuItem;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

import hva.nl.bloqs.R;
import hva.nl.bloqs.UartDataChunk;
import hva.nl.bloqs.ble.BleManager;
import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.WorldEventListener;
import hva.nl.bloqs.simulator.World;
import hva.nl.bloqs.simulator.blocks.And;
import hva.nl.bloqs.simulator.blocks.Button;
import hva.nl.bloqs.simulator.blocks.Cross;
import hva.nl.bloqs.simulator.blocks.Light;
import hva.nl.bloqs.simulator.blocks.Not;
import hva.nl.bloqs.simulator.blocks.Or;
import hva.nl.bloqs.simulator.blocks.Wire;
import hva.nl.bloqs.simulator.blocks.Xor;

public class SimulatorActivity extends UartInterfaceActivity implements WorldEventListener, BleManager.BleManagerListener {

    private final static String TAG = SimulatorActivity.class.getSimpleName();

    private World mSimulation;
    private String mMode = null;
    private Integer mScenario = null;
    private volatile ArrayList<UartDataChunk> mDataBuffer;
    private volatile int mSentBytes;
    private volatile int mReceivedBytes;

    private DataFragment mRetainedDataFragment;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        Intent intent = getIntent();
        mMode = intent.getStringExtra("mode");
        if (intent.hasExtra("scenario"))
        {
            int scenario = intent.getIntExtra("scenario", -1);
            if (scenario >= 0)
                mScenario = scenario;
        }

        mBleManager = BleManager.getInstance(this);
        restoreRetainedDataFragment();


        onServicesDiscovered();

    }

    @Override
    protected void onStart() {
        super.onStart();

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/app/index.html");
        webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }

    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mBleManager.setBleListener(this);

        mSimulation.setWorldEventListener(this);

        startSimulation();
   }

    @Override
    public void onPause() {

        if (mSimulation != null)
            mSimulation.stop();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        if (mSimulation != null)
            mSimulation.stop();

        saveRetainedDataFragment();

        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simulation_main_menu, menu);

        if (mBleManager.getState() != BleManager.STATE_DISCONNECTED) {
            menu.findItem(R.id.action_connect).setIcon(R.drawable.ic_bluetooth_connected_white_18dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_connect:
                Intent intent = new Intent(this, PeripheralActivity.class);
                intent.putExtra("mode", mMode);
                intent.putExtra("scenario", mScenario);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onTickEvent(final long currentTick) {
        executeJavaScript("onTickEvent(" + currentTick + ");");
    }

    @Override
    public void onGridResize(int width, int height) {
        //executeJavaScript("onGridResize(" + width + ", " + height + ");");

    }

    @Override
    public void onScenarioMetConditions() {
        mSimulation.stop();

        SimulatorActivity.this.runOnUiThread(new Runnable() {
            public void run() {

                new AlertDialog.Builder(SimulatorActivity.this)
                        .setTitle("Scenario Completed")
                        .setMessage("You've successfully completed this scenario.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sendData(new byte[]{(byte) 0xEE});

                                Intent intent = new Intent(SimulatorActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).show();

                sendData(new byte[]{(byte) 0xEF});
            }
        });


    }

    private void startSimulation() {
        if (mSimulation != null && !mSimulation.isRunning())
            (new Thread(mSimulation)).start();
        else {
            Log.d(TAG, "Simulation is null or not running.");
        }
    }

    private void executeJavaScript(final String javascript) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + javascript);
            }
        });
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

    }

    @Override
    public void onServicesDiscovered() {
        mUartService = mBleManager.getGattService(UUID_SERVICE);

        mBleManager.enableNotification(mUartService, UUID_RX, true);
    }

    @Override
    public void onDataAvailable(BluetoothGattCharacteristic characteristic) {
        Log.d(TAG, "onDataAvailable");

        String data = characteristic.getStringValue(0);

        Log.d(TAG, data);
    }

    @Override
    public void onDataAvailable(BluetoothGattDescriptor descriptor) {

    }

    @Override
    public void onReadRemoteRssi(int rssi) {

    }

    // endregion

    // region DataFragmenet

    public static class DataFragment extends Fragment {

        private World mSimulation;
        private String mMode;
        private Integer mScenario;
        private ArrayList<UartDataChunk> mDataBuffer;
        private int mSentBytes;
        private int mRecievedBytes;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }


    private void restoreRetainedDataFragment() {
        FragmentManager fm = getFragmentManager();
        mRetainedDataFragment = (DataFragment)fm.findFragmentByTag(TAG);

        if (mRetainedDataFragment == null) {
            // Create
            mRetainedDataFragment = new DataFragment();
            fm.beginTransaction().add(mRetainedDataFragment, TAG).commit();

            // Init
            mSimulation = new World(15, 15);
            mSimulation.setScenario(mScenario);


            mDataBuffer = new ArrayList<>();
        } else {
            // Restore status
            mDataBuffer = mRetainedDataFragment.mDataBuffer;
            mSentBytes = mRetainedDataFragment.mSentBytes;
            mReceivedBytes = mRetainedDataFragment.mRecievedBytes;
            mSimulation = mRetainedDataFragment.mSimulation;
            mMode = mRetainedDataFragment.mMode;
            mScenario = mRetainedDataFragment.mScenario;
        }
    }

    private void saveRetainedDataFragment() {
        mRetainedDataFragment.mDataBuffer = mDataBuffer;
        mRetainedDataFragment.mSentBytes = mSentBytes;
        mRetainedDataFragment.mRecievedBytes = mReceivedBytes;
        mRetainedDataFragment.mSimulation = mSimulation;
        mRetainedDataFragment.mMode = mMode;
        mRetainedDataFragment.mScenario = mScenario;
    }

    // endregion


    public class JavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getJson()
        {
            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(mSimulation);

                return json;
            } catch(JsonProcessingException ex) {
                return ex.toString();
            }
        }

        @JavascriptInterface
        public void stop() {
            mSimulation.stop();
        }

        @JavascriptInterface
        public void start() {
            if (!mSimulation.isRunning())
                (new Thread(mSimulation)).start();
        }

        @JavascriptInterface
        public void gridClick(final int x, final int y) {
            final Block block = mSimulation.getBlockAt(x, y, Block.class);


            if (block != null) {

                PopupMenu p = new PopupMenu(mContext, null);
                Menu menu = p.getMenu();
                getMenuInflater().inflate(R.menu.simulation_block_menu, menu);

                // Enable the power options for buttons, Also toggle status.
                if(block.getBlockType() == 2)
                {
                    menu.getItem(0).setVisible(true);

                    Button button = mSimulation.getBlockAt(x, y, Button.class);

                    if(button.isPressed())
                        menu.getItem(0).setIcon(R.drawable.ic_flash_off_black_48dp);
                    else
                        menu.getItem(0).setIcon(R.drawable.ic_flash_on_black_48dp);

                }
                // Hide rotate options for blocks that don't have
                // any effect when rotated.
                if(block.getBlockType() == 1 ||
                        block.getBlockType() == 2 ||
                        block.getBlockType() == 7 ||
                        block.getBlockType() == 8) {
                    menu.getItem(1).setVisible(false);
                    menu.getItem(2).setVisible(false);
                }



                BottomSheetMenuDialog dialog = new BottomSheetBuilder(mContext, R.style.AppTheme_BottomSheetDialog)
                        .setMode(BottomSheetBuilder.MODE_GRID)
                        .setBackgroundColor(android.R.color.white)
                        .setMenu(menu)
                        .setItemClickListener(new BottomSheetItemClickListener() {
                            @Override
                            public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
                                switch (bottomSheetMenuItem.getId()) {
                                    case R.id.power:
                                        if (block.getBlockType() == 2) {
                                            Button button = mSimulation.getBlockAt(x, y, Button.class);

                                            button.setPressed(!button.isPressed());
                                        }
                                        break;
                                    case R.id.rotateLeft:
                                        int newOrientation = block.getOrientation() - 1;
                                        if (newOrientation < 0)
                                            newOrientation = 3;

                                        mSimulation.setBlockOrientation(x, y, newOrientation);
                                        break;
                                    case R.id.rotateRight:
                                        newOrientation = block.getOrientation() + 1;
                                        if (newOrientation > 3)
                                            newOrientation = 0;

                                        mSimulation.setBlockOrientation(x, y, newOrientation);
                                        break;
                                    case R.id.remove:
                                        mSimulation.removeBlockAt(x, y);
                                        break;
                                }
                            }
                        })
                        .createDialog();

                dialog.setTitle("Block @" + x + ";" + y);

                dialog.show();
            } else {

                PopupMenu p = new PopupMenu(mContext, null);
                Menu menu = p.getMenu();
                getMenuInflater().inflate(R.menu.simulation_new_block_menu, menu);

                BottomSheetMenuDialog dialog = new BottomSheetBuilder(mContext, R.style.AppTheme_BottomSheetDialog)
                        .setMode(BottomSheetBuilder.MODE_GRID)
                        .setBackgroundColor(android.R.color.white)
                        .setMenu(menu)
                        .setItemClickListener(new BottomSheetItemClickListener() {
                            @Override
                            public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
                                Block block = null;

                                switch (bottomSheetMenuItem.getId()) {
                                    case R.id.wire:
                                        block = new Wire();
                                        break;
                                    case R.id.cross:
                                        block = new Cross();
                                        break;
                                    case R.id.button:
                                        block = new Button();
                                        break;
                                    case R.id.and:
                                        block = new And();
                                        break;
                                    case R.id.not:
                                        block = new Not();
                                        break;
                                    case R.id.or:
                                        block = new Or();
                                        break;
                                    case R.id.xor:
                                        block = new Xor();
                                        break;
                                    case R.id.light:
                                        block = new Light();
                                        break;
                                }

                                if (block != null)
                                    mSimulation.setBlockAt(x, y, block);
                            }
                        })
                        .createDialog();

                dialog.setTitle("Block @" + x + ";" + y);

                dialog.show();
            }
        }
    }
}
