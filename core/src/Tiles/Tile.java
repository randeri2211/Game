package Tiles;

import static Constants.constants.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import Screens.Main;

public class Tile {
    public Vector2 position;
    Color color;
    Main game;
    public boolean placeable;
    public boolean[][] placeMask;

    public Tile(Vector2 _position, Main _game){
        position = _position;
        game = _game;
        placeable = true;
        color = Color.WHITE;
        placeMask = new boolean[][]{{true}};
    }

    public void render(){
        for(int y = 0;y < placeMask.length;y++){
            for(int x = 0;x < placeMask[0].length;x++){
                if(placeMask[y][x]){
                    game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    game.shapeRenderer.setColor(color);
                    game.shapeRenderer.rect(position.x+x* getTileSize(),position.y+y* getTileSize(), getTileSize(), getTileSize());
                    game.shapeRenderer.end();
                    game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                    game.shapeRenderer.setColor(Color.BLACK);
                    game.shapeRenderer.line(position.x+x* getTileSize(),position.y+y* getTileSize(),position.x+x* getTileSize()+ getTileSize(),position.y+y* getTileSize());
                    game.shapeRenderer.line(position.x+x* getTileSize(),position.y+y* getTileSize(),position.x+x* getTileSize(),position.y+y* getTileSize()+ getTileSize());
                    game.shapeRenderer.line(position.x+x* getTileSize()+ getTileSize(),position.y+y* getTileSize(),position.x+x* getTileSize()+ getTileSize(),position.y+y* getTileSize()+ getTileSize());
                    game.shapeRenderer.line(position.x+x* getTileSize(),position.y+y* getTileSize()+ getTileSize(),position.x+x* getTileSize()+ getTileSize(),position.y+y* getTileSize()+ getTileSize());
                    game.shapeRenderer.end();
                }
            }
        }

    }

    public void setPosition(float x,float y){
        position = new Vector2(x,y);
    }

}
