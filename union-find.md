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

  Quick-Find Implementation:
    - data structure: int array, where index is identity (id) of object
    - value at index is connected component (group) id
    - objects are connected if and only if they are in same connected group (they have same value)

