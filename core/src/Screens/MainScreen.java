package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;


public class MainScreen extends ScreenAdapter {
    Main game;

    public MainScreen(Main _game){
        game= _game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            public boolean keyDown(int keyCode){
                if(keyCode== Input.Keys.NUM_1){
                    game.setScreen(game.baseScreen);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.getData().setScale(3);
        game.font.draw(game.batch,"Click 1 For BaseScreen",Gdx.graphics.getWidth()*.25f,Gdx.graphics.getHeight()*.5f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
