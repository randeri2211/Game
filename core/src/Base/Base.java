package Base;
import static Constants.constants.*;
import Tiles.Building1;
import Tiles.Building2;
import Tiles.Tile;
import com.badlogic.gdx.math.Vector2;
import Screens.Main;


public class Base {
    Tile[][] tiles;
    Main game;
    public Base(int width, int height, Main _game){
        game = _game;
        tiles = new Tile[height][width];
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
        Vector2 v2 = new Vector2(3* getTileSize(),2* getTileSize());
        createTile(v2,new Building2(v2,game));
        System.out.println(tiles[3][3].placeable);
    }

    public void render(){
        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                tiles[i][j].render();
            }
        }
    }

    public boolean createTile(Vector2 position,Tile tile)
    {
        for(int y = 0;y < tile.placeMask.length;y++)
        {
            for (int x = 0; x < tile.placeMask[0].length; x++)
            {
                if (tile.placeMask[y][x] &&
                tiles[(int) (position.y / getTileSize()) + y][(int) (position.x / getTileSize()) + x] != null &&
                !tiles[(int) (position.y / getTileSize()) + y][(int) (position.x / getTileSize()) + x].placeable)
                {
                    return false;
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
        return true;
    }
}
