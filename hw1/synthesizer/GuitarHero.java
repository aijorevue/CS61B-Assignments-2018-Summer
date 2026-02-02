package synthesizer;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public void main(String[] args) {
        GuitarString[] guitar = new GuitarString[37];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for(int i = 0; i<37;i++)
        {
            double freq = 440 * Math.pow(2, (i - 24) / 12.0);
            guitar[i] = new GuitarString(freq);
        }

        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key=StdDraw.nextKeyTyped();

                int index=keyboard.indexOf(key);

                if(index>0){
                    guitar[index].pluck();
                }
            }

            double sample=0;

            for(int i=0;i<37;i++){
                sample+=guitar[i].sample();
            }

            StdAudio.play(sample);

            for(int i=0;i<37;i++){
                guitar[i].tic();
            }
        }
    }
}
