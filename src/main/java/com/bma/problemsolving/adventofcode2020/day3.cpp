#include<iostream>
#include<fstream>
#include<string>
#include<vector>

using namespace std;

int  main() {
    ifstream day_3_input("inputs/day3.txt");
    string line;

    vector<vector<char>> frame;
    while (getline(day_3_input, line)) {
        vector<char> col;
        for (int j=0; j < line.length(); j++) {
            col.push_back(line[j]);
        }
        frame.push_back(col);
    }

    vector<vector<int>> slopes =  { {3,1} };
    // vector<vector<int>> slopes =  { {1,1}, {3,1}, {5,1}, {7,1}, {1,2} };

    int mul = 1;
    
    for (auto slope: slopes) {
        int count = 0;
        int r = slope[1], c = slope[0];
        while (r < frame.size()) {
            if (frame[r][c % frame[r].size()] == '#') count++;
            c += slope[0];
            r += slope[1];
        }

        cout << count << "," << endl;
        mul *= count;
    }
    

    cout << mul;

    day_3_input.clear();
    day_3_input.close();

    return 0;
}