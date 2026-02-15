package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {
    private int x;
    private int y;
    public Player(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void drawLocation(TETile[][] world){
        world[x][y]=Tileset.MOUNTAIN;
    }

    public void ahead(TETile[][] world){
        if(world[x][y+1]!=Tileset.WALL) {
            world[x][y] = Tileset.FLOOR;
            this.y += 1;
            drawLocation(world);
        }
    }
    public void back(TETile[][] world){
        if(world[x][y-1]!=Tileset.WALL) {
            world[x][y] = Tileset.FLOOR;
            this.y -= 1;
            drawLocation(world);
        }
    }
    public void left(TETile[][] world){
        if(world[x-1][y]!=Tileset.WALL) {
            world[x][y] = Tileset.FLOOR;
            this.x -= 1;
            drawLocation(world);
        }
    }
    public void right(TETile[][] world){
        if(world[x+1][y]!=Tileset.WALL) {
            world[x][y] = Tileset.FLOOR;
            this.x += 1;
            drawLocation(world);
        }
    }

    public int getPlayerX(){
        return x;
    }
    public int getPlayerY(){
        return y;
    }

}
