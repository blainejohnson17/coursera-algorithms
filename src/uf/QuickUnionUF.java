package uf;

public class QuickUnionUF implements UnionFind {

  private int[] id;

  public QuickUnionUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  @Override
  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);
    if (pRoot != qRoot) {
      id[pRoot] = qRoot;
    }
  }

  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private int root(int i) {
    while (i != id[i]) {
      i = id[i];
    }
    return i;
  }

}
