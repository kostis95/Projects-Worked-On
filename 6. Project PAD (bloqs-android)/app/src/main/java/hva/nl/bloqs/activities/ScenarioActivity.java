package hva.nl.bloqs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hva.nl.bloqs.GameScenarioRVAdapter;
import hva.nl.bloqs.R;

/**
 * Created by Frank on 6-6-2016.
 */
public class ScenarioActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private GameScenarioRVAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerViewAdapter = new GameScenarioRVAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

    }

    public void onClickScenario(final View v) {
        final Integer scenarioIndex = (Integer) v.getTag();

        Intent intent = new Intent(this, SimulatorActivity.class);
        intent.putExtra("mode", "scenario");
        intent.putExtra("scenario", scenarioIndex);
        startActivity(intent);
    }
}