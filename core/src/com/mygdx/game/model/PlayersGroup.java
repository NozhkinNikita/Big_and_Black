package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class PlayersGroup implements Drawble {
    private List<GameObject> parts = new CopyOnWriteArrayList<>();

    public PlayersGroup(Texture texture, float x, float y, float radious) {
        Player p = new Player(texture, x, y, radious, this);
        parts.add(p);
    }

    @Override
    public void draw(SpriteBatch batch) {
        parts.forEach(gameObject -> gameObject.draw(batch));
    }


    @Override
    public List<GameObject> getparts() {
        return parts;
    }

    public void split(Player player, Vector2 direction) {

        float mass = player.radious * player.radious * MathUtils.PI;
        float newMass = mass / 2;
        float newRadious = (float) Math.sqrt(newMass / Math.PI);
        player.setRadious(newRadious);
        Player p = new Player(player.getSprite().getTexture(),
                direction.x, direction.y, newRadious, this);

        parts.add(p);
    }
}
