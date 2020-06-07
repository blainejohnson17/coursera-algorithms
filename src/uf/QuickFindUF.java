package uf;

public class QuickFindUF implements UnionFind {
  private int[] id;

  QuickFindUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  @Override
  public void union(int p, int q) {
    if (!connected(q, p)) {
      int pVal = id[p];
      int qVal = id[q];
      for (int i = 0; i < id.length; i++) {
        if (id[i] == pVal) {
          id[i] = qVal;
        }
      }

    }
  }

  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }
}
