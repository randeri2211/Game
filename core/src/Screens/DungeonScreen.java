package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class DungeonScreen extends ScreenAdapter {
    Main game;

    public DungeonScreen(Main _game){
        game=_game;
    }
    @Override
    public void show(){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
