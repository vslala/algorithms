#include<iostream>
#include<string>

using namespace std;

int main () {
    int len;
    cin >> len; cin.ignore();

    char str[len];
    cin >> str;

    int left = 0, right = len - 1;

    bool isOdd = len % 2 == 1;
    int mid = len / 2;
    int mid_c = str[mid];

    if (isOdd) {
        for (int i = mid; i <= right; i++) {
            str[i] = str[i+1];
        }
        right--;
    }
    
    int count = 0;
    while (left < right) {
        if (str[left] != str[right]) {
            // right shift
            char c = str[right];
            for (int j = right; j > left; j--) {
                str[j] = str[j - 1];
                count++;
            }
            str[left++] = c;
        } else {
            left ++;
            right --;
        }
    }

    count = isOdd ? ++count : count;
    count = (isOdd && (left > mid || right < mid)) ? ++count : count;
    cout << count;

    return 0;
}