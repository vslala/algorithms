#include<iostream>
#include<vector>

using namespace std;

typedef vector<int> vi;

const int x = 400;
vi v;
bool ok(int index) {
    return v[index] >= x;
}

int main() {
    
    int  n = 10000;
    int val = 0;
    for (int i = 0; i < n; i++) {
        val = val + 4;
        v.push_back(val);
    }

    // gap binary search
    int k = 0;
    for (int b = n/2; b >= 1; b = b/2) {    // n/2, n/4, n/6 ,,,
        while (k + b < n && v[k+b] <= x) k += b; 
        // k = 0, k + b = 5000  => false loop breaks;
        // k = 0, k + b = 2500  => false loop breaks;
        // k = 0, k + b = 1250  => false loop breaks;
        // k = 0, k + b = 625   => false loop breaks;
        // k = 0, k + b = 312   => false loop breaks;
        // k = 0, k + b = 106   => false loop breaks;
        // k = 0. k + b = 53    => true loop continue;
            // k = 53, k + b = 106 => false loop breaks;
        // k = 56, k + b = 56 + 26 = 82   => true loop continue;
            // k = 82, k + b = 108 => false loop breaks;
        // k = 82, k + b = 82 + 13 = 95 => true loop continues;
            // k = 95, k + b = 108 => false loop breaks;
        // k = 95, k + b = 101 => false loop breaks;
        // k = 95, k + b = 99 => true loop continues;
            // k = 99, k + b = 101 => false loop breaks;
        // k = 99, k + b = 100 => false loop breaks
        
        // outer loops breaks since b == 1;
    }

    // optimization; break the loop as soon as you find the value;
    bool found = false;
    for (int b = n/2; b >= 1; b /= 2) {
        while (k + b < n && v[k + b] <= x) {
            k = k + b;
            if (v[k] == x) {
                found = true;
                break;
            }
        }

        if (found) break;
    }

    if (v[k]  == 400) {
        cout << "FOUND!" << "; INDEX=" << k;
    }


    // Finding the smallest solution
    int a = 0;
    for (int b  = n/2; b >= 1; b /= 2) {
        while (!ok(a + b)) a += b;
    }
    k = a;

    cout << "\nSmallest solution for x is " << v[k];

    // Finding the largest solution
    a = 0;
    for (int b = n/2; b >= 1; b/=2) {
        while (!ok(a + b)) a += b;
    }
    k = a + 1;

    cout << "\nLargest solution for x is " << v[k];
}