import collections
from collections import defaultdict
from typing import List, Tuple, Deque


def dfs(node: int, parent: int, children: list[list[int]], k: int):
    if k < 0:
        return 0

    res = 1
    for child in children[node]:
        if child == parent:
            continue
        res += dfs(child, node, children, k - 1)

    return res



def count_node_edges(edges, k):
    children = [[] for _ in range(len(edges) + 1)]
    for edge in edges:
        u = edge[0]
        v = edge[1]
        children[u].append(v)
        children[v].append(u)

    edge_count = [0] * len(children)
    for i in range(len(children)):
        edge_count[i] = dfs(i, -1, children, k)
    return edge_count


def maxTargetNodes(edges1: List[List[int]], edges2: List[List[int]], k: int) -> List[int]:
    edge_count_1 = count_node_edges(edges1, k)
    edge_count_2 = count_node_edges(edges2, k - 1)

    max_edges = max(edge_count_2)
    res = edge_count_1.copy()
    for i in range(len(edge_count_1)):
        res[i] += max_edges

    return res
