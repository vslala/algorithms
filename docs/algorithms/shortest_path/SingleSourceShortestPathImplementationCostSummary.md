## Single Source Shortest Path Implementation: Cost Summary

|   algorithm                   |   restrictions        |   typical case    | worst case    | extra space       |
|   ---                         |   ---                 |   ---             | ---           | ---               |
|   topological sort            |   no directed cycles  |   E + V           |   E + V       |   V               |
|   Dijkstra (binary heap)      |   no negative weights |   E log V         |   E log V     |   V               |
|   Bellman-Ford                |   no negative cycles  |   EV              |   EV          |   V               |
|   Bellman-Ford (queue based)  |   no negative cycles  |   E + V           |   EV          |   V               |

- Remark 1: Directed cycles makes the problem harder
- Remark 2: Negative weights make the problem harder
- Remark 3: Negative cycles makes the problem intractable