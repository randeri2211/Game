package Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import Screens.Main;

public class Building1 extends Tile {
    public Building1(Vector2 _position, Main _game) {
        super(_position, _game);
        placeable = false;
        color = Color.GREEN;
        placeMask = new boolean[][]{{true, true},
                                    {true, false}};
    }
}
