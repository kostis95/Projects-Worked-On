package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.Helpers;
import hva.nl.bloqs.simulator.World;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Light extends Block {

    private Boolean[] adjacentStates = new Boolean[] { null, null, null, null};

    public Light(){}
    public Light(Light light)
    {
        super(light);
        this.adjacentStates = light.adjacentStates;
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
        Light light = new Light(this);

        light.adjacentStates = getAdjacentPowerStates();
        return light;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public int getBlockType() {
        return 8;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }

}
