#include<iostream>

using namespace std;

int numlength(int n) {
    int len = 0;
    while (n != 0) {
        n = n / 10;
        len++;
    }

    return len;
}

int main() {
    int series[] = {0, 1, 3, 6, 10};
    int testcases = 0;
    int num = 0;

    cin >> testcases; cin.ignore();

    int result[testcases];

    for (int i=0; i < testcases; i++) {
        cin >> num; cin.ignore();
        result[i] = (num % 10 - 1) * 10 + series[numlength(num)-];
    }

    for (int i=0; i < testcases; i++) {
        cout << result[i] << "\n";
    }

    return 0;    
}