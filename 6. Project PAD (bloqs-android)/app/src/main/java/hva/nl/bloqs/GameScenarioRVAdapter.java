package hva.nl.bloqs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hva.nl.bloqs.simulator.GameScenario;

public class GameScenarioRVAdapter extends RecyclerView.Adapter<GameScenarioRVAdapter.GameScenarioDataViewHolder> {

    public static class GameScenarioDataViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameTextView;
        TextView descriptionTextView;

        GameScenarioDataViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public GameScenarioDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_scenario_item, viewGroup, false);
        GameScenarioDataViewHolder gsdvh = new GameScenarioDataViewHolder(v);
        gsdvh.cardView.setTag(i);
        return gsdvh;
    }

    @Override
    public void onBindViewHolder(GameScenarioDataViewHolder GameScenarioDataViewHolder, int i) {
        GameScenarioDataViewHolder.nameTextView.setText(GameScenario.Scenarios[i].getTitle());
        GameScenarioDataViewHolder.descriptionTextView.setText(GameScenario.Scenarios[i].getShortDescription());
    }

    @Override
    public int getItemCount() {
        return GameScenario.Scenarios.length;
    }
}