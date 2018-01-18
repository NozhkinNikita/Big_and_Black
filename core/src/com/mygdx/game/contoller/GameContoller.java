package com.mygdx.game.contoller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameContoller {

    private static List<Drawble> gameObjects = new CopyOnWriteArrayList<>();
    private static Field field;


    public void setGameObjects(List<Drawble> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public static List<Drawble> getGameObjects() {
        return gameObjects;
    }

    public GameContoller() {
    }

    public static void setField(Field field) {
        GameContoller.field = field;
    }

    public static Field getField() {

        return field;
    }

    public void init() {

        initField();
        initPlayer();
        initRandomFood();
        initRandomVirus();
    }

    private void initPlayer() {

        int radius = 1000;
        int borderWidth = 10;
        Pixmap pixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(new Color(1, 0, 0, 1));
        pixmap.fillCircle(radius, radius, radius);
//        pixmap.setColor(new Color(1, 0, 0, 0.6f));
//        pixmap.fillCircle(radius, radius, radius - borderWidth);

        pixmap.setColor(new Color(0.33f, 0.72f, 0.99f, 0.95f));
        pixmap.fillCircle(radius - radius / 2, radius - radius / 2, radius / 4);

        pixmap.setColor(new Color(0.33f, 0.72f, 0.99f, 0.95f));
        pixmap.fillCircle(radius + radius / 2, radius - radius / 2, radius / 4);

        pixmap.setColor(new Color(1f, 0.72f, 0.99f, 0.95f));
        pixmap.fillRectangle(radius - radius / 2, radius, radius, radius / 2);

        Texture background = new Texture(pixmap);
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        pixmap.dispose();

        PlayersGroup player1 = new PlayersGroup(background, -1.9f, 1.9f, 1f);
        gameObjects.add(player1);
    }

    private void initRandomFood() {
        int radius = 10;
        Pixmap pixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        for (int i = 0; i < 100; i++) {
            pixmap.setColor(new Color(MathUtils.random(1f), MathUtils.random(1f),
                    MathUtils.random(1f), MathUtils.random(1f)));
            pixmap.fillCircle(radius, radius, radius);
            GameObject food1 = new Food(new Texture(pixmap), MathUtils.random(-10f, 10f),
                    MathUtils.random(-10f, 10f), 0.1f);
            gameObjects.add(food1);
        }
        pixmap.dispose();
    }

    private void initField(){

        Texture kirillPhone = new Texture(Gdx.files.internal("ph.jpg"));
        kirillPhone.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        field = new Field(kirillPhone, 40, 40, new Vector2(-20f, -20f), new Vector2(20f, 20f));
    }

    private void initRandomVirus() {
        int radius = 10;
        Pixmap pixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(new Color(0.15f, 0.83f, 0, 1));
        pixmap.fillCircle(10, 10, 10);
        Texture virusTexture = new Texture(pixmap);

        for (int i = 1; i <= 20; i++) {
            GameObject virus = new Virus(virusTexture, MathUtils.random(-10f, 10f),
                    MathUtils.random(-10f, 10f), 0.7f);
            gameObjects.add(virus);

        }

        pixmap.dispose();
    }

}
