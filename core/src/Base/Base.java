package Base;
import static Constants.constants.*;
import static com.badlogic.gdx.math.MathUtils.ceil;
import static com.badlogic.gdx.math.MathUtils.floor;
import static java.lang.Math.max;
import static java.lang.Math.min;

import Screens.BaseScreen;
import Tiles.Building1;
import Tiles.Building2;
import Tiles.Tile;
import com.badlogic.gdx.math.Vector2;
import Screens.Main;


public class Base {
    Tile[][] tiles;
    Main game;
    BaseScreen baseScreen;

    public Base(int width, int height, Main _game, BaseScreen _baseScreen){
        game = _game;
        tiles = new Tile[height][width];
        baseScreen = _baseScreen;
        System.out.println("height:" + height + "width:" + width);

        for(int y=0;y<tiles.length;y++){
            for(int x=0;x<tiles[0].length;x++){
                Vector2 temp=new Vector2();
                temp.set(x* getTileSize(),y* getTileSize());
                createTile(temp,new Tile(temp, game));
            }
        }
        Vector2 v = new Vector2(2* getTileSize(),2* getTileSize());
        createTile(v,new Building1(v,game));
        Vector2 v2 = new Vector2(2* getTileSize(),3* getTileSize());
        createTile(v2,new Building2(v2,game));
        System.out.println(tiles[3][3].placeable);
    }

    public void render(){
        int minX = ceil(game.camera.viewportWidth / getTileSize() / 2);
        int maxX = floor(getWIDTH() - (game.camera.viewportWidth / 2 / getTileSize()));
        int minY = ceil(game.camera.viewportHeight / getTileSize() / 2);
        int maxY = floor(getHEIGHT() - (game.camera.viewportHeight / 2 / getTileSize()));
        int currentPosX = floor(game.player.player_pos[0] / getTileSize());
        int currentPosY = floor(game.player.player_pos[1] / getTileSize());
        if (currentPosX < minX){
            currentPosX = minX;
        }else if(currentPosX > maxX){
            currentPosX = maxX;
        }
        if (currentPosY < minY){
            currentPosY = minY;
        }else if(currentPosY > maxY){
            currentPosY = maxY;
        }
        int renderDistanceX = ceil(game.camera.viewportWidth / getTileSize() / 2) + 1;
        int renderDistanceY = ceil(game.camera.viewportHeight / getTileSize() / 2) + 1;
        for(int y = max(currentPosY - renderDistanceY,0); y < min(currentPosY + renderDistanceY,getHEIGHT()); y++)
        {

            for(int x = max(currentPosX - renderDistanceX,0); x < min(currentPosX + renderDistanceX,getWIDTH()); x++)
            {
                tiles[y][x].render();
            }
        }
    }


    public void createTile(Vector2 position, Tile tile)
    {
        for(int y = 0;y < tile.placeMask.length;y++)
        {
            for (int x = 0; x < tile.placeMask[0].length; x++)
            {
                if (tile.placeMask[y][x] &&
                tiles[(int) (position.y / getTileSize()) + y][(int) (position.x / getTileSize()) + x] != null &&
                !tiles[(int) (position.y / getTileSize()) + y][(int) (position.x / getTileSize()) + x].placeable)
                {
                    return;
                }
            }
        }
        for(int y = 0;y < tile.placeMask.length;y++)
        {
            for(int x = 0;x < tile.placeMask[0].length;x++)
            {
                if (tile.placeMask[y][x]){
                    tiles[(int)(position.y/ getTileSize())+y][(int)(position.x/ getTileSize())+x] = tile;
                }
            }
        }
    }
}
