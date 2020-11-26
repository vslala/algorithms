#include<iostream>

using namespace std;

int totalCount = 0, n = 4, x = 0, y = 0; 
bool diag1[16], diag2[16], column[4];

void search(int y) {
    if (y == n) {
        // this is where you have found a solution
        totalCount++;
        cout << totalCount << "\n";
        return;
    } else {
        for (int x = 0; x < n; x++) {
            cout << "Row: " << y << ", Column: " << x << "\n";
            if (column[x] || diag1[y + x]|| diag2[x  - y + n - 1]) continue;

            column[x] = diag1[y + x] = diag2[x - y + n - 1] = true;

            search(y + 1);

            column[x] = diag1[y + x] = diag2[x - y + n - 1] = false;
        }
    }
    
}

int main() {
    search(0);

    cout << "Count: " << totalCount;
}

