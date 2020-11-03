#include<iostream>
#include<vector>
#include<unordered_set>
#include<set>
#include<algorithm>

using namespace std;

#define sum(nums, i, l, r) (nums[i] + nums[l] + nums[r])

int threeSum(vector<int>& nums, int target) {
    if (nums.empty()) return {};

    sort(nums.begin(), nums.end());
    int i = 0, l = i + 1, r = nums.size() - 1, output = 0, diff = INT_MAX;

    while (i < nums.size() - 2) {
        l = i + 1;
        r = nums.size() - 1;

        // skip the loop because the triplet have already been counted for the same value of the i
        if (i > 0 && nums[i] == nums[i - 1]) {
            i ++;
            continue;
        }

        while (l < r) {
            // cout << i << ":" << l << ":" << r << ", ";
            if (l > i + 1 && nums[l] == nums[l - 1]) {
                l++;
                continue;
            }
            
            // calc smallest diff
            int curr_diff = abs(target - sum(nums, i, l, r));
            if (diff > curr_diff) {
                output = sum(nums, i, l, r);
                diff = curr_diff;
            }

            if (sum(nums, i, l, r) > target) r--;
            else if (sum(nums, i, l, r) < target) l++;
            else {
                output = sum(nums,i, l, r);
                return output;
            }
        }

        i++;

    }

    return output;
}

int main() {
    vector<int> nums = {-1,2,1,-4};
    int target = 1;

    int output = threeSum(nums, target); 

    cout << "\n";
    cout << "SUM: " << output;
}