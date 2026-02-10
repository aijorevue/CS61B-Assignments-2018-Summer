package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
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

        WorldGenerator world = new WorldGenerator(WIDTH, HEIGHT, seed);
        TETile[][] finalWorldFrame = world.generate();

        return finalWorldFrame;
    }

}
