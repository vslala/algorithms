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


const int NO_CYCLE = -1;

class Command {
    string val;
    public:
    string instruction;
    
    Command(string instruction, string value) {
        this->instruction = instruction;
        this->val = value;
    }

    int value() {
        char op = val[0];
        int num = stoi(val.substr(1));

        if (op == '-') return num * -1;
        return num;
    }
};

vector<int> detect_cycle(vector<Command*> commands) {
    unordered_set<int> visited_commands;
    int curr = 0, acc = 0, len = commands.size() - 1;
    Command* command;
    while (true) {
        if (curr >= len) {
            return {NO_CYCLE, acc};
        }

        command = commands[curr];

        if (visited_commands.count(curr) > 0) 
            return {0, acc};
        

        visited_commands.insert(curr);
        if (command->instruction.compare("nop") == 0) {
            curr++;
            continue;
        }

        if (command->instruction.compare("acc") == 0) {
            acc += command->value();
            curr++;
            continue;
        }
        
        if (command->instruction.compare("jmp") == 0) {
            curr += command->value();
            continue;
        }
    }   

    return {NO_CYCLE, acc};
}

inline int next_change_command_pos(int curr, vector<Command*> commands) {
    for (int i = curr + 1; i < commands.size(); i++) {
        if (commands[i]->instruction == "nop" || commands[i]->instruction == "jmp") return i;
    }
    return -2;
}

int fix_operation(vector<Command*> commands) {
    int acc = 0;
    int change_command_pos = -1;
    while ((change_command_pos = next_change_command_pos(change_command_pos, commands)) != -2) {
        acc = 0;
        commands[change_command_pos]->instruction = commands[change_command_pos]->instruction == "nop" ? "jmp" : "nop";

        vector<int> result = detect_cycle(commands);
        acc = result[1];
        
        if (result[0] == NO_CYCLE) {
            return acc;
        }
        // revert back to original
        commands[change_command_pos]->instruction = commands[change_command_pos]->instruction == "nop" ? "jmp" : "nop";
    }

    return 0;
}

int main () {
    vector<Command*> commands;
    unordered_set<int> visited_commands;

    ifstream in("inputs/day8.txt");
    string line;
    while (getline(in, line)) {
        // start your code here...
        commands.push_back(
            new Command(line.substr(0, line.find(" ")),
                line.substr(line.find(" ")))
        );
    }

    // PART - 1
    int acc = detect_cycle(commands)[1];
    cout << acc << endl;

    // PART - 2
    acc = fix_operation(commands);
    cout << acc << endl;

    in.clear();
    in.close();
    return 0;
}