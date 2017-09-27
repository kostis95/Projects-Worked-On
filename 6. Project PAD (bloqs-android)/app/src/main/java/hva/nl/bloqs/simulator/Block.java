package hva.nl.bloqs.simulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Block {
    private boolean[] powerState = new boolean[4];


    private World world;
    private int x;
    private int y;
    private int orientation;

    public Block(){}
    public Block(Block block) {
        this.powerState = block.powerState;
        this.world = block.world;
        this.x = block.x;
        this.y = block.y;
        this.orientation = block.orientation;
    }

    public abstract Block update(long currentTick);
    public abstract int getBlockType();
    public abstract boolean canEmitPower();


    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        if (isPowered())
            paint.setColor(Color.YELLOW);
        else
            paint.setColor(Color.BLUE);

        canvas.drawRect(getX() * World.BLOCK_SIZE, getY() * World.BLOCK_SIZE, World.BLOCK_SIZE, World.BLOCK_SIZE, paint);


        paint.setColor(Color.BLACK);
        canvas.drawText("And",  getX() * World.BLOCK_SIZE + 2, getY() * World.BLOCK_SIZE + 17, paint);
    }

    public void setPowered(boolean state) { setPowered(state, SIDE_ALL); }

    public void setPowered(boolean state, int side){
        if (side == SIDE_ALL)
            for(int i = 0; i < 4; i ++)
                powerState[i] = state;
        else
            powerState[side] = state;
    }

    /**
     * Whether any of the sides is sending power to the adjacent blocks.
     * @return True if any of the sides is sending, False otherwise.
     */
    public boolean isPowered() {
        return isPowered(SIDE_ALL);
    }

    /**
     * Whether the given side of the block is sending power to the adjacent blocks.
     * @param side The side to check if it sends power.
     * @return True if the side sends power, False otherwise.
     */
    public boolean isPowered(int side) {

        if (side == SIDE_ALL)
            return powerState[SIDE_TOP] ||
                    powerState[SIDE_RIGHT] ||
                    powerState[SIDE_BOTTOM] ||
                    powerState[SIDE_LEFT];

        return powerState[side];
    }

    public Boolean isAdjacentPowered(int side) {

        switch (side)
        {
            case SIDE_TOP:
                if (getAdjacentTop() == null)
                    return null;
                return getAdjacentTop().isPowered(SIDE_BOTTOM);
            case SIDE_RIGHT:
                if (getAdjacentRight() == null)
                    return  null;
                return  getAdjacentRight().isPowered(SIDE_LEFT);
            case SIDE_BOTTOM:
                if (getAdjacentBottom() == null)
                    return null;
                return getAdjacentBottom().isPowered(SIDE_TOP);
            case SIDE_LEFT:
                if (getAdjacentLeft() == null)
                    return null;
                return getAdjacentLeft().isPowered(SIDE_RIGHT);
            case SIDE_ALL:
                if (getAdjacentTop() == null && getAdjacentRight() == null && getAdjacentBottom() == null && getAdjacentLeft() == null)
                    return  null;

                return (isAdjacentPowered(SIDE_TOP) ||
                        isAdjacentPowered(SIDE_RIGHT) ||
                        isAdjacentPowered(SIDE_BOTTOM) ||
                        isAdjacentPowered(SIDE_LEFT));
        }

        throw new IllegalArgumentException();
    }

    public Boolean[] getAdjacentPowerStates(){
        return new Boolean[] {
                isAdjacentPowered(SIDE_TOP),
                isAdjacentPowered(SIDE_RIGHT),
                isAdjacentPowered(SIDE_BOTTOM),
                isAdjacentPowered(SIDE_LEFT)
        };
    }

    public Boolean[] getAdjacentPowerStates(int excludedSide) {
        Boolean[] states = getAdjacentPowerStates();
        states[excludedSide] = null;

        return states;
    }

    public Block getAdjacent(int side) {
        return getAdjacent(side, Block.class);
    }

    public <T extends Block> T getAdjacent(int side, Class<T> type) {
        switch (side) {
            case SIDE_TOP:
                return getAdjacentTop(type);
            case SIDE_RIGHT:
                return getAdjacentRight(type);
            case SIDE_BOTTOM:
                return getAdjacentBottom(type);
            case SIDE_LEFT:
                return getAdjacentLeft(type);
        }

        throw new IllegalArgumentException();
    }

    @JsonIgnore
    public Block getAdjacentLeft() {
        return getAdjacentLeft(Block.class);
    }

    @JsonIgnore
    public Block getAdjacentRight() {
        return getAdjacentRight(Block.class);
    }

    @JsonIgnore
    public Block getAdjacentTop(){
        return getAdjacentTop(Block.class);
    }

    @JsonIgnore
    public Block getAdjacentBottom() {
        return getAdjacentBottom(Block.class);
    }

    public  <T extends Block> T getAdjacentLeft(Class<T> type)
    {
        return world.getBlockAt(x - 1, y, type);
    }

    public  <T extends Block> T getAdjacentRight(Class<T> type)
    {
        return world.getBlockAt(x + 1, y, type);
    }

    public  <T extends Block> T getAdjacentTop(Class<T> type)
    {
        return world.getBlockAt(x, y - 1, type);
    }

    public  <T extends Block> T getAdjacentBottom(Class<T> type)
    {
        return world.getBlockAt(x, y + 1, type);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getOrientation() { return orientation; }

    public void setOrientation(int orientation) {
        setPowered(false, SIDE_ALL);
        this.orientation = orientation;
    }

    protected void setPosition(int x, int y, int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    protected void setWorld(World world) {
        this.world = world;
    }


    public static final int SIDE_ALL = -1;
    public static final int SIDE_TOP = 0;
    public static final int SIDE_RIGHT = 1;
    public static final int SIDE_BOTTOM = 2;
    public static final int SIDE_LEFT = 3;
}
