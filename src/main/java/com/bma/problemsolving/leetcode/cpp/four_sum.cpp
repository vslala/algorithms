#include<iostream>
#include<vector>

using namespace std;

vector<vector<int>> fourSum(vector<int>& nums, int target) {
    int len = nums.size();
    int a=0, b = 1, c = 2, d = len - 1;

    vector<vector<int>> subsets;

    for (int a = 0; a < len - 4; a++) {
        if (a > 0 && nums[a] == nums[a-1]) continue;

        for (int b = a + 1; b < len - 3; b++) {
            if (nums[b] == nums[b-1]) continue;

            for (int c = b+1, d = len-1; d > b && c < len;) {
                if (nums[c] > nums[c - 1]) {
                    c++;
                }
                if (nums[a] + nums[b] + nums[c] +  nums[d] > target) {
                    d--;
                } else if (nums[a] + nums[b] + nums[c] +  nums[d] < target) {
                    c++;
                } else {
                    subsets.push_back({a,b,c,d});
                }
            }
        }
    }

    return subsets;
}
int main() {
    int target, size;
    vector<int> nums;
    
    cin >> size;
    for (int i=0; i < size; i++) {
        int input;
        cin >> input;
        nums.push_back(input);
    }

    cin >> target;

    cout << endl;
    vector<vector<int>> four_sums = fourSum(nums, target);
    for (auto subset: four_sums) {
        for (auto item: subset) {
            cout << item << ",";
        }
        cout << endl;
    }

    return 0;
}