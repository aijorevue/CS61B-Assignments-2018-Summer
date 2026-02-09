package byog.Core;

public class Room {
    private int x;
    private int y;
    private int w;
    private int h;
    private int centerX;
    private int centerY;

    public Room() {
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.centerX = x + w / 2;
        this.centerY = y + h / 2;
    }

    public Room(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.centerX = x + w / 2;
        this.centerY = y + h / 2;
    }

    public boolean overlaps(Room other) {
        return x + w < other.x || other.x + other.w < x || y + h < other.y || other.y + other.h < y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public int getW(){
        return w;
    }
    public int getH(){
        return h;
    }
    public int getCenterX(){
        return centerX;
    }
    public int getCenterY(){
        return centerY;
    }
}
