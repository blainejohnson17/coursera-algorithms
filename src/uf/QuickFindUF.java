package uf;

public class QuickFindUF {
  private int[] id;

  QuickFindUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

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

  public boolean connected(int q, int p) {
    return id[q] == id[p];
  }
}
