package hw2;

import edu.princeton.cs.introcs.StdRandom;

import java.util.Random;

public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;


    private double[] counts;


    public PercolationStats(int N, int T, PercolationFactory pf) {
        validate(N, T);
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.counts = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int a = StdRandom.uniform(N);
                int b = StdRandom.uniform(N);
                if (!p.isOpen(a, b)) {
                    p.open(a, b);
                }
            }
            counts[i] = (double) p.numberOfOpenSites() / Math.pow(N, 2);
        }
    }// perform T independent experiments on an N-by-N grid

    public double mean() {
        double mean = 0;
        for (double count : counts) {
            mean += count;
        }
        return mean / counts.length;
    }                                           // sample mean of percolation threshold

    public double stddev() {
        double moecule = 0;
        for (double count : counts) {
            moecule += StrictMath.pow(count - mean(), 2);
        }
        return Math.sqrt(moecule / (T - 1));
    }                                 // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }                                  // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }

    private void validate(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
    }
}
