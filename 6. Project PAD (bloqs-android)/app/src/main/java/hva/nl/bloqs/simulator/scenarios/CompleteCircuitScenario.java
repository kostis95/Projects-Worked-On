package hva.nl.bloqs.simulator.scenarios;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.GameScenario;
import hva.nl.bloqs.simulator.World;
import hva.nl.bloqs.simulator.blocks.And;
import hva.nl.bloqs.simulator.blocks.Button;
import hva.nl.bloqs.simulator.blocks.Light;
import hva.nl.bloqs.simulator.blocks.Not;
import hva.nl.bloqs.simulator.blocks.Wire;


public class CompleteCircuitScenario extends GameScenario {
    @Override
    public String getTitle() {
        return "Complete the Scenario";
    }

    @Override
    public String getShortDescription() { return "Power on the light with a complete circuit."; }

    @Override
    public String getDescription() {
        return "This is a Scenario used for testing, in here we can explain what the user is supposted to do." +
                "Place a block at the coordinates 2;2.";
    }

    @Override
    public int getDifficulty() {
        return 1;
    }

    @Override
    public void initializeWorld(World world) {

        world.setBlockAt(1, 1, new Button());
        world.setBlockAt(1, 3, new Button());
        world.setBlockAt(1, 5, new Button());
        world.setBlockAt(1, 6, new Wire());
        world.setBlockAt(1, 7, new Wire());

        world.setBlockAt(2, 1, new Wire());
        world.setBlockAt(2, 3, new Wire());
        world.setBlockAt(2, 5, new Wire());
        world.setBlockAt(2, 7, new Wire());

        world.setBlockAt(3, 1, Block.SIDE_BOTTOM, new Not());
        world.setBlockAt(3, 2, new Wire());
        world.setBlockAt(3, 3, new And());
        world.setBlockAt(3, 4, new Wire());
        world.setBlockAt(3, 5, new Wire());
        world.setBlockAt(3, 7, new Not());

        world.setBlockAt(4, 3, new Wire());
        world.setBlockAt(4, 7, new Wire());

        world.setBlockAt(5, 3, new And());
        world.setBlockAt(5, 4, new Wire());
        //world.setBlockAt(6, 5, new Wire());
        world.setBlockAt(5, 6, new Wire());
        world.setBlockAt(5, 7, new Wire());

        world.setBlockAt(6, 3, new Wire());

        world.setBlockAt(7, 3, new Light());
    }


    @Override
    public boolean hasMetConditions(World world) {
        boolean result = false;

        if (world.getBlockAt(5, 5, Block.class) != null)
        {
            Block block = world.getBlockAt(5, 5, Block.class);

            if (block.getBlockType() == 3 && block.getOrientation() == block.SIDE_TOP)
                result = true;
        }

        Block block = world.getBlockAt(7, 3, Block.class);
        if (block == null || !block.isPowered())
            result = false;

        return result;
    }
}
