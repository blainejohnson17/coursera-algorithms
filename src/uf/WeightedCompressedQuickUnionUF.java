package uf;

public class WeightedCompressedQuickUnionUF implements UnionFind {
  private int[] id;
  private int[] weight;

  public WeightedCompressedQuickUnionUF(int n) {
    id = new int[n];
    weight = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      weight[i] = 1;
    }
  }

  @Override
  public void union(int p, int q) {
    int rootP = root(p);
    int rootQ = root(q);
    if (weight[rootP] > weight[rootQ]) {
      id[rootQ] = rootP;
      weight[rootP] += weight[rootQ];
    } else {
      id[rootP] = rootQ;
      weight[rootQ] += weight[rootP];
    }
  }

  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private int root(int i) {
    while (id[i] != i) {
      id[1] = id[id[1]];
      i = id[i];
    }
    return i;
  }
}
