package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;


public class Cross extends Block {

    private Boolean[] adjacentStates = new Boolean[] { null, null, null, null};

    public Cross(){}
    public Cross(Cross cross){
        super(cross);
        this.adjacentStates = cross.adjacentStates;
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

        if (side == SIDE_LEFT || side == SIDE_RIGHT)
            return Helpers.coalesce(adjacentStates[SIDE_LEFT], false) ||
                    Helpers.coalesce(adjacentStates[SIDE_RIGHT], false);
        else
            return Helpers.coalesce(adjacentStates[SIDE_TOP], false) ||
                    Helpers.coalesce(adjacentStates[SIDE_BOTTOM], false);
    }


    @Override
    public Block update(long currentTick) {
        Cross cross = new Cross(this);

        cross.adjacentStates = getAdjacentPowerStates();
        return cross;
    }

    @Override
    public void draw(Canvas canvas) {
//        if(Helpers.coalesce(isAdjacentPowered(SIDE_TOP), false) || Helpers.coalesce(isAdjacentPowered(SIDE_BOTTOM), false))
//            gc.setFill(Color.YELLOW);
//        else
//            gc.setFill(Color.AQUA);
//
//        gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE, 5, 25);
//
//        if(Helpers.coalesce(isAdjacentPowered(SIDE_LEFT), false) || Helpers.coalesce(isAdjacentPowered(SIDE_RIGHT), false))
//            gc.setFill(Color.YELLOW);
//        else
//            gc.setFill(Color.AQUA);
//
//        gc.fillRect(getX() * World.BLOCK_SIZE, getY() * World.BLOCK_SIZE + 10, 7, 5);
//        gc.fillRect(getX() * World.BLOCK_SIZE + 18, getY() * World.BLOCK_SIZE + 10, 7, 5);
    }

    @Override
    public int getBlockType() {
        return 7;
    }

    @Override
    public boolean canEmitPower() {
        return false;
    }
}
