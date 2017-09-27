package hva.nl.bloqs;

import hva.nl.bloqs.simulator.World;

public class SimulationManager {

    private volatile static World mSimulation;

    public static World getSimulation() {
        if(mSimulation == null)
            mSimulation = new World(15, 15);
        return mSimulation;
    }
}
