package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;

public class Wire extends Block {

    private Boolean[] adjacentStates = new Boolean[] { null, null, null, null};

    public Wire(){}
    public Wire(Wire wire){
        super(wire);
        this.adjacentStates = wire.adjacentStates;
    }

    @Override
    public boolean isPowered(int side) {

        if(Helpers.coalesce(adjacentStates[SIDE_TOP], false) == true && side == SIDE_TOP)
            return false;
        if(Helpers.coalesce(adjacentStates[SIDE_RIGHT], false) == true && side == SIDE_RIGHT)
            return false;
        if(Helpers.coalesce(adjacentStates[SIDE_BOTTOM], false) == true && side == SIDE_BOTTOM)
            return false;
        if(Helpers.coalesce(adjacentStates[SIDE_LEFT], false) == true && side == SIDE_LEFT)
            return false;



        return Helpers.coalesce(adjacentStates[SIDE_TOP], false) ||
                Helpers.coalesce(adjacentStates[SIDE_RIGHT], false) ||
                Helpers.coalesce(adjacentStates[SIDE_BOTTOM], false) ||
                Helpers.coalesce(adjacentStates[SIDE_LEFT], false);
    }


    @Override
    public Block update(long currentTick) {
        Wire wire = new Wire(this);

        wire.adjacentStates = getAdjacentPowerStates();
        return wire;
    }

    @Override
    public void draw(Canvas canvas) {
//
//        if(isPowered())
//            gc.setFill(Color.YELLOW);
//        else
//            gc.setFill(Color.AQUA);
//
//        // Fill Center wire.
//        gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE + 10, 5, 5);
//
//
//        // Fill sides if connected.
//        if (isAdjacentPowered(SIDE_TOP) != null)
//            gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE, 5, 15);
//        if (isAdjacentPowered(SIDE_RIGHT) != null)
//            gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE + 10, 15, 5);
//        if (isAdjacentPowered(SIDE_BOTTOM) != null)
//            gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE + 15, 5, 15);
//        if (isAdjacentPowered(SIDE_LEFT) != null)
//            gc.fillRect(getX() * World.BLOCK_SIZE, getY() * World.BLOCK_SIZE + 10, 15, 5);
    }

    @Override
    public int getBlockType() {
        return 1;
    }

    @Override
    public boolean canEmitPower() {
        return false;
    }
}
