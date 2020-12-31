#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include<unordered_map>
#include<unordered_set>
#include<set>
#include<map>
#include<queue>
#include<math.h>

using namespace std;

#define pb push_back

inline vector<long long int> sum_to_target(vector<long long int> preamble, int line_count, long long int target) {
    vector<long long int> range;
    long long int sum = 0;
    for (int i=0; i < line_count;  i++) {
        sum = 0;
        range.clear();
        for (int j = i; j < line_count; j++) {
            sum += preamble[j];
            range.pb(preamble[j]);

            if (sum > target) {
                break;
            }

            if (sum == target) {
                return range;
            }
        }

    }

    return {};
}

inline long min_and_max_sum(vector<long long int> range) {
    long long int min = INT_MAX;
    long long int max = INT_MIN;

    for (auto num: range) {
        if (num > max) {
            max = num;
        }

        if (num < min) min = num;
    }

    return min + max;
}

inline bool is_valid(vector<long long int> preamble, long long int target) {
    unordered_set<long long int> compliment;
    for (auto p: preamble) {
        if (compliment.count(p) == 0) {
            compliment.insert(target - p);
        } else {
            return true;
        }
    }

    return false;
}

int main () {
    ifstream in("inputs/day9.txt");
    string line;
    vector<long long int> all_nums;
    vector<long long int> preamble;

    while (getline(in, line)) 
        all_nums.pb(stol(line));

    int line_count = 0;
    for (auto num: all_nums) {
        if (preamble.size() == 25) {
            if (is_valid(preamble, num)) 
                preamble.erase(preamble.begin());
             else {
                // PART ONE
                cout << num << endl;
                cout << "-------------\n";
                vector<long long int> range = sum_to_target(all_nums, line_count, num);
                long long int sum = 0;
                sum += min_and_max_sum(range);
                cout << sum;
                cout << endl;
                break;
            }
        } 
            
        preamble.pb(num);  
        line_count++;
    }
    


    in.clear();
    in.close();
    return 0;
}