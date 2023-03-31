package Screens;

import Base.Base;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static Constants.constants.*;
import static java.lang.System.currentTimeMillis;

public class BaseScreen extends ScreenAdapter {
    Main game;
    Base base;
    Skin skin;
    Stage stage;
    Table table;
    public boolean building;


    public BaseScreen(Main _game){
        this.game=_game;
        base = new Base(getWIDTH(),getHEIGHT(),game,this);
        building = false;
    }

    public void show(){
        skin=new Skin(Gdx.files.internal("metal/skin/metal-ui.json"));
        stage = new Stage(new ScreenViewport());
        TextButton textButton = new TextButton("Dungeon", skin);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("true");
                game.setScreen(game.dungeonScreen);
                System.out.println(x + "," + y);
            }
        });
        stage.addActor(textButton);
        game.player.player_pos[0] = Gdx.graphics.getWidth() >> 1;
        game.player.player_pos[1] = Gdx.graphics.getHeight() >> 1;
        game.camera.position.set((float)(Gdx.graphics.getWidth()/2),(float)(Gdx.graphics.getHeight()/2),0);
        game.camera.update();
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        Gdx.input.setInputProcessor(new InputAdapter(){
            public boolean keyDown(int keyCode){
                if(keyCode== Input.Keys.ESCAPE){
                    game.setScreen(game.mainScreen);
                }else if(keyCode== Input.Keys.NUM_1){
                    building = true;
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                building = false;
                return super.touchUp(screenX, screenY, pointer, button);
            }

//            @Override
//            public boolean mouseMoved(int screenX, int screenY) {
//                if(building){
//                    b
//                }
//                return super.mouseMoved(screenX, screenY);
//            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.frames+=1;
        if (currentTimeMillis() - game.time >= 1000){
            System.out.println(currentTimeMillis() - game.time);
            game.time = currentTimeMillis();
            System.out.println("frames:" + game.frames);
            game.frames = 0;
        }
        base.render();
        if(ghostRender != null){
            ghostRender.render();
        }
        game.player.render(delta);
        stage.act();
        stage.draw();
        if(moveScreen){
            System.out.println("movescreen");
            moveScreen=false;
            game.setScreen(game.dungeonScreen);
        }
    }

    @Override
    public void hide(){

    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

}
