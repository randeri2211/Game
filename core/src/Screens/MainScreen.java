package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainScreen extends ScreenAdapter {
    Main game;

    private Skin skin;
    private Stage stage;
    private Table root;
    boolean moveScreen= false;

    public MainScreen(Main _game){
        game= _game;
    }

    @Override
    public void show(){
        skin = new Skin(Gdx.files.internal("metal/skin/metal-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        TextButton textButton = new TextButton("Base", skin);
        textButton.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                moveScreen = true;
            }
        });
        root.add(textButton);
        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if(moveScreen){
            game.setScreen(game.baseScreen);
            moveScreen=false;
        }
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }
}
