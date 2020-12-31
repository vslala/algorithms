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

class MemoryBoard {
    int pos = 1;
    public:
	int prev_number;
	int last_number;
	unordered_map<int, int> times_spoken;
	unordered_map<int, vector<int>> num_pos;

    void add_number(int number) {
	prev_number = last_number;
	last_number = number;
	times_spoken[number]++;
	num_pos[number].push_back(pos++);
    }

    void generate_next() {
	if (times_spoken[last_number] <= 1) {
	    add_number(0);
	    return;
	}
	
	if (times_spoken[last_number] > 1) {
	    vector<int> positions = num_pos[last_number];
	    int l = positions[positions.size() - 1];
	    int sl = positions[positions.size() - 2];
	    add_number(l - sl);
	}
    }

    void   print() {
	cout << "Last Number: " << last_number << endl;
	cout << "Word Frequency: " << endl;
	for (auto f: times_spoken) {
	    cout << f.first << ":"  << f.second << ", ";
	}
	cout << endl;
	cout << "Word Position: " << endl;
	for (auto p: num_pos)  {
	    cout << p.first << ":";
	    for (auto position: p.second) cout << position << " ";
	    cout << ",";
	}
	cout << endl;
	cout << "------------------------------------------" << endl;
    }

};

int main () {
    // ifstream in("inputs/day15test.txt");
    vector<int> input = {0,5,4,1,10,14,7};
    MemoryBoard board;

    for (auto num: input) {
	board.add_number(num);
    }
    
    for (int i = input.size() - 1; i < 30000000; i++) {
	if (i == 2020)  {
	    cout << board.prev_number << endl;
	}
	board.generate_next();
    }
    
    cout << "LAST NUMBER: " << board.prev_number;
    return 0;
}
