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

int total_bags(string node, map<string, map<string,int>>  &adj)
{
    int count=0;
    if(adj[node].empty())
        return 0;
    else
        for(auto bag : adj[node])
            count+= bag.second * (1 + total_bags(bag.first,adj));

    return count;
}

void Solve()
{
    ifstream in("inputs/day7.txt");
    string s;
    map<string, map<string, int>> adj;
    vector<string> all_bags;
    while (getline(in, s))
    {
        int loc = s.find("bags");
        string curr = s.substr(0, loc - 1);
        map<string, int> bag_collection;
        bool should_concat = false;
        string bag_name;
        int bag_count = 0;
        for (int i = loc; i < s.size() - 3; i++) {

            if (s[i] == ' ') 
                if (s[i + 1] == 'b' && s[i + 2] == 'a' && s[i + 3] == 'g') {
                    bag_collection[bag_name] = bag_count;
                    bag_name = "";
                    should_concat = false;
                }
            
            if (should_concat)
                bag_name += s[i];

            if (isdigit(s[i])) {
                bag_count = s[i] - '0';
                should_concat = true;
                i++;
            }
        }

        adj[curr] = bag_collection;
        all_bags.emplace_back(curr);
    }

    // PART ONE -
    int count = 0;
    queue<string> q;
    set<string> visited;
    string first;
    for (auto bag : all_bags){
        if (adj[bag].count("shiny gold")){
            if (visited.count(bag) == 0)
                q.push(bag);
            visited.insert(bag);
            q.push(bag);
        }
    }
    while (!q.empty()){
        string head = q.front();
        q.pop();

        for (auto bag : all_bags)
            if (adj[bag].count(head)){
                if (visited.count(bag) == 0)
                    q.push(bag);
                visited.insert(bag);
            }
    }

    cout << visited.size();

    // PART TWO - 
    count = 0;
    first = "shiny gold";
    count=total_bags(first,adj);
    cout<< endl << count<<endl;

}

int main () {
    Solve();
    return 0;
}