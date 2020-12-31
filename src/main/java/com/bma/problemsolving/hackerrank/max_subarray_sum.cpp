#include<iostream>
#include<vector>

using namespace std;

int main() {

    vector<int> arr = {-1, 2, 4, -3, 5, 2, -5, 2};

    int best = 0, sum = 0;
    for (int k = 0; k < arr.size(); k++) {
        sum = max(arr[k], arr[k] + sum);
        best = max(best, sum);
    }

    cout << "MAX SUM: " << best;
    return 0;
}