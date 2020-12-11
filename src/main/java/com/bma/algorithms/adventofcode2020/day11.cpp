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

const char FLOOR = '.';
const char EMPTY = 'L';
const char OCCUPIED =  '#';

int main () {
    vector<vector<char>> layout;
    ifstream in("inputs/day11.txt");
    string line;

    while (getline(in, line)) {
        vector<char> col;
        for (int  i =0; i < line.size(); i++) {
            col.push_back(line[i]);
        }        
        layout.push_back(col);
    }
    int height = layout.size();
    int width = layout[0].size();
    int total = 0;
    bool change = true;
    while (change) {
        change = false;
        vector<vector<char>>  newLayout = layout; 
        for (int i=0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (layout[i][j] == FLOOR) continue;

                int count = 0;
                for (int r = -1; r <= 1; r++) {
                    for  (int c = -1; c<=1; c++) {
                        if (r == 0 && c == 0) continue;
                        int rr = i + r;
                        int cc = j + c;
                        while (0<=rr && rr < height && 0 <= cc && cc < width && layout[rr][cc] == FLOOR) {
                            rr = rr + r;
                            cc = cc + c;
                        }
                        if (0 <= rr && rr < height && 0 <= cc && cc < width &&  layout[rr][cc] == OCCUPIED) {
                            count ++;
                        }
                    }
                }

                if (layout[i][j] == EMPTY) {
                    if (count == 0) {
                        newLayout[i][j] = OCCUPIED;
                        change = true;
                    }
                } 
                
                else if (layout[i][j] == OCCUPIED && count >= 5) {
                    newLayout[i][j] = EMPTY;
                    change = true;
                }
            }
        }

        layout = newLayout;
    }
    

    for (int i=0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (layout[i][j] == OCCUPIED) total++;
        }
    }

    cout << total << endl;

    in.clear();
    in.close();
    return 0;
}