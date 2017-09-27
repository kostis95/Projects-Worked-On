package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;

public class Not extends Block {

    public Not(){}
    public Not(Not not){
        super(not);
    }

    @Override
    public Block update(long currentTick) {
        Not not = new Not(this);

        // Return false if one of the 3 inputs are on.

        Boolean[] states = getAdjacentPowerStates();

        if((Helpers.coalesce(states[SIDE_TOP], false) == true && getOrientation() != SIDE_TOP) ||
                (Helpers.coalesce(states[SIDE_RIGHT], false) == true && getOrientation() != SIDE_RIGHT) ||
                (Helpers.coalesce(states[SIDE_LEFT], false) == true && getOrientation() != SIDE_LEFT) ||
                (Helpers.coalesce(states[SIDE_BOTTOM], false) == true && getOrientation() != SIDE_BOTTOM))
            not.setPowered(false, getOrientation());
        else
            not.setPowered(true, getOrientation());

        return not;
    }
//
//    @Override
//    public void draw(Canvas canvas) {
////        if (isPowered())
////            gc.setFill(Color.YELLOW);
////        else
////            gc.setFill(Color.AQUA);
////
////        gc.fillRect(getX() * World.BLOCK_SIZE, getY() * World.BLOCK_SIZE, World.BLOCK_SIZE, World.BLOCK_SIZE);
////
////        gc.setFill(Color.BLACK);
////        gc.fillText("Not", getX() * World.BLOCK_SIZE + 2, getY() * World.BLOCK_SIZE + 17);
//    }

    @Override
    public int getBlockType() {
        return 3;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }
}
