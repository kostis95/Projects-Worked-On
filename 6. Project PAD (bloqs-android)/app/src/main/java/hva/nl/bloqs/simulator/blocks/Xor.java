package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;

public class Xor extends Block {

    public Xor(){}
    public Xor(Xor xor){
        super(xor);
    }

    @Override
    public Block update(long currentTick) {
        Xor xor = new Xor(this);

        // Return false if one of the 3 inputs are on.

        Boolean[] states = getAdjacentPowerStates();

        boolean isOneOn = false;

        for(int i = 0; i < 4; i ++) {
            if(i == getOrientation())
                continue;

            if(Helpers.coalesce(states[i], false) == true) {
                if (isOneOn == true)
                {
                    isOneOn = false;
                    break;
                }
                isOneOn = true;
            }
        }

        xor.setPowered(isOneOn, getOrientation());


        return xor;
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
////        gc.fillText("Xor", getX() * World.BLOCK_SIZE + 2, getY() * World.BLOCK_SIZE + 17);
//    }

    @Override
    public int getBlockType() {
        return 6;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }
}
