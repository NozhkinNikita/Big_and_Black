package com.mygdx.game.viewer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.contoller.GameContoller;
import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Player;



public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture background;
    private static GameContoller gameContoller;
    private static OrthographicCamera camera;
    private BitmapFont font = new BitmapFont();

    public static float deltaCff;

    public static OrthographicCamera getCamera() {
        return camera;
    }

    private void setCameraPositionToPlayer(){

        Polygon polygon = gameContoller.getGameObjects().get(0).getparts().get(0).getBounds();
        Float width = gameContoller.getGameObjects().get(0).getparts().get(0).getWidth();
        camera.position.x = polygon.getX() + width / 2f;
        camera.position.y = polygon.getY() + width / 2f;

    }

    public GameScreen(GameContoller gameContoller) {
        this.gameContoller = gameContoller;
    }

    @Override
    public void show() {

        gameContoller.init();
        batch = new SpriteBatch();
        font.getData().setScale(0.1f);

    }

    @Override
    public void render(float delta) {

        setCameraPositionToPlayer();
        camera.update();
        Gdx.gl.glClearColor(1, 1f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaCff = delta;
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        GameContoller.getField().draw(batch);
        gameContoller.getGameObjects().forEach((gameobject) -> gameobject.draw(batch));
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        camera.zoom = 1f;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
