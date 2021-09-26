#include<iostream>
#include<vector>
using namespace std;

class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> hits;
        if (nums.size() == 0) {
            hits.push_back(-1);
            hits.push_back(-1);
            return hits;
        }
        int left = 0;
        int right = nums.size();
        search(nums, target, left, right, hits);
        return hits;
    }
    
    void search(vector<int>& nums, int target, int left, int right, vector<int>& hits) {
        int mid = left + ((right - left) / 2);
        
        if (nums[mid] == target) {
            for (int i = left; i < right; i++) {
                if (nums[i] == target) {
                    hits.push_back(i);
                    break;
                }
            }
            
            for (int i = right - 1; i > -1; i--) {
                if (nums[i] == target) {
                    hits.push_back(i);
                    break;
                }
            }
        }
        
        else if (left - right == 1) {
            hits.push_back(-1);
            hits.push_back(-1);
        }
        
        else if (nums[mid] < target) {
            search(nums, target, mid, right, hits);
        }
        
        else if (nums[mid] > target) {
            search(nums, target, left, mid, hits);
        }
    }
};

int main() {
    Solution sol;
    vector<int> nums;
    int target = 0;
    vector<int> hits = sol.searchRange(nums, target);
    cout<<hits[0]<<","<<hits[1];
}

