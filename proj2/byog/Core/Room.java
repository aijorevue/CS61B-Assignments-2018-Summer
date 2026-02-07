package byog.Core;

public class Room {
    public int x;
    public int y;
    public int w;
    public int h;
    public int centerX;
    public int centerY;

    public Room(){
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.centerX = x + w / 2;
        this.centerY = y + h / 2;
    }
    public Room(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.centerX = x + w / 2;
        this.centerY = y + h / 2;
    }

    public boolean overlaps(Room other){
        return x+w<other.x||other.x+other.w<x||y+h<other.y||other.y+other.h<y;
    }


}
