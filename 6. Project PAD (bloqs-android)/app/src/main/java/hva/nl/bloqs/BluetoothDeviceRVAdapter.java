package hva.nl.bloqs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BluetoothDeviceRVAdapter extends RecyclerView.Adapter<BluetoothDeviceRVAdapter.BluetoothDeviceDataViewHolder> {

    public static class BluetoothDeviceDataViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView rssiImageView;
        TextView rssiTextView;
        TextView nameTextView;
        TextView descriptionTextView;

        BluetoothDeviceDataViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            rssiImageView = (ImageView)itemView.findViewById(R.id.rssiImageView);

        }
    }

    List<BluetoothDeviceData> devices;

    public BluetoothDeviceRVAdapter(List<BluetoothDeviceData> devices){
        this.devices = devices;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BluetoothDeviceDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_scan_item, viewGroup, false);
        BluetoothDeviceDataViewHolder bddvh = new BluetoothDeviceDataViewHolder(v);
        bddvh.cardView.setTag(i);
        return bddvh;
    }

    @Override
    public void onBindViewHolder(BluetoothDeviceDataViewHolder bluetoothDeviceDataViewHolder, int i) {
        bluetoothDeviceDataViewHolder.nameTextView.setText(devices.get(i).device.getName());

        int rrsiDrawableResource = getDrawableIdForRssi(devices.get(i).rssi);
        bluetoothDeviceDataViewHolder.rssiImageView.setImageResource(rrsiDrawableResource);

        //bluetoothDeviceDataViewHolder.personAge.setText(devices.get(i).age);
        //bluetoothDeviceDataViewHolder.personPhoto.setImageResource(devices.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }


    private int getDrawableIdForRssi(int rssi) {
        int index;
        if (rssi == 127 || rssi <= -84) {       // 127 reserved for RSSI not available
            index = 0;
        } else if (rssi <= -72) {
            index = 1;
        } else if (rssi <= -60) {
            index = 2;
        } else if (rssi <= -48) {
            index = 3;
        } else {
            index = 4;
        }

        final int kSignalDrawables[] = {
                R.drawable.signalstrength0,
                R.drawable.signalstrength1,
                R.drawable.signalstrength2,
                R.drawable.signalstrength3,
                R.drawable.signalstrength4};
        return kSignalDrawables[index];
    }
}