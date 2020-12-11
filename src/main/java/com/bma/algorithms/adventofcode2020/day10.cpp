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

inline int find_max(vector<int> &jolts) {
    int max_num = INT_MIN;
    for (auto item: jolts) {
        if  (item > max_num) max_num = item;
    }
    return max_num;
}

map<int,long long int> dp;
inline long long int count_ways(vector<int> &jolts, int i) {
    for (auto item: jolts) {
        dp[item] = 0;
    }

    dp[0] = 0;

    long long int count = 0;

    for (auto data: jolts) {
        for  (int place = 1; place < 4; place++) {
            if (dp.count(data + place) > 0) {
                dp[data + place] += dp[place];
            }
        }
    }

    for (auto c: dp) {
        cout << c.first  << ":" << c.second << ",";
    }

    return count;
}

int main () {
    ifstream in("inputs/day10test.txt");
    string line;
    vector<int> jolts;
    jolts.push_back(0);
    while (getline(in, line)) {
        // start your code here...
        jolts.push_back(stoi(line));
    }
    jolts.push_back(jolts.back() + 3);
    sort(jolts.begin(), jolts.end());

    int one_diff_jolts = 0;
    int three_diff_jolts = 1;

    for  (int i = 0; i < jolts.size() - 1; i++) {
        if (jolts[i + 1] - jolts[i] == 1) one_diff_jolts++;
        if (jolts[i + 1] - jolts[i] == 3) three_diff_jolts++;
    }
    
    cout << one_diff_jolts << endl << three_diff_jolts  << endl << one_diff_jolts * three_diff_jolts << endl;

    // PART TWO
    // jolts.push_back(jolts.back() + 3);
    long long int ways = count_ways(jolts, 0);

    cout << ways;
    
    in.clear();
    in.close();
    return 0;
}