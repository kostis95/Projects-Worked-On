package hva.nl.bloqs.simulator.scenarios;

import hva.nl.bloqs.simulator.GameScenario;
import hva.nl.bloqs.simulator.World;
import hva.nl.bloqs.simulator.blocks.Button;
import hva.nl.bloqs.simulator.blocks.Light;
import hva.nl.bloqs.simulator.blocks.Not;
import hva.nl.bloqs.simulator.blocks.Wire;

public class Tutorial2Scenario extends GameScenario {
    @Override
    public String getTitle() {
        return "Tutorial 2";
    }

    @Override
    public String getShortDescription() {
        return "Power off the light!";
    }

    @Override
    public String getDescription() {
        return "Use a wire and turn on the button to turn off the light";
    }

    @Override
    public int getDifficulty() {
        return 1;
    }

    @Override
    public void initializeWorld(World world) {
        world.setBlockAt(1, 1, new Button());

        world.setBlockAt(3, 1, new Not());
        world.setBlockAt(4, 1, new Wire());
        world.setBlockAt(5, 1, new Light());
    }

    @Override
    public boolean hasMetConditions(World world) {
        return world.getBlockAt(1, 1, Button.class).isPressed() &&
                !world.getBlockAt(5, 1, Light.class).isPowered();
    }
}
