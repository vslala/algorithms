#include<iostream>

using namespace std;

int  main() {

    int x;
    cin >> x;

    int l = 0;
    int r = x;
    while (l < r) {
        long long mid = l + (r - l) / 2;
        if (mid*mid > x) {
            r = mid - 1;
        } else if (mid * mid < x) {
            l = mid + 1;
            if (l * l > x) {
                l--;
                break;
            }
        } else if (mid * mid == x) {
            l = mid;
            break;
        }
    }

    cout << l;

    return 0;
}