#include<iostream>
#include<unordered_set>

using namespace std;

int main() {

    int matrix[3][3] = {
        {1, 3, 4}, 
        {5, 2, 9}, 
        {8, 7, 6}
    };

    unordered_set<int> posEl;

    for (int i=0; i < 3; i++){
        int min = matrix[0][0], max = matrix[0][0];
        for (int j=0; j < 3; j++) {
            if (matrix[i][j] > max) {
                max = matrix[i][j];
            } else {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }

        posEl.insert(min);
        posEl.insert(max);

        min = matrix[0][0], max = matrix[0][0];
        for (int j=0; j < 3; j++) {
            if (matrix[j][i] > max) {
                max = matrix[j][i];
            } else {
                if (matrix[j][i] > min) {
                    min = matrix[j][i];
                }
            }
        }

        posEl.insert(min);
        posEl.insert(max);
    }

    for (auto item: posEl) {
        cout << item << ",";
    }

    cout << "\n" << posEl.size();

    return 0;
}