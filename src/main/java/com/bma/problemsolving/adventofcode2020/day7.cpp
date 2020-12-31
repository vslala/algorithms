#include<iostream>
#include<string>
#include<vector>
#include<unordered_map>
#include<unordered_set>
#include<set>
#include<map>
#include<math.h>
#include<fstream>
#include<queue>
#include<map>

using namespace std;

map<string,map<string,int>> generate_adjacency_graph() {
    ifstream in("inputs/day7.txt");
    string line;
    vector<string> all_bags;
    map<string, map<string,int>> adj;
    while (getline(in, line)) {
        int left_of_contain = line.find("bags");
        string curr = line.substr(0, left_of_contain - 1);
        map<string, int> bag_collection;
        bool should_concat = false;
        string bag_name;
        int bag_count = 0;
        for (int i = left_of_contain; i < line.size() - 3; i++) {
            if (line[i] == ' ') 
                if (line[i + 1] == 'b' && line[i + 2] == 'a' && line[i + 3] == 'g') {
                    bag_collection[bag_name] = bag_count;
                    bag_name = "";
                    should_concat = false;
                }
            
            if (should_concat)
                bag_name += line[i];

            if (isdigit(line[i])) {
                bag_count = line[i] - '0';
                should_concat = true;
                i++;
            }
        }

        adj[curr] = bag_collection;
        all_bags.emplace_back(curr);
    }

    return adj;
}

vector<string> get_all_bag_names(map<string,map<string,int>> adj) {
    vector<string> bags;
    for (auto  bag: adj) {
        bags.push_back(bag.first);
    }
    return bags;
}

int calculate_total_count(string node, map<string,map<string,int>> adj) {
    int count = 0;
    if (adj[node].empty()) 
        return 0;
    else
        for (auto bag:  adj[node]) 
            count += bag.second * (1 + calculate_total_count(bag.first, adj));
        
    return count;
}

int main() {
    
    map<string, map<string,int>> adj  = generate_adjacency_graph();
    vector<string> all_bags =  get_all_bag_names(adj);
    
    // debug
    // for (auto bag: adj) {
    //     cout << bag.first << "\t\t=>\t";
    //     for (auto b:  bag.second) {
    //         cout << b.first << ":" << b.second << ", ";
    //     }
    //     cout << endl;
    // }

    // part one
    unordered_set<string>  distinct;
    queue<string> q;
    q.push("shiny gold");
    while (! q.empty()) {
        string to_find = q.front();
        q.pop();
        for (auto bag: all_bags) {
            if (adj[bag].count(to_find)) {
                if (distinct.count(bag) == 0)
                    q.push(bag);
                distinct.insert(bag);
            }
        }
    }

    cout << distinct.size();
    
    // part two
    string first = "shiny gold";
    int total_count = calculate_total_count(first, adj);
    cout << endl << total_count;
    
    return 0;
}