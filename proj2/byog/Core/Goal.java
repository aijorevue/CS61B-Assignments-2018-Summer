package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Goal {
    private int x;
    private int y;
    public Goal(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void theGoal(TETile[][] world){
        world[x][y]= Tileset.LOCKED_DOOR;
    }
    public int getGoalX(){
        return x;
    }
    public int getGoalY(){
        return y;
    }
}
