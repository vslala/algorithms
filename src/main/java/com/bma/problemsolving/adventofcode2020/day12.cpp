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

#define pb push_back


const char NORTH = 'N';
const char SOUTH = 'S';
const char EAST = 'E';
const char WEST = 'W';
const char LEFT = 'L';
const char RIGHT = 'R';
const char FORWARD = 'F';

class Instruction {
    public:
        char direction;
        int val;
};
int main () {
    vector<string> lines;
    ifstream in("inputs/day12.txt");
    string line;
    while (getline(in, line)) {
        lines.push_back(line);
    }

    vector<Instruction> instructions;
    for (auto instruction : lines) {
        Instruction ins;
        ins.direction = instruction[0];
        ins.val = stoi(instruction.substr(1));
        instructions.push_back(ins);
    }
    // N,E,S,W
    int dx[] = {0,1,0,-1};
    int dy[] = {1,0,-1,0};
    int x = 0;
    int y = 0;
    int direction = 1;
    int degree = 0;
    char curr_direction = EAST;
    for (auto ins: instructions) {
        // cout << ins.direction << ins.val << endl;
        if  (ins.direction == NORTH) {
            y += ins.val;
        }

        else if (ins.direction == SOUTH) {
            y -= ins.val;
        }

        else if (ins.direction == EAST) {
            x += ins.val;
        }

        else if (ins.direction == WEST) {
            x -= ins.val;
        }

        else if (ins.direction == LEFT) {
            direction = (direction + 3 * (ins.val / 90)) % 4;
        }

        else if (ins.direction == RIGHT) {
            direction = (direction + 1 * (ins.val / 90)) % 4;
        }

        else if (ins.direction == FORWARD) {
            x += dx[direction] * ins.val;
            y += dy[direction] * ins.val;
        }

    }

    cout << abs(x) + abs(y)  << endl;

    in.clear();
    in.close();
    return 0;
}