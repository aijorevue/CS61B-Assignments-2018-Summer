package byog.Core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Heart {
    private int width;
    private int height;
    Heart(int w,int h){
        width=w;
        height=h;
        initializeHeart();
    }
    public void initializeHeart() {
        StdDraw.clear(Color.black);

        StdDraw.setPenColor(Color.WHITE);
        Font font=new Font("AI",Font.BOLD,50);
        StdDraw.setFont(font);

        StdDraw.text((double) width /2, (double) height /2,"starting game now!!!!!");
        StdDraw.show();
    }


}
