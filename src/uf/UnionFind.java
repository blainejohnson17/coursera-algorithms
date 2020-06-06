package uf;

public interface UnionFind {

  void union(int p, int q);

  boolean connected(int q, int p);

}
