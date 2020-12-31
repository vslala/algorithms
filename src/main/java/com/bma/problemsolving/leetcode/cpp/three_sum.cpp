#include<iostream>
#include<vector>
#include<unordered_set>
#include<set>
#include<algorithm>

using namespace std;

#define sum(nums, i, l, r) (nums[i] + nums[l] + nums[r])

vector<vector<int>> threeSum(vector<int>& nums) {
    if (nums.empty()) return {};

    sort(nums.begin(), nums.end());
    int i = 0, l, r = nums.size() - 1;

    vector<vector<int>> output;

    while (i < nums.size() - 2) {
        l = i + 1;
        r = nums.size() - 1;
        
        // skip the loop because the triplet have already been counted for the same value of the i
        if (i > 0 && nums[i] == nums[i - 1]) {
            i ++;
            continue;
        }

        while (l < r) {
            cout << i << ":" << l << ":" << r << ", ";
            if (l > i + 1 && nums[l] == nums[l - 1]) {
                l++;
                continue;
            }

            if (sum(nums, i, l, r)  > 0) r--;
            else if (sum(nums, i, l, r) < 0) l++;
            else {
                output.push_back({nums[i], nums[l], nums[r]});
                l++;
                r--;
            }
        }

        i++;

    }

    return output;
}

int main() {
    vector<int> nums = {-1,0,1,2,-1,-4};

    vector<vector<int>> output = threeSum(nums); 

    cout << "\n";
    for (auto itr = output.begin(); itr != output.end(); itr++) {
        vector<int> sub_set = *itr;
        cout << "[";
        for (int i = 0; i < sub_set.size(); i++) {
            cout << sub_set[i] << ",";
        }
        cout << "]";
    }
}