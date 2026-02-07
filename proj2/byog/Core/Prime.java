package byog.Core;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    private static class Edge{
        int from;
        int to;
        double distance;

        Edge(int f,int t,double d){
            from=f;
            to=t;
            distance=d;
        }
    }
    private static double distace(Room r1,Room r2){
        int dx=r1.centerX-r2.centerX;
        int dy=r1.centerY-r2.centerY;
        return Math.sqrt(dx*dx+dy*dy);
    }

    private static List<Integer> initial_point(int count){
        List<Integer> whether_visit=new ArrayList<>(count);
        for(int i:whether_visit){
            i=0;
        }

        return whether_visit;
    }
    private static List<Edge> calculate_the_edge(List<Room> rooms){
        List<Edge> edge=new ArrayList<Edge>();

        int a=0,b=1;
        List<Room> temp=rooms;
        for(Room i:temp){
            temp.removeFirst();
            for(Room j:temp){
                double dis=distace(i,j);
                Edge e=new Edge(a,b,dis);
                b++;
                edge.add(e);
            }
            a++;
        }
        return edge;
    }

    private static void calculate_prime(List<Room> rooms){
        List<Edge> edge=calculate_the_edge(rooms);
        List<Integer> whether_visit=initial_point(rooms.size());

    }


}
