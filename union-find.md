# Union-Find

### Key Concepts
  - Apply the union-find data type to solve problems in science, engineering, and industry.
  - Define the union-find (or disjoint sets) data type.
  - Compare the performance of different algorithms for the union-find data type.
  - Design different algorithms (quick find, quick union, weighted quick union, path compression) for the union-find data type.
  - Develop Java implementations of different algorithms for the union-find data type.
  - Use the parent-link representation to represent tree data structures.


### Dynamic Connectivity
  - Given a set of N objects
  - Operations:
    1. union command: connects 2 objects together
    2. connected query: checks if 2 objects are connected (directly or through other connected objects)


  Modeling the connections
  Assuming "is connected to" is an equivalence relation
    - equivalence relations have these properties:
      - Reflexive: p is connected to p (is connected to itself)
      - Symmetric: if p is connected to q, then q is connected to p
      - Transitive: if p is connected to q and q is connected to r, then p is connected to r

  Connected Components
    - Maintaining sets of objects that are mutually connected
    - use as technique in algorithms to gain efficiency answer *connected* query

  Union-Find data type (API)
  Goal: Design an effective datastructure for union-find
    - Number of objects (N) can be large
    - Number of operation (M) can be large
    - Union commands and Connected queries can be intermixed

  Interface:
    - UF(int n)
    - void union(int q, int p)
    - boolean connected(int q, int p)

  Implementation Cost Summary:
  | Algorithm                      | worst case time |
  |--------------------------------|-----------------|
  | quick-find                     | M N             |
  | quick-union                    | M N             |
  | weighted QU                    | N + M log N     |
  | weighted QU + path compression | N + M lg* N     |

  Quick-Find:
    Implementation Notes:
      - data structure: int array, where index is identity (id) of object
      - value at index is connected component (group) id
      - objects are connected if and only if they are in same connected group (they have same value)
    Performance Notes:
      - Cost Model: Number of array accesses (for read or write)
        - used for cost evaluation
      - Initialize: n, (proportional to size n, happens once upon init)
      - union n, (proportional to size n, happens once for each union)
      - find 1, (constant time regardless of size n, happens once for each find)
      - Defect:
        - too slow for union, n^2 time for n unions on n objects
        - trees are flat, but expensive to keep them flat (updating many elements during union)

  Quick-Union (lazy approach)
    Implementation Notes:
      Represents connected components as tree structure, connected objects are in same tree
      Is lazy in that it will do most work during find operation
      Data Structure:
        - int[] id size n
        - id[i] = parent of i (value of array at index i is index of parent of i)
        - root of object is found by traversing through parents until element references self as parent (root has self as parent)

      Find:
        - Check if p & q have same root

      Union:
        - merge tree of p with tree of q by connecting root of p to root of q

      Performance Analysis:
        - initialize: n
        - find: n (worst case if tall/skinny trees)
        - union: 1, contant time
        - Defects: Find too expensive when trees get too tall

  Weighted Quick-Union
    Same as quick-union with the enhancement of placing the smaller tree under the bigger tree when performing the union command

      Data Structure:
        - int[] id size n
        - int[] weights size n (tracks weights of each tree where root is idx and weight is value)
        - id[i] = parent of i (value of array at index i is index of parent of i)
        - root of object is found by traversing through parents until element references self as parent (root has self as parent)

      Find:
        - Check if p & q have same root

      Union:
        - merge tree of p with tree of q by connecting root of p to root of q
        - place smaller tree under root of bigger tree

      Performance:
        - Init: n
        - Find: lg n
        - Union: lg n

      Note: the find and union of this operation is log2 n because the find and union operation cost depends on cost of finding root. Cost of find root is proportional to how far object is from root (how many levels away from root). A tree section increases it's distance from root when being merged into tree. treeA is merged into treeB (gets placed under root of treeB) when n of treeA <= n of treeB. When this happens treeA becomes at least twice as big. So, increase in depth can only happen at most lg n times since if you start w/ 1 and double lg n times, you get to n and there is only n nodes in the tree.

  Weighted Quick-Union with Path Compression
    Same as weighted quick-union with the enhancement of compressing the path when we find the root. 

      Data Structure:
        - int[] id size n
        - int[] weights size n (tracks weights of each tree where root is idx and weight is value)
        - id[i] = parent of i (value of array at index i is index of parent of i)
        - root of object is found by traversing through parents until element references self as parent (root has self as parent)

      Find:
        - Check if p & q have same root
        - while travelling up tree to find root, make the parent of each node we touch point to grand-parent

      Union:
        - merge tree of p with tree of q by connecting root of p to root of q
        - place smaller tree under root of bigger tree
        - while travelling up tree to find root, make the parent of each node we touch point to grand-parent

      Performance:
        - Init: n
        - Find: lg* n
        - Union: lg* n

      Note: lg* n (iterated log) is the number of times you have the take lg n to get 1
      Defined as: `0 if n <= 1, 1 + lg*(lg n) if n > 1`
      It grows extremely slow, e.g. lg* n => 5, where n => 2^65536


