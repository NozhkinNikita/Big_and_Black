package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.contoller.PlayerController;

public class Food extends GameObject {

    public Food(Texture texture, float x, float y, float radious) {
        super(texture, x, y, radious*2, radious*2,radious,null);
        this.radious = radious;
    }

    @Override
    public String getType() {
        return "food";
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

}
