import pytest

from python.src.main.leetcode.dailychallenge.maximize_number_of_target_nodes_after_connecting_tree_part_1 import \
    maxTargetNodes


@pytest.mark.parametrize("test_input", [
    {
        'edges1': [[0, 1], [0, 2], [2, 3], [2, 4]],
        'edges2': [[0, 1], [0, 2], [0, 3], [2, 7], [1, 4], [4, 5], [4, 6]],
        'k': 2,
        'expected': [9, 7, 9, 8, 8]
    },
    {
        'edges1': [[0, 1], [0, 2], [0, 3], [0, 4]],
        'edges2': [[0, 1], [1, 2], [2, 3]],
        'k': 1,
        'expected': [6, 3, 3, 3, 3]
    }
])
def test_should_maximize_number_of_target_nodes_after_connecting_to_another_tree(test_input: dict):
    output = maxTargetNodes(edges1=test_input['edges1'], edges2=test_input['edges2'], k=test_input['k'])
    assert test_input['expected'] == output
