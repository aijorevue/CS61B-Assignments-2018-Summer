package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static TETile[][] world;
    private static TERenderer ter=new TERenderer();

    public static void addHexagon(int x,int y,int s,TETile tile){
        if(s<2){
            return ;
        }

        for(int row=0;row<2*s;row++){
            int rowWidth=calculateRowWidth(s,row);
            int rowOffset=calculateRowOffset(s,row);

            for(int i=0;i<rowWidth;i++){
                world[x+rowOffset+i][y+row]=tile;
            }
        }
    }

    private static int calculateRowWidth(int s,int row){
        if(row<s){
            return s+2*row;
        }
        else{
            return s+2*(2*s-1-row);
        }
    }

    private static int calculateRowOffset(int s,int row){
        if(row<s){
            return -(row);
        }
        else{
            return -(2*s-1-row);
        }
    }

    public static void main(String [] args){
        int width=50;
        int height=50;
        ter.initialize(width,height);

        world=new TETile[width][height];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                world[x][y]=Tileset.NOTHING;
            }
        }

        addHexagon(10,10,3,Tileset.FLOOR);

        ter.renderFrame(world);
    }

}
