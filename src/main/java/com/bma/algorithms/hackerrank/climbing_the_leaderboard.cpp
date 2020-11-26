#include<iostream>
#include<vector>

using namespace std;

int find_rank(int total_score, vector<int> ranks, int right) {
    int left = 0, mid = 0;

    while (left < right) {
        mid = left + (right - left) / 2;
        if (total_score < ranks[mid]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left + 1;
}

vector<int> climbingLeaderboard(vector<int> ranked, vector<int> player) {
    vector<int> leaderboard;
    vector<int> ranks;

    for (int i = 0; i < ranked.size(); i++) {
        if (i == 0) {
            ranks.push_back(ranked[i]);
        } else {
            if (ranks.back() != ranked[i]) {
                ranks.push_back(ranked[i]);
            }
        }
    }

    int total_score = 0;
    for (int i = 0; i < player.size(); i++) {
        total_score = player[i];
        leaderboard.push_back(find_rank(total_score, ranks, i == 0 ? ranks.size() : leaderboard.back()));
    }


    return leaderboard;
}

int main() {
    int n;
    cin >> n; cin.ignore();

    vector<int> ranked(n);
    for (int  i = 0; i < n; i ++) {
        cin >> ranked[i];
    }

    int m;
    cin >>  m; cin.ignore();
    vector<int> player(m);
    for (int  i =0; i < m; i++) {
        cin >> player[i];
    }

    vector<int> ranks = climbingLeaderboard(ranked,  player);

    for (int i = 0; i < ranks.size(); i++) {
        cout << ranks[i] << ",";
    }

    return 0;
}
