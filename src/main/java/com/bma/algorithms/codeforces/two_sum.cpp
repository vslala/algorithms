#include<iostream>
#include<vector>
#include<unordered_map>

using namespace std;

vector<int> twoSum(vector<int>& nums, int target) {
    unordered_map<int, int> compliments;

    for (int i=0; i < nums.size(); i++) {
        if (compliments.count(nums[i]) > 0) {
            return {i, compliments[nums[i]]};            
        }
        cout << target - nums[i] << ":" << i << ",";
        compliments.insert({target - nums[i], i});  // {3:0, 2:1}
    }

    return {};
}

int main() {
    // vector<int> nums = {2,7,11,15};
    vector<int> nums = {3,2,4};
    // int target = 9;
    int target = 6;

    vector<int> result = twoSum(nums, target);

    cout  << "\n";
    for (auto item: result) {
        cout << item << ",";
    }

    return 0;
}