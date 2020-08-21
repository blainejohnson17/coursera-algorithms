import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double CONFIDENCE_95 = 1.96;
  // each result is a proportion of open sites to total sites at the point when the set first
  // percolates
  private final double[] results;
  private final int trials;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException();
    }
    results = new double[trials];
    this.trials = trials;
    int totalSites = n * n;
    for (int i = 0; i < trials; i++) {
      Percolation p = new Percolation(n);
      do {
        int row;
        int col;
        do {
          row = StdRandom.uniform(n) + 1;
          col = StdRandom.uniform(n) + 1;
        } while (p.isOpen(row, col));
        p.open(row, col);
      } while (!p.percolates());
      results[i] = (double) p.numberOfOpenSites() / totalSites;
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(results);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(results);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n, trials);
    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi());
  }
}
