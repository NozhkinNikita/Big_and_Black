package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Virus extends GameObject {

    public Virus(Texture texture, float x, float y, float radious) {
        super(texture, x, y, radious * 2, radious * 2, radious, null);
        this.radious = radious;
    }

    @Override
    public String getType() {
        return "virus";
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        //  playerController.handle();
    }

}
