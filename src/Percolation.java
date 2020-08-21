import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int n;
  private int nOpenSites = 0;
  private boolean[][] grid;
  private final WeightedQuickUnionUF uf;
  private final WeightedQuickUnionUF ufAlt;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }

    this.n = n;
    grid = new boolean[n + 1][n + 1];
    uf = new WeightedQuickUnionUF((n * n) + 2);
    ufAlt = new WeightedQuickUnionUF((n * n) + 1);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validateInput(row, col);

    if (isOpen(row, col)) {
      return;
    }

    int p = ufIdx(row, col);

    // open site
    grid[row][col] = true;
    nOpenSites++;

    // connect to open site above
    if (row > 1 && isOpen(row - 1, col)) {
      uf.union(p, ufIdx(row - 1, col));
      ufAlt.union(p, ufIdx(row - 1, col));
    }

    // open site below
    if (row < n && isOpen(row + 1, col)) {
      uf.union(p, ufIdx(row + 1, col));
      ufAlt.union(p, ufIdx(row + 1, col));
    }

    // open site left
    if (col > 1 && isOpen(row, col - 1)) {
      uf.union(p, ufIdx(row, col - 1));
      ufAlt.union(p, ufIdx(row, col - 1));
    }

    // open site right
    if (col < n && isOpen(row, col + 1)) {
      uf.union(p, ufIdx(row, col + 1));
      ufAlt.union(p, ufIdx(row, col + 1));
    }

    // connect to virtual top node
    if (row == 1) {
      uf.union(p, ufVirtualTopIdx());
      ufAlt.union(p, ufVirtualTopIdx());
    }

    // connect to virtual bottom node
    if (row == n) {
      uf.union(p, ufVirtualBottomIdx());
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validateInput(row, col);

    return grid[row][col];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validateInput(row, col);

    return ufAlt.find(ufVirtualTopIdx()) == ufAlt.find(ufIdx(row, col));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return nOpenSites;
  }

  // does the system pergridColate?
  public boolean percolates() {
    return uf.find(ufVirtualTopIdx()) == uf.find(ufVirtualBottomIdx());
  }

  private int ufIdx(int row, int col) {
    return ((row - 1) * n) + col;
  }

  private int ufVirtualTopIdx() {
    return 0;
  }

  private int ufVirtualBottomIdx() {
    return (n * n) + 1;
  }

  private void validateInput(int row, int col) {
    if (row < 1 || row > n || col < 1 || col > n) {
      throw new IllegalArgumentException();
    }
  }
}
