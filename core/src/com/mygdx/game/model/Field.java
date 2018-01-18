package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.List;


public class Field implements Drawble {

    private final Sprite sprite;
    private Vector2 leftUp;
    private Vector2 rightDown;
    private Texture texture;


    public Vector2 getLeftUp() {
        return leftUp;
    }

    public Vector2 getRightDown() {
        return rightDown;
    }

    public Field(Texture texture,float width,float height,Vector2 leftUp, Vector2 rightDown) {

        this.leftUp = leftUp;
        this.rightDown = rightDown;
        this.texture = texture;

        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setOrigin(width, height);
        sprite.setPosition(leftUp.x, leftUp.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(leftUp.x,leftUp.y);
        sprite.draw(batch);
    }

    @Override
    public List<GameObject> getparts() {
        return null;
    }
}
