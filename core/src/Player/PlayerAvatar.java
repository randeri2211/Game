package Player;


import Screens.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static Constants.constants.*;
import static Constants.constants.getTileSize;

public class PlayerAvatar {
    Main game;
    public float[] player_pos = {(Gdx.graphics.getWidth() >> 1), (Gdx.graphics.getHeight() >> 1)};

    public PlayerAvatar(Main _game){
        game= _game;
    }

    public void show(){
        player_pos[0] = Gdx.graphics.getWidth() >> 1;
        player_pos[1] = Gdx.graphics.getHeight() >> 1;
        game.camera.position.set((float)(Gdx.graphics.getWidth()/2),(float)(Gdx.graphics.getHeight()/2),0);
        game.camera.update();
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
    }


    public void render(float delta) {
        float distance = 5*getTileSize()*delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (player_pos[1] + distance < getHEIGHT() * getTileSize()) {
                player_pos[1] += distance;
            } else {
                player_pos[1] = getHEIGHT() * getTileSize();
            }

            if (game.camera.position.y + distance < getHEIGHT() * getTileSize() - game.camera.viewportHeight / 2 && player_pos[1] > game.camera.viewportHeight / 2) {
                game.camera.translate(0, distance);
            } else if (game.camera.position.y + distance >= getHEIGHT() * getTileSize() - game.camera.viewportHeight / 2) {
                game.camera.position.y = getHEIGHT() * getTileSize() - game.camera.viewportHeight / 2;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (player_pos[0] - distance > 0) {
                player_pos[0] -= distance;
            } else {
                player_pos[0] = 0;
            }

            if (game.camera.position.x - distance > game.camera.viewportWidth / 2 && player_pos[0] < getWIDTH() * getTileSize() - game.camera.viewportWidth / 2) {
                game.camera.translate(-distance, 0);
            } else if (game.camera.position.x - distance <= game.camera.viewportWidth / 2) {
                game.camera.position.x = game.camera.viewportWidth / 2;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (player_pos[1] - distance > 0) {
                player_pos[1] -= distance;
            } else {
                player_pos[1] = 0;
            }
            if (game.camera.position.y - distance > game.camera.viewportHeight / 2 && player_pos[1] < getHEIGHT() * getTileSize() - game.camera.viewportHeight / 2) {
                game.camera.translate(0, -distance);
            } else if (game.camera.position.y - distance <= game.camera.viewportHeight / 2) {
                game.camera.position.y = game.camera.viewportHeight / 2;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (player_pos[0] + distance < getWIDTH() * getTileSize()) {
                player_pos[0] += distance;
            } else {
                player_pos[0] = getWIDTH() * getTileSize();
            }

            if (game.camera.position.x + distance < getWIDTH() * getTileSize() - game.camera.viewportWidth / 2 && player_pos[0] > game.camera.viewportWidth / 2) {
                game.camera.translate(distance, 0);
            } else if (game.camera.position.x + distance >= getWIDTH() * getTileSize() - game.camera.viewportWidth / 2) {
                game.camera.position.x = getWIDTH() * getTileSize() - game.camera.viewportWidth / 2;
            }
        }
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(Color.RED);
        game.shapeRenderer.circle(player_pos[0],player_pos[1], getTileSize() >> 1);
        game.shapeRenderer.end();
        game.camera.update();
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
    }
}
