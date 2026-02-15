package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF whetherPercolation;
    private WeightedQuickUnionUF whetherFull;

    private boolean[][] opened;
    private int openSites;

    private int n;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int N) {
        validate(N);
        n = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        whetherPercolation = new WeightedQuickUnionUF(N * N + 2);
        whetherFull = new WeightedQuickUnionUF(N * N + 1);
        opened = new boolean[N][N];
        openSites = 0;
    }

    private void validate(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int turn(int row, int col) {
        return row * n + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        opened[row][col] = true;
        openSites++;
        int current = turn(row, col);
        if (row == 0) {
            whetherPercolation.union(current, virtualTop);
            whetherFull.union(current, virtualTop);
        }
        if (row == n - 1) {
            whetherPercolation.union(current, virtualBottom);
        }
        int[] ro = {-1, 1, 0, 0};
        int[] co = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int roo = ro[i] + row;
            int coo = co[i] + col;
            if (roo >= 0 && coo >= 0 && roo < n && coo < n && isOpen(roo, coo)) {
                whetherPercolation.union(current, turn(roo, coo));
                whetherFull.union(current, turn(roo, coo));
            }
        }
    }      // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opened[row][col];
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        validate(row, col);
        return whetherFull.connected(turn(row, col), virtualTop);
        //2时代表水灌进来
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return openSites;
    }         // number of open sites

    public boolean percolates() {
        return whetherPercolation.connected(virtualTop, virtualBottom);
    }             // does the system percolate?

    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
