package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.contoller.GameContoller;
import com.mygdx.game.viewer.GameScreen;

public class Feed extends GameObject {
    private float speed = 10f;
    private float radious;
    private Vector2 direction;
    private static final float distance = 1f;
    private final Vector2 start;


    public Feed(Texture texture, float x, float y, float radious, Vector2 direction) {
        super(texture, x, y, radious * 2, radious * 2, radious, null);
        this.radious = radious;
        this.direction = direction;
        this.start = new Vector2(x, y);

        GameContoller.getGameObjects().add(this);
    }

    @Override
    public String getType() {
        return "feed";
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        handle();
    }

    private void handle() {

        if (speed != 0) {
            float d = start.dst(bounds.getX(), bounds.getY());
            if (d > distance) {
                speed = 0;
            } else {
                bounds.setPosition(bounds.getX() + direction.x * GameScreen.deltaCff * speed,
                        bounds.getY() + direction.y * GameScreen.deltaCff * speed);
            }
        }
    }
}
