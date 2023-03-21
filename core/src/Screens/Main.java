package Screens;
import Monsters.MonstersCollection;
import Player.PlayerAvatar;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Main extends Game {

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public OrthographicCamera camera;
	public BitmapFont font;
	public MainScreen mainScreen;
	public BaseScreen baseScreen;
	public MonstersCollection monsters;
	public PlayerAvatar player;

	@Override
	public void create () {
		mainScreen = new MainScreen(this);
		baseScreen = new BaseScreen(this);
		monsters = new MonstersCollection(this);
		player= new PlayerAvatar(this);
		System.out.println("Width: " + Gdx.graphics.getWidth() + "\nHeight: " + Gdx.graphics.getHeight());
		batch= new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font= new BitmapFont();
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set((int)(Gdx.graphics.getWidth()/4),(int)(Gdx.graphics.getHeight()/4),0);
		camera.update();
		setScreen(mainScreen);
		shapeRenderer.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
