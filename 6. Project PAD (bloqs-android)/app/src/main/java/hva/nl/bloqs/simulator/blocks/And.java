package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.World;


public class And extends Block {

    public And(){}
    public And(And and){
        super(and);
    }


    @Override
    public Block update(long currentTick) {
        And and = new And(this);

        Boolean[] states = getAdjacentPowerStates();

        boolean allNull = true;
        boolean allOn = true;

        for (int i = 0; i < 4; i++)
        {
            if (i == getOrientation())
                continue;

            if(states[i] != null) {
                allNull = false;

                if (states[i] != true)
                    allOn = false;
            }
        }

        if(!allNull && allOn)
            and.setPowered(true, getOrientation());
        else
            and.setPowered(false, getOrientation());


        return and;
    }

//    @Override
//    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//
//        if (isPowered())
//            paint.setColor(Color.YELLOW);
//        else
//            paint.setColor(Color.BLUE);
//
//        canvas.drawRect(getX() * World.BLOCK_SIZE, getY() * World.BLOCK_SIZE, World.BLOCK_SIZE, World.BLOCK_SIZE, paint);
//
//
//        paint.setColor(Color.BLACK);
//        canvas.drawText("And",  getX() * World.BLOCK_SIZE + 2, getY() * World.BLOCK_SIZE + 17, paint);
//    }

    @Override
    public int getBlockType() {
        return 5;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }
}
