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

int main () {
    ifstream in("inputs/day13test.txt");
    string line;
    
    getline(in, line);
    int et = stoi(line);

    getline(in, line);
    vector<int> bus_ids;
    int start = 0, next_break;
    while((next_break = line.find(",", start)) != -1) {
        string bus_id = line.substr(start, next_break);
        if (bus_id.compare("x") == 0) {
            start = next_break;
            continue;
        };

        cout << "Bus Id:  " << bus_id << endl;
        bus_ids.push_back(stoi(bus_id));
        start = next_break;
    }

    for (auto i: bus_ids) {
        cout << i << ",";
    }


    in.clear();
    in.close();
    return 0;
}