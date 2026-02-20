package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solver {
    private MinPQ<SearchNode> pq;

    private SearchNode goalNode;
    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        Set<WorldState> visited = new HashSet<>();
        pq.insert(new SearchNode(initial, 0, null));

        while (true) {
            SearchNode current = pq.delMin();

            if (visited.contains(current.node)) {
                continue;
            }
            visited.add(current.node);

            if (current.node.isGoal()) {
                goalNode = current;
                break;
            }

            for (WorldState neighbor : current.node.neighbors()) {
                if (!visited.contains(neighbor)) {
                    pq.insert(new SearchNode(
                            neighbor,
                            current.moves + 1,
                            current));
                }
            }
        }
    }

    public int moves() {
        return goalNode.moves;
    }

    public Iterable<WorldState> solution() {
        LinkedList<WorldState> path = new LinkedList<>();
        SearchNode current = goalNode;

        while (current != null) {
            path.addFirst(current.node);
            current = current.previous;
        }

        return path;
    }
}
