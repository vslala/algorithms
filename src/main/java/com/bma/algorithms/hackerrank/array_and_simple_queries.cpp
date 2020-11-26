#include<iostream>
#include<vector>

using namespace std;

vector<int> arr;
vector<vector<int>> queryList;

void input() {
    freopen("input.txt", "r", stdin);
    
    int arr_size = 0, queries = 0;
    cin >> arr_size;
    cin >> queries;
    
    for (int i=0; i<arr_size; i++) {
        int input;
        cin >> input;

        arr.push_back(input);
    }

    for (int i=0; i < queries; i++) {
        int query_type;
        int start_index;
        int end_index;
        
        cin >> query_type >> start_index >> end_index;

        queryList.push_back({query_type, start_index, end_index});
    }
}

void right_shift(vector<int> &arr, int from, int shift_len) {
    int tmp = arr[from];
    for (int i = 0; i < shift_len; i++, from++) {
        arr[from] = arr[from + 1];
    }
    arr[from] = tmp;
}

void left_shift(vector<int> &arr, int from, int shift_len) {
    int tmp = arr[from];
    for (int i = 0; i < shift_len; i++, from--) {
        arr[from] = arr[from - 1];
    }
    arr[from] = tmp;
}

int main() {
    input();
    
    // 1 2 3 4 5 6 7 8
    // 1 2 4

    // 2 3 4 1 5 6 7 8
    // 2 3 5

    for (auto query: queryList) {
        int query_type = query[0];      // 1
        int start_index = query[1] - 1; // 2
        int end_index = query[2] - 1;   // 4

        if (query_type == 1) {
            for (int index = start_index - 1; index >= 0; index--) {
                right_shift(arr, index, end_index - start_index + 1);
            }
        }

        if (query_type == 2) {
            for (int index = end_index + 1; index < arr.size(); index++) {
                left_shift(arr, index, end_index - start_index + 1);
            }
        }
        
    }

    cout << (arr.front() - arr.back()) << "\n";
    for (auto item: arr) cout << item << " ";

    return 0;
}