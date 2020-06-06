package uf;

public class QuickFindUF {
  public int[] ids;

  QuickFindUF(int n) {
    ids = new int[n];
    for (int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  public void union(int q, int p) {
    if (!connected(q, p)) {
      int pVal = ids[p];
      for (int i = 0; i < ids.length; i++) {
        if (ids[i] == pVal) {
          ids[i] = ids[q];
        }
      }

    }
  }

  public boolean connected(int q, int p) {
    return ids[q] == ids[p];
  }
}
