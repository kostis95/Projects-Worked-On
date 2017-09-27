package hva.nl.bloqs.simulator;

import hva.nl.bloqs.simulator.scenarios.CompleteCircuitScenario;

/**
 * Created by Frank on 5-6-2016.
 */
public abstract class GameScenario {

    public final static GameScenario[] Scenarios = new  GameScenario[]{
        new CompleteCircuitScenario()
    };

    public abstract String getTitle();
    public abstract String getShortDescription();
    public abstract String getDescription();
    public abstract int getDifficulty();

    public abstract void initializeWorld(World world);

    public abstract boolean hasMetConditions(World world);
}
