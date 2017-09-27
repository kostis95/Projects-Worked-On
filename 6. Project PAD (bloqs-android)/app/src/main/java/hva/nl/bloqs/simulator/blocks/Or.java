package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;

public class Or extends Block {

    public Or(){}
    public Or(Or or){
        super(or);
    }

    @Override
    public Block update(long currentTick) {
        Or or = new Or(this);

        // Return false if one of the 3 inputs are on.

        Boolean[] states = getAdjacentPowerStates();

        if((Helpers.coalesce(states[SIDE_TOP], false) == true && getOrientation() != SIDE_TOP) ||
                (Helpers.coalesce(states[SIDE_RIGHT], false) == true && getOrientation() != SIDE_RIGHT) ||
                (Helpers.coalesce(states[SIDE_LEFT], false) == true && getOrientation() != SIDE_LEFT) ||
                (Helpers.coalesce(states[SIDE_BOTTOM], false) == true && getOrientation() != SIDE_BOTTOM))
            or.setPowered(true, getOrientation());
        else
            or.setPowered(false, getOrientation());

        return or;
    }

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
////        gc.fillText("Or", getX() * World.BLOCK_SIZE + 2, getY() * World.BLOCK_SIZE + 17);
//    }

    @Override
    public int getBlockType() {
        return 4;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }
}
