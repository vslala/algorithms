#include<iostream>

using namespace std;

int main ()  {
    long min = 0, max = -1, matches = 0, score = 0, min_count = 0, max_count = -1;
    cin >> matches;
    for (int  i = 0; i < matches; i++) {
        cin >> score;

        if (score > max) {
            if (i == 0) min = score;
            max = score;
            max_count++;
        } else {
            if (score < min) {
                min = score;
                min_count++;
            }
        }
    }

    cout << max_count << " " << min_count;
}