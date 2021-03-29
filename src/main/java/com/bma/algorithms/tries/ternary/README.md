## Ternary Trie

Ternary trie attempts to reduce the space wasted by the 
R-way trie by limiting the children to 3 nodes (left, middle, right).
It creates slightly more nodes than R-Way trie but significantly reduces the space required
to store the pairs.


### Representation In Java

A TST node is a five field:

- A Value.
- A Character `c`.
- A reference to a left TST.
- A reference to a middle TST.
- A reference to a Right TST.

```
public class Node {
    private Object val;
    private char c;
    private Node left;
    private Node mid;
    private Node right;
}
```

### Cost Summary

|Search Hit |   Search Miss | Insert    | Space |
| --------- |   ----------- | ------    | ----  |
| L + logN  |   logN        | L + logN  | 4N    |

**Remark.** Can build balanced TSTs via rotations to achieve L + logN worst case guarantee.

**Bottom Line.** TST is as fast as hashing (for string keys), space efficient.

### Speed Up TSTs by using Hybrid R<sup>2</sup> Branching at Root

- Do R<sup>2</sup>-way branching at root.
- Each of R<sup>2</sup> root nodes points to a TST.

Q. What about one and two-letter word?

|Implementation                 |Search Hit |   Search Miss | Insert    | Space |
|-------                        | --------- |   ----------- | ------    | ----  |
| TST                           | L + logN  |   log N       | L + logN  | 4N    |
| TST with R<sup>2</sup>        | L + logN  |   log N       | L + logN  | 4N + R<sup>2</sup> |


### TST vs Hashing

**Hashing.**

- Need to examine entire key
- Search hit and misses cost about the same
- Performance relies on hash function
- Does not support ordered symbol table operations.

**TSTs.**

- Works only for strings (or digital keys)
- Only examines just enough key characters
- Search miss may involve only a few characters
- Supports ordered symbol table operations (plus others!)

**Bottom Line.** TSTs are:

- Faster than Hashing (especially for search misses)
- More flexible than red-black BSTs.