#include<iostream>
#include<vector>

using namespace std;


// This is straightforward performing the steps and readings the final values.
inline void right_rotation(vector<int> &a, int rotation) {
    int len = a.size();
    
    while (rotation > 0) {
        int last_element = a.back();
        for (int i=len - 1; i > 0; i--) {
            a[i] = a[i - 1];
        }
        a[0] = last_element;
        rotation--;
    }
}

// formulized approach to project the values of asked indices after k rotations
// formula -> arr[(len - (k % len) + query_index) % len] 
vector<int> circularArrayRotation(vector<int> a, int k, vector<int> queries) {
    int len = a.size();
    int rotations = k % len;

    vector<int> ans;
    for (auto q: queries) {
        int projectedIndexAfterKRotations = (len - (k % len) + q) % len;
        ans.push_back(a[projectedIndexAfterKRotations]);
    }

    return ans;
}
int main() {

    int arr_size, rotation_count, num_of_queries;
    cin >> arr_size >> rotation_count >> num_of_queries;

    int input;
    vector<int> a;
    for (int i=0; i < arr_size; i++) {
        cin >> input;
        a.push_back(input);
    }

    vector<int> queries;
    for (int i=0; i < num_of_queries; i++) {
        cin >> input;
        queries.push_back(input);
    }

    cout << "Query Result" << endl;

    vector<int> ans = circularArrayRotation(a, rotation_count, queries);
    for (auto result: ans) {
        cout << result << "\n";
    }

    return 0;
}