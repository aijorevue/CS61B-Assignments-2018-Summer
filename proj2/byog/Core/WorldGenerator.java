package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import static byog.Core.Prime.calculatePrime;

public class WorldGenerator {

    private int width;
    private int height;
    private long SEED;
    private TETile[][] world;
    private Random RANDOM;



    public WorldGenerator(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        SEED = seed;
        world = new TETile[width][height];
        RANDOM = new Random(SEED);

    }


    private void nothingWorld() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private Room randomRoom() {
        int x = RANDOM.nextInt(width - 12)+1;
        int y = RANDOM.nextInt(height - 12)+1;
        int w = RANDOM.nextInt(7) + 3;
        int h = RANDOM.nextInt(7) + 3;
        int centerX = x + w / 2;
        int centerY = y + h / 2;
        return new Room(x, y, w, h);
    }

    private void generateWall(Room room) {
        for (int i = room.getX(); i < room.getX() + room.getW(); i++) {
            for (int j = room.getY(); j < room.getY() + room.getH(); j++) {
                world[i][j] = Tileset.WALL;
            }
        }
    }

    private void generateFloor(Room room) {
        for (int i = room.getX() + 1; i < room.getX() + room.getW() - 1; i++) {
            for (int j = room.getY() + 1; j < room.getY() + room.getH() - 1; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    private List<Room> rooms = new ArrayList<>();

    private void generateRandomRoom(int count) {

        for (int i = 0; i < count; i++) {
            int attempts = 0;
            boolean roomAdded = false;

            while (attempts < 100 && !roomAdded) {
                Room newRoom = randomRoom();
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
                    //System.out.println("成功添加房间 " + (rooms.size()) +
                    //        ": (" + newRoom.x + "," + newRoom.y +
                    //        ") 大小: " + newRoom.w + "x" + newRoom.h);
                }
                attempts++;
            }

        }

        // 绘制所有房间
        for (Room room : rooms) {
            generateWall(room);
            generateFloor(room);
        }

        //System.out.println("总共生成了 " + rooms.size() + " 个房间");
    }


    private void drawLShapedCorridor(Room r1, Room r2) {
        int x1 = r1.getCenterX();
        int y1 = r1.getCenterY();
        int x2 = r2.getCenterX();
        int y2 = r2.getCenterY();

        // 随机决定是先横再纵，还是先纵再横 (增加地图多样性)
        if (RANDOM.nextBoolean()) {
            drawHorizontalLine(x1, x2, y1);
            drawVerticalLine(y1, y2, x2);
        } else {
            drawVerticalLine(y1, y2, x1);
            drawHorizontalLine(x1, x2, y2);
        }
    }

    // 简化后的横向线：只管铺地板
    private void drawHorizontalLine(int xStart, int xEnd, int y) {
        for (int x = Math.min(xStart, xEnd); x <= Math.max(xStart, xEnd); x++) {
            world[x][y] = Tileset.FLOOR;
        }
    }

    // 简化后的纵向线：只管铺地板
    private void drawVerticalLine(int yStart, int yEnd, int x) {
        for (int y = Math.min(yStart, yEnd); y <= Math.max(yStart, yEnd); y++) {
            world[x][y] = Tileset.FLOOR;
        }
    }

    private void fillWalls() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // 如果当前位置是空的，检查它是否应该成为墙
                if (world[x][y] == Tileset.NOTHING) {
                    if (hasNeighborFloor(x, y)) {
                        world[x][y] = Tileset.WALL;
                    }
                }
            }
        }
    }

    // 辅助方法：检查周围 8 个格子是否有地板
    private boolean hasNeighborFloor(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                // 确保不越界
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (world[nx][ny] == Tileset.FLOOR) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void randomCorrider(List<Room> roomsList) {
        for (int i = 0; i < roomsList.size(); i++) {
            int count = RANDOM.nextInt(3);
            // if(count==1){
            //     List<Edge> mstEdges=calculate_prime(rooms);
            //      for(Edge ii:mstEdges){
            //          drawLShapedCorridor(rooms.get(ii.from),rooms.get(ii.to));
            //     }
            // }
            for (int j = 0; j < count; j++) {
                int linkRoom = RANDOM.nextInt(roomsList.size());
                drawLShapedCorridor(roomsList.get(i), roomsList.get(linkRoom));
            }
        }
        List<Edge> mstEdges = calculatePrime(rooms);
        for (Edge ii : mstEdges) {
            drawLShapedCorridor(rooms.get(ii.from), rooms.get(ii.to));
        }
    }

    public TETile[][] generate() {
        nothingWorld();
        generateRandomRoom(10);
        randomCorrider(rooms);
        fillWalls();
        return world;
    }
}
