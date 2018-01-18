package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameObject implements Drawble {

    Polygon bounds;
    Sprite sprite;
    protected float radious;
    protected PlayersGroup playersGroup;

    GameObject(Texture texture, float x, float y, float width, float height,float radious,PlayersGroup playersGroup) {
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setOrigin(width / 2f, height / 2f);
        sprite.setPosition(x, y);

        bounds = new Polygon(new float[]{0f, 0f, width, 0f, width, height, 0f, height});
        bounds.setPosition(x, y);
        sprite.setOrigin(width / 2f, height / 2f);

        this.radious = radious;
        this.playersGroup = playersGroup;

    }

    public PlayersGroup getPlayersGroup() {
        return playersGroup;
    }

    public String getType(){
        return null;
    }


    @Override
    public void draw(SpriteBatch batch){
        sprite.setPosition(bounds.getX(), bounds.getY());
        sprite.setRotation(bounds.getRotation());
        sprite.draw(batch);
    }

    public Polygon getBounds() {
        return bounds;
    }

    public float getRadious() {
        return radious;
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    @Override
    public List<GameObject> getparts() {
       List<GameObject> list= new CopyOnWriteArrayList<>();
        list.add(this);
        return list;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
