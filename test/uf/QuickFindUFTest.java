package uf;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class QuickFindUFTest {

  @Test
  void test() {
    UnionFind uf = new QuickFindUF(10);
    uf.union(4, 3);
    testConnected(uf, new int[][] {{0}, {1}, {2}, {5}, {6}, {7}, {8}, {9}, {3, 4}});

    uf.union(3, 8);
    testConnected(uf, new int[][] {{0}, {1}, {2}, {5}, {6}, {7}, {9}, {3, 4, 8}});

    uf.union(6, 5);
    testConnected(uf, new int[][] {{0}, {1}, {2}, {7}, {9}, {3, 4, 8}, {5, 6}});

    uf.union(9, 4);
    testConnected(uf, new int[][] {{0}, {1}, {2}, {7}, {3, 4, 8, 9}, {5, 6}});

    uf.union(2, 1);
    testConnected(uf, new int[][] {{0}, {7}, {3, 4, 8, 9}, {5, 6}, {1, 2}});

    uf.union(8, 9);
    testConnected(uf, new int[][] {{0}, {7}, {3, 4, 8, 9}, {5, 6}, {1, 2}});

    uf.union(5, 0);
    testConnected(uf, new int[][] {{7}, {3, 4, 8, 9}, {0, 5, 6}, {1, 2}});

    uf.union(7, 2);
    testConnected(uf, new int[][] {{3, 4, 8, 9}, {0, 5, 6}, {1, 2, 7}});

    uf.union(6, 1);
    testConnected(uf, new int[][] {{3, 4, 8, 9}, {0, 5, 6, 1, 2, 7}});

    uf.union(1, 0);
    testConnected(uf, new int[][] {{3, 4, 8, 9}, {0, 5, 6, 1, 2, 7}});

    uf.union(6, 7);
    testConnected(uf, new int[][] {{0, 1, 2, 5, 6, 7}, {3, 4, 8, 9}});
  }

  void testConnected(UnionFind uf, int[][] groups) {
    for (int i = 0; i < groups.length; i++) {
      for (int j = 0; j < groups[i].length; j++) {
        int id = groups[i][j];

        for (int jj = j + 1; jj < groups[i].length; jj++) {
          int otherInSameGroup = groups[i][jj];
          assertTrue(uf.connected(id, otherInSameGroup));
          assertTrue(uf.connected(otherInSameGroup, id));
        }

        for (int ii = i + 1; ii < groups.length; ii++) {
          for (int jj = 0; jj < groups[ii].length; jj++) {
            int otherInOtherGroup = groups[ii][jj];
            assertFalse(uf.connected(id, otherInOtherGroup));
            assertFalse(uf.connected(otherInOtherGroup, id));
          }
        }
      }
    }
  }

}
