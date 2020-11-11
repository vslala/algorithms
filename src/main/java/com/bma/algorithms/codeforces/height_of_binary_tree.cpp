#include<iostream>
#include<math.h>

using  namespace std;

class Node {
    public:
        int data;
        Node* left;
        Node* right;
};

int height(Node* root) {
    if (root == NULL) {
        return -1;
    }
    
    return max(height(root->left), height(root->right)) + 1;
}

int main() {
    // take binary tree as input
    Node* node;

    int h = height(node);

    cout << "Height of the tree is " << h;

    return 0;
}