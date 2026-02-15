package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public WorldGenerator world;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */

    public void playWithKeyboard() {
        StdDraw.enableDoubleBuffering();
        StringBuilder input = new StringBuilder();

        boolean save=false;
        while (!world.gameOver()) {
            if (StdDraw.hasNextKeyTyped()) {
                input.append(StdDraw.nextKeyTyped());
                char choice = Character.toUpperCase(input.charAt(input.length() - 1));
                if(save &&choice=='Q'){
                   save();
                   return;
                }
                switch (choice) {
                    case ':':
                        save=true;
                        break;
                    case 'W':
                        world.getPlayer().ahead(world.getWorld());
                        break;
                    case 'S':
                        world.getPlayer().back(world.getWorld());
                        break;
                    case 'A':
                        world.getPlayer().left(world.getWorld());
                        break;
                    case 'D':
                        world.getPlayer().right(world.getWorld());
                        break;
                    default:
                        break;
                }
                //ter.renderFrame(world.getWorld());
            }
            //showName();
            ter.renderFrame(world.getWorld());
            showName();
            StdDraw.show();
        }
        drawGameOver();
    }
    private void save(){
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(StdDraw.WHITE);
        java.awt.Font font = new java.awt.Font("AI", java.awt.Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 3)*2, "Starting game now!!!!!");

        font = new java.awt.Font("AI", java.awt.Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2,HEIGHT/2,"New Game (N)\nLoad Game (L)\nQuit (Q)");
        StdDraw.show();
    }

    private void drawGameOver(){
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(StdDraw.WHITE);
        java.awt.Font font = new java.awt.Font("Monaco", java.awt.Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Game Over!");
        StdDraw.show();
        StdDraw.pause(3000);
        System.exit(0);
    }
    private void showName() {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();

        if (x < WIDTH && y < HEIGHT) {
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(3, HEIGHT - 1, world.getWorld()[x][y].description());
            //StdDraw.show();
        }

    }
    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        input = input.toUpperCase();

        // 找到 N 和 S 的位置
        int start = input.indexOf('N') + 1;
        int end = input.indexOf('S');

        long seed;
        // 提取 N 和 S 之间的字符串
        if (start > 0 && end > start) {
            String seedString = input.substring(start, end);
            // 将字符串 "12345" 转换为数字 12345L
            seed = Long.parseLong(seedString);
        } else {
            // 如果没有找到符合格式的种子，给一个默认值
            seed = 0;
        }


        world = new WorldGenerator(WIDTH, HEIGHT, seed);
        TETile[][] finalWorldFrame = world.generate();

        return finalWorldFrame;
    }

}
