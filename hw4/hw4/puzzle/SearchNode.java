package hw4.puzzle;

public class SearchNode implements Comparable<SearchNode>{
    public WorldState node;
    public int moves;
    public SearchNode previous;
    private int priority;

    SearchNode(WorldState n, int m, SearchNode p) {
        node = n;
        moves = m;
        previous = p;
        priority = moves + n.estimatedDistanceToGoal();
    }

    @Override
    public int compareTo(SearchNode other) {
        return this.priority - other.priority;
    }
}
