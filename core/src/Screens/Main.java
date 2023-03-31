package Screens;
import Monsters.MonstersCollection;
import Player.PlayerAvatar;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static java.lang.System.currentTimeMillis;


public class Main extends Game {

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public OrthographicCamera camera;
	public BitmapFont font;
	public MainScreen mainScreen;
	public BaseScreen baseScreen;
	public DungeonScreen dungeonScreen;
	public MonstersCollection monsters;
	public PlayerAvatar player;
	public int frames;
	public long time;

	@Override
	public void create () {
		mainScreen = new MainScreen(this);
		baseScreen = new BaseScreen(this);
		dungeonScreen = new DungeonScreen(this);
		monsters = new MonstersCollection(this);
		player= new PlayerAvatar(this);
		System.out.println("Width: " + Gdx.graphics.getWidth() + "\nHeight: " + Gdx.graphics.getHeight());
		batch= new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font= new BitmapFont();
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() >> 2, Gdx.graphics.getHeight() >> 2,0);
		camera.update();
		setScreen(mainScreen);
		shapeRenderer.setProjectionMatrix(camera.combined);
		frames = 0;
		time = currentTimeMillis();
		System.out.println("initial time: " + currentTimeMillis());
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
