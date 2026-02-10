package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;

        this.rand = new Random(seed);
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator

    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String temp = "";
        for (int i = 0; i < n; i++) {
            int index = rand.nextInt(26);
            temp += CHARACTERS[index];
        }
        return temp;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.black);
        Font font = new Font("Monospaced", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text((double) this.width / 2, (double) this.height / 2, s);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.text((double) this.width / 2, ((double) this.height / 3) * 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (char temp : letters.toCharArray()) {

            drawFrame(String.valueOf(temp));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String input = "";

        while (input.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char temp = StdDraw.nextKeyTyped();
                input += temp;
                drawFrame(input);
            }
        }
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int flag=0;
        if(this.round==1) {
            flag = 1;
        }
        while(flag==1){
            drawFrame("Round:"+this.round);
            String trueWord=generateRandomString(round);
            flashSequence(trueWord);
            String temp=solicitNCharsInput(round);
            if(temp==trueWord){
                round++;
            }
            else{
                flag=0;
            }
        }
        System.out.print("Game Over! You made it to round:"+round);
        //TODO: Establish Game loop
    }

}
