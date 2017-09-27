package hva.nl.bloqs.simulator.blocks;

import android.graphics.Canvas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import hva.nl.bloqs.simulator.Block;
import hva.nl.bloqs.simulator.World;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Button extends Block {

    private boolean pressed = false;

    public Button(){}
    public Button(Button button)
    {
        super(button);
        this.pressed = button.pressed;
    }

    @Override
    public Block update(long currentTick) {
        Button button = new Button(this);

        button.setPowered(isPressed());

        return button;
    }

   @Override
    public void draw(Canvas canvas) {
//
//        if(isPressed())
//            gc.setFill(Color.YELLOW);
//        else
//            gc.setFill(Color.AQUA);
//
//        gc.fillRect(getX() * World.BLOCK_SIZE + 10, getY() * World.BLOCK_SIZE + 10, 5, 5);
//
    }

    @Override
    public int getBlockType() {
        return 2;
    }

    @Override
    public boolean canEmitPower() {
        return true;
    }

    public boolean isPressed()
    {
        return pressed;
    }

    public void setPressed(boolean pressed)
    {
        this.pressed = pressed;
    }}
