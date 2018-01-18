package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.contoller.GameContoller;
import com.mygdx.game.contoller.PlayerController;
import com.mygdx.game.viewer.GameScreen;

import java.util.Iterator;

public class Player extends GameObject {

    private float speed = 1f;
    private PlayerController playerController;

    public PlayersGroup getPlayersGroup() {
        return playersGroup;
    }

    public Player(Texture texture, float x, float y, float radious, PlayersGroup playersGroup) {
        super(texture, x, y, radious * 2, radious * 2, radious, playersGroup);
        playerController = new PlayerController(this, bounds, sprite, speed);
    }

    @Override
    public String getType() {
        return "player";
    }

    public void touchSky() {

        if (bounds.getX() < GameContoller.getField().getLeftUp().x) {
            bounds.setPosition(GameContoller.getField().getLeftUp().x, bounds.getY());
        }
        if (bounds.getX() > GameContoller.getField().getRightDown().x - sprite.getWidth()) {
            bounds.setPosition(GameContoller.getField().getRightDown().x - sprite.getWidth(), bounds.getY());
        }
        if (bounds.getY() < GameContoller.getField().getLeftUp().y) {
            bounds.setPosition(bounds.getX(), GameContoller.getField().getLeftUp().y);
        }
        if (bounds.getY() > GameContoller.getField().getRightDown().y - sprite.getHeight()) {
            bounds.setPosition(bounds.getX(), GameContoller.getField().getRightDown().y - sprite.getHeight());
        }

    }


    public void split(Vector2 direction) {

        playersGroup.split(this, direction);
    }

    public void touch(Vector2 defaultBound) {

        Iterator<Drawble> j = GameContoller.getGameObjects().iterator();
        while (j.hasNext()) {
            Iterator<GameObject> i = j.next().getparts().iterator();
            while (i.hasNext()) {
                GameObject gameObject = i.next();
                if (gameObject != this) {
                    Polygon bound = gameObject.getBounds();
                    float radious = gameObject.getRadious();
                    float d = Vector2.dst(bound.getX() + gameObject.getWidth() / 2,
                            bound.getY() + gameObject.getWidth() / 2,

                            this.getBounds().getX() + this.getWidth() / 2,
                            this.getBounds().getY() + this.getWidth() / 2

                    );

                    if (playersGroup != gameObject.getPlayersGroup()) {
                        if (sprite.getWidth() / 2 > d) {

                            switch (gameObject.getType()) {
                                case "feed": {

                                }
                                case "food": {

                                    float currentMass = this.radious * this.radious * MathUtils.PI;
                                    float foodMass = gameObject.getRadious() * gameObject.getRadious() * MathUtils.PI;
                                    currentMass += foodMass;
                                    this.radious = (float) Math.sqrt(currentMass / MathUtils.PI);
                                    sprite.setSize(this.radious * 2, this.radious * 2);
                                    GameContoller.getGameObjects().remove(gameObject);

                                    break;
                                }
                                case "virus": {
                                    if (gameObject.radious < this.radious) {
                                            GameContoller.getGameObjects().remove(gameObject);

                                        this.collapse();
                                    }
                                    break;
                                }
                            }

                        }
                    } else {

                        if ((sprite.getWidth() / 2) + (gameObject.getWidth() / 2) > d) {
                            bounds.setPosition(defaultBound.x, defaultBound.y);
                        }
                    }


                }
            }
        }

    }

    public void collapse() {

    }

    protected void setRadious(float radious) {
        this.radious = radious;
        sprite.setSize(this.radious * 2, this.radious * 2);
    }

    public void feed(Vector2 start, Vector2 direction) {

        Feed feed = new Feed(sprite.getTexture(), start.x, start.y, 0.1f, direction);

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        playerController.handle();
    }

}
