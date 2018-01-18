package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public interface Drawble {
    public void draw(SpriteBatch batch);
    public List<GameObject> getparts();
}
