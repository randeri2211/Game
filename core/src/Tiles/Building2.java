package Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import Screens.Main;


public class Building2 extends Tile{
    public Building2(Vector2 _position, Main _game) {
        super(_position, _game);
        placeable = false;
        color = Color.YELLOW;
        placeMask = new boolean[][]{{false, true,false},
                                    {true, true,true},
                                    {false, true,false}};
    }
}
