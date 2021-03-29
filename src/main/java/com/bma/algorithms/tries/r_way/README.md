## Trie Data Structure

It is a symbol table specialized to string keys.

The goal is to device a data structure that is **Faster than hashing and More flexible than BSTs**.

Challenge - Efficient Performance for String keys

### R-Way Trie

- Store characters in nodes (not keys)
- Each node has `R` children for each possible character.
- Store values in nodes corresponding to last characters in keys.

### Trie Performance

- **Search Hit**
    
    Need to examine all `L` characters for equality

- **Search Miss**

    - could have mis-match on first character
    - Typical case: examine only a few characters (sublinear)
    
- **Space**

    - Space is a downside. `R` null links at each leaf.
    - (but sublinear space possible if many short strings share common prefixes)
    - ```
        public class TrieNode {
            private static final int R = 256;   // extended ascii

            Object value;
            TrieNode[] next = new TrieNode[R];
        }
      ```
    
- **Bottom Line*: Fast search hit and even faster search miss, but wastes space.

### Popular Interview Question

- Design a data structure to perform efficient spell checking

**Sol.** Build a 26-way trie (key = word, value = bit)

### Deletion in R-Way Trie

To delete a key-value pair:

- Find a node corresponding to the key and set the value to `null`
- If that node has all null links, remove that node (and recur).