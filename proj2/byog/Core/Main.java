package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

/** This is the main entry point for the program. This class simply parses
 *  the command line inputs, and lets the byog.Core.Game class take over
 *  in either keyboard or input string mode.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] world;

        if (args.length > 0) {
            // 如果命令行有参数（比如 java Main N123S），取第一个
            world = game.playWithInputString(args[0]);
        } else {
            // 如果你直接点运行没加参数，这里给一个默认值方便你调试图片
            world = game.playWithInputString("N12345678S");
        }

        // 渲染出来
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        ter.renderFrame(world);
    }
}
