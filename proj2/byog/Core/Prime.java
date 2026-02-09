package byog.Core;

import java.util.ArrayList;
import java.util.List;

public class Prime {


    private static double distace(Room r1, Room r2) {
        int dx = r1.getCenterX() - r2.getCenterX();
        int dy = r1.getCenterY() - r2.getCenterY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    private static List<Edge> calculate_the_edge(List<Room> rooms) {
        List<Edge> edge = new ArrayList<Edge>();
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                double dis = distace(rooms.get(i), rooms.get(j));
                edge.add(new Edge(i, j, dis));
            }
        }
        return edge;
    }

    public static List<Edge> calculate_prime(List<Room> rooms) {
        List<Edge> allEdges = calculate_the_edge(rooms);
        boolean[] visited = new boolean[rooms.size()];
        List<Edge> mstEdges = new ArrayList<>();
        visited[0] = true;

        for (int count = 0; count < rooms.size() - 1; count++) {
            Edge bestEdge = null;
            double smallest = Double.MAX_VALUE;

            for (Edge e : allEdges) {
                boolean fromVisited = visited[e.from];
                boolean toVisited = visited[e.to];
                if (fromVisited != toVisited) { // 一个在圈内，一个在圈外
                    if (e.distance < smallest) {
                        smallest = e.distance;
                        bestEdge = e;
                    }
                }
            }

            if (bestEdge != null) {
                mstEdges.add(bestEdge);
                // 将新连接的房间标记为已访问
                visited[bestEdge.from] = true;
                visited[bestEdge.to] = true;
            }
        }
        return mstEdges;
    }

}
