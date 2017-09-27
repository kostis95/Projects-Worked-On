package hva.nl.bloqs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hva.nl.bloqs.R;

/**
 * Created by Frank on 6-6-2016.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSandboxClick(View v) {
        Intent intent = new Intent(this, SimulatorActivity.class);
        intent.putExtra("mode", "sandbox");
        startActivity(intent);
    }

    public void btnScenarioClick(View v) {
        Intent intent = new Intent(this, ScenarioActivity.class);
        intent.putExtra("mode", "scenario");
        startActivity(intent);
    }
}
