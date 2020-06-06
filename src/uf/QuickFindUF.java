package uf;

public class QuickFindUF {
  private int[] ids;

  QuickFindUF(int n) {
    ids = new int[n];
    for (int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  public void union(int p, int q) {
    if (!connected(q, p)) {
      int pVal = ids[p];
      int qVal = ids[q];
      for (int i = 0; i < ids.length; i++) {
        if (ids[i] == pVal) {
          ids[i] = qVal;
        }
      }

    }
  }

  public boolean connected(int q, int p) {
    return ids[q] == ids[p];
  }
}
