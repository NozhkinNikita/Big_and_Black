package com.mygdx.game.contoller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.model.Player;
import com.mygdx.game.viewer.GameScreen;

public class PlayerController {

    float eps = 0.1f;
    private float playerSpeed, speedVelocity, speedMax = 10f;
    private Polygon playerBounds;
    private Sprite sprite;
    private Player current;

    public PlayerController(Player current, Polygon playerBounds, Sprite sprite, float speed) {
        this.current = current;
        this.playerBounds = playerBounds;
        this.sprite = sprite;
        this.speedVelocity = speed;
    }


    public void handle() {

        Vector3 mouseVector = getMousePosInGameWorld();
        Vector2 direction = new Vector2();

        direction.x = mouseVector.x - (playerBounds.getX() + sprite.getWidth() / 2);
        direction.y = mouseVector.y - (playerBounds.getY() + sprite.getHeight() / 2);
        float d = (float) Math.sqrt(direction.x * direction.x + direction.y * direction.y);
        direction.x = direction.x / d;
        direction.y = direction.y / d;

        Vector2 defaultBound = new Vector2(playerBounds.getX(), playerBounds.getY());
        if (d > eps) {
            playerBounds.setPosition(playerBounds.getX() + direction.x * GameScreen.deltaCff * speedVelocity,
                    playerBounds.getY() + direction.y * GameScreen.deltaCff * speedVelocity);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            current.split(new Vector2(playerBounds.getX() + direction.x * current.getRadious() * 1.5f,
                    playerBounds.getY() + direction.y * current.getRadious() * 1.5f));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            current.feed(new Vector2(new Vector2(playerBounds.getX()+sprite.getWidth()/2 + direction.x * current.getRadious() * 1.3f,
                            playerBounds.getY()+sprite.getWidth()/2 + direction.y * current.getRadious() * 1.3f)),
                    direction

                    );
        }

        current.touchSky();
        current.touch(defaultBound);

       // if (Gdx.input.isKeyPressed(Input.Keys.C)) {
//            sprite.setSize(sprite.getWidth() + 0.1f, sprite.getHeight() + 0.1f);
//        }

    }

    Vector3 getMousePosInGameWorld() {
        return GameScreen.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    private void downSpeed() {
        if (playerSpeed > speedVelocity * GameScreen.deltaCff)
            playerSpeed -= speedVelocity * GameScreen.deltaCff;
        else if (playerSpeed < -speedVelocity * GameScreen.deltaCff)
            playerSpeed += speedVelocity * GameScreen.deltaCff;
        else
            playerSpeed = 0f;
    }

    private float sliceSpeed() {
        if (playerSpeed > speedMax)
            return speedMax;
        if (playerSpeed < -speedMax)
            return -speedMax;
        return playerSpeed;
    }


}
