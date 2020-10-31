#include<iostream>
#include<vector>
#include<unordered_set>
#include<set>
#include<algorithm>

using namespace std;

int sum(vector<int> arr, int i, int j, int k) {
    return arr[i] + arr[j] + arr[k];
}

int main() {
    vector<int> nums = {-1,0,1,2,-1,-4};

    sort(nums.begin(), nums.end());

    set<vector<int>> seen;
    vector<vector<int>> output;

    int l = 0, r = 0;

    for (int i = 0; i < nums.size(); i++) {
        l = i + 1; r = nums.size() - 1;
        while (l < r) {
            if (sum(nums, i, l, r) > 0) r--;
            else if (sum(nums, i, l, r) < 0) l++;
            else {
                vector<int> subset = {nums[i], nums[l++], nums[r--]};
                seen.insert(subset);
            }
        }
    }

    output.assign(seen.begin(),  seen.end());
    
    // vector<vector<int>> result;
    // for (auto itr = output.begin(); itr != output.end(); itr++) {
    //     result.push_back(*itr);
    // }


    for (auto itr = output.begin(); itr != output.end(); itr++) {
        vector<int> sub_set = *itr;
        cout << "[";
        for (int i = 0; i < sub_set.size(); i++) {
            cout << sub_set[i] << ",";
        }
        cout << "]";
    }
}