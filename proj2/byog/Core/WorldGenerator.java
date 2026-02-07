package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {

    private static final int width=50;
    private static final int height=50;
    private static TETile[][] world=new TETile[width][height];
    private static int SEED=0;
    private static Random RANDOM=new Random(SEED);

    private static void Nothing_world(){
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                world[i][j]=Tileset.NOTHING;
            }
        }
    }

    private static Room random_room(){
        int x=RANDOM.nextInt(width-7);
        int y=RANDOM.nextInt(height-7);
        int w=RANDOM.nextInt(7)+3;
        int h=RANDOM.nextInt(7)+3;
        int centerX=x+w/2;
        int centerY=y+h/2;
        return new Room(x,y,w,h);
    }
    private static void generate_wall(Room room){
        for(int i=room.x;i<room.x+room.w;i++){
            for(int j=room.y;j<room.y+room.h;j++){
                world[i][j]=Tileset.WALL;
            }
        }
    }

    private static void generate_floor(Room room){
        for(int i=room.x+1;i<room.x+room.w-1;i++){
            for(int j=room.y+1;j<room.y+room.h-1;j++){
                world[i][j]=Tileset.FLOOR;
            }
        }
    }
    private static List<Room> rooms = new ArrayList<>();

    private static void generate_random_room(int count) {

        for (int i = 0; i < count; i++) {
            int attempts = 0;
            boolean roomAdded = false;

            while (attempts < 100 && !roomAdded) {
                Room newRoom = random_room();
                boolean noOverlap = true; // 假设没有重叠

                // 根据你的overlaps方法逻辑
                // 如果newRoom.overlaps(existingRoom)返回true，表示"不重叠"
                // 我们需要检查是否与所有已有房间都不重叠
                for (Room existingRoom : rooms) {
                    // 这里的关键：如果overlaps返回true，表示"不重叠"
                    // 但我们需要检查是否有重叠，所以应该取反逻辑
                    if (!newRoom.overlaps(existingRoom)) {
                        // 如果overlaps返回false，说明有重叠
                        noOverlap = false;
                        break;
                    }
                }
                if (noOverlap || rooms.isEmpty()) {
                    rooms.add(newRoom);
                    roomAdded = true;
                    System.out.println("成功添加房间 " + (rooms.size()) +
                            ": (" + newRoom.x + "," + newRoom.y +
                            ") 大小: " + newRoom.w + "x" + newRoom.h);
                }
                attempts++;
            }
            if (!roomAdded) {
                System.out.println("无法生成第 " + (i+1) + " 个房间，跳过");
            }
        }

        // 绘制所有房间
        for (Room room : rooms) {
            generate_wall(room);
            generate_floor(room);
        }

        System.out.println("总共生成了 " + rooms.size() + " 个房间");
    }

    private void generate_corride(){

    }


    public static void main(String[] args){
        TERenderer ter=new TERenderer();
        ter.initialize(width,height);
        Nothing_world();
        generate_random_room(10);

        ter.renderFrame(world);
    }
}