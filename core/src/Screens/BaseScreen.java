package Screens;

import Base.Base;
import Tiles.Building1;
import Tiles.Building2;
import Tiles.Tile;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static Constants.constants.*;
import static com.badlogic.gdx.math.MathUtils.floor;
import static java.lang.System.currentTimeMillis;

public class BaseScreen extends ScreenAdapter {
    Main game;
    Base base;
    Skin skin;
    Stage stage;
    boolean moveScreen= false;
    private boolean building;
    Tile ghostRender;


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
        InputAdapter inputAdapter = new InputAdapter(){
            public boolean keyDown(int keyCode){
                if(keyCode== Input.Keys.ESCAPE){
                    game.setScreen(game.mainScreen);
                    return true;
                }else if(keyCode== Input.Keys.NUM_1){
                    building = true;
                    Vector3 position3 = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                    game.camera.unproject(position3);
                    ghostRender = new Building1(new Vector2(position3.x - position3.x % getTileSize(),position3.y - position3.y % getTileSize()),game);
                    return true;
                }
                else if(keyCode== Input.Keys.NUM_2){
                    building = true;
                    Vector3 position3 = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                    game.camera.unproject(position3);
                    ghostRender = new Building2(new Vector2(position3.x - position3.x % getTileSize(),position3.y - position3.y % getTileSize()),game);
                    return true;
                }
                return false;
            }
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                System.out.println("false");
                if(building){
                    base.createTile(ghostRender.position,ghostRender);
                    ghostRender=null;
                    building = false;
                    return true;
                }
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if(building){
                    Vector3 position3 = new Vector3(screenX,screenY,0);
                    game.camera.unproject(position3);
                    ghostRender.setPosition(position3.x - position3.x % getTileSize(),position3.y - position3.y % getTileSize());
                    return true;
                }
                return false;
            }
        };
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputAdapter);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        skin.dispose();
        stage.dispose();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

}
