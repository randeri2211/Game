package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import static com.badlogic.gdx.math.MathUtils.random;

public class DungeonScreen extends ScreenAdapter {
    Main game;
    private Skin skin;
    private Stage stage;
    private Table root;
    boolean moveScreen= false;

    public DungeonScreen(Main _game){
        game=_game;
    }
    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            public boolean keyDown(int keyCode){
                if(keyCode== Input.Keys.ESCAPE){
                    game.setScreen(game.baseScreen);
                }
                return true;
            }
        });
        skin = new Skin(Gdx.files.internal("metal/skin/metal-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        for(int i=1;i<4;i++){
            for(int j=1;j<4;j++) {
                String index;
                index= "D"+j+"-"+i;
                TextButton textButton = new TextButton(index, skin);
                root.add(textButton).expand().padRight(random(10,80)).padLeft(random(10,80));
            }
            root.row();
        }

        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
