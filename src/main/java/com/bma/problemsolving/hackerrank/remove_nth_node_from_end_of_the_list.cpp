#include<iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
 };

ListNode* removeNthFromEnd(ListNode* head, int n) {
    ListNode* itr = head;
    ListNode* start = head;

    for (int i = 0; i < n; i++, itr = itr->next) {}

    ListNode* end = itr;

    while (itr != NULL)  {
        itr = itr->next;
        if (itr != NULL) {
            start = start->next;
            end = end->next;
        }
    }

    if (start == head && end == NULL) head = start->next;
    else start->next = start->next->next;
    
    
    return head;

}

int main() {
    int nth;
    cin >> nth;

    ListNode* head = new ListNode(1);
    ListNode* last = head;

    for (int i = 2; i <= 5; i++) {
        last->next = new ListNode(i);
        last = last->next;
    }




    ListNode* itr = head = removeNthFromEnd(head, nth);


    while (itr != NULL) {
        cout << itr->val << ",";
        itr = itr->next;
    }

    return 0;
}