#include<iostream>
#include<vector>
#include<queue>
#include<unordered_map>
#include<map>
#include<stack>

using namespace std;

#define mp make_pair

class Node {
    public:
        int data;
        Node* left;
        Node* right;
};


void insertNode(Node* head, Node* newNode) {
    if (head ==  NULL) return;

    if (newNode->data <= head->data) {
        if (head->left == NULL) {
            head->left = newNode;
            return;
        }
        insertNode(head->left, newNode);
    } else {
        if (head->right == NULL) {
            head->right = newNode;
            return;
        }
        insertNode(head->right, newNode);
    }
}

Node* createTree() {
    vector<int> treeInput = {72, 30, 44, 58, 73, 49, 7};
    cout << "Input Nodes:\t";
    for (auto input: treeInput) cout << input << ",";

    Node* head = new Node();
    head->data = treeInput[0];

    for (int i = 1; i < treeInput.size(); i++) {
        Node* newNode = new Node();
        // int random = rand() % 100;
        int random = treeInput[i];
        newNode->data = random;
        insertNode(head, newNode);   
    }

    return head;
}

void preOrder(Node* head) {
    if (head != NULL) {
        cout << head->data << ", ";
        preOrder(head->left);
        preOrder(head->right);
    }
}

void postOrder(Node* head) {
    if (head != NULL) {
        postOrder(head->left);
        postOrder(head->right);
        cout << head->data << ", ";
    }
}

void inOrder(Node* head) {
    if (head != NULL) {
        inOrder(head->left);
        cout << head->data << ", ";
        inOrder(head->right);
    }
}

void levelTraversal(Node* head) {
    queue<Node*> remaining;
    remaining.push(head);

    while (!remaining.empty()) {
        Node* node = remaining.front();
        cout << node->data << ", ";
        remaining.pop();
        if (node->left != NULL)
            remaining.push(node->left);
        
        if (node->right != NULL)
            remaining.push(node->right);
    }
}

int verticalOrder(Node* head, int l,  unordered_map<int, vector<Node*>> &vertical) {
    if (head != NULL) {
        if (vertical.count(l) == 0){
            vertical[l] = vector<Node*>();
        }
        vertical[l].push_back(head);
        verticalOrder(head->left, l - 1, vertical);
        verticalOrder(head->right, l + 1, vertical);
    }

    return l;
}

void topView(Node* head, int level, int vertical_dist, unordered_map<int, pair<Node*, int>> &m)  {
    if (head  != NULL) {
        
        if (m.count(vertical_dist) == 0) {
            m[vertical_dist] = pair<Node*, int>(head, level);
        } else if (m[vertical_dist].second > level) {
            m[vertical_dist] = pair<Node*, int>(head, level);
        }

        topView(head->left, level + 1, vertical_dist - 1,  m);
        topView(head->right, level + 1, vertical_dist + 1,  m);
    }
}

void lca(Node* head, int v1, int v2, queue<Node*> &trace)  {
    if (v1 < head->data && v2 < head->data) lca(head->left, v1, v2, trace);
    if (v1 > head->data && v2  > head->data) lca(head->right,  v1, v2, trace);
    
    trace.push(head);
}

Node* lowestCommonAncestor(Node* head, int v1, int v2) {
    queue<Node*> trace;
    lca(head, v1, v2, trace);
    return trace.front();
}

void iterativePreOrder(Node* head) {
    stack<Node*> s;
    Node* curr = head;
    while (!s.empty() || curr  != NULL) {
        if (curr != NULL) {
            cout << curr->data <<  ",";
            s.push(curr);
            curr = curr->left;
        } else {
            curr = s.top();
            s.pop();
            curr = curr->right;
        }
    }
}

void iterativeInOrder(Node* head) {
    stack<Node*> s;
    Node* curr = head;
    while (!s.empty() || curr != NULL) {
        if (curr != NULL) {
            s.push(curr);
            curr = curr->left;
        } else {
            curr = s.top();
            s.pop();
            cout << curr->data << ",";
            curr = curr->right;
        }
    }
}

void iterativePostOrder(Node* head) {
    
}

int main() {
    Node* head = createTree();
    cout << "\n";
    cout << "Pre-Order:\t";
    preOrder(head);
    cout << "\n";

    cout << "Post-Order:\t";
    postOrder(head);
    cout << "\n";

    cout << "In-Order:\t";
    inOrder(head);
    cout << "\n";

    cout << "Level-Order:\t";
    levelTraversal(head);
    cout << "\n";

    cout << "Vertical-Order:\t";
    unordered_map<int, vector<Node*>> vertical;
    verticalOrder(head, 0, vertical);
    for (auto ln: vertical) {
        cout << ln.first << ":";
        for (auto node: ln.second) {
            cout << node->data << ",";
        }
    }
    cout << "\n";

    cout << "Top-View Recr.:\t";
    unordered_map<int, pair<Node*,int>> topViewMap;
    topView(head, 0, 0, topViewMap);
    for (auto topV: topViewMap) {
        cout << topV.second.first->data << ",";
    }
    cout << "\n";

    cout << "LCA:\t\t";
    Node* lca = lowestCommonAncestor(head, 7, 49);
    cout << lca->data << "\n";
    
    cout << "I-pre-order:\t";
    iterativePreOrder(head);
    cout << "\n";

    cout << "I-inorder:\t";
    iterativeInOrder(head);
    cout << "\n";
}