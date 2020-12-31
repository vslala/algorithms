#include<iostream>
#include<fstream>
#include<vector>
#include<unordered_set>
#include<string>
#include<unordered_map>

using namespace std;

int main () {
    unordered_set<char> answers, common_set;
    string input;
    ifstream in("inputs/day_6_input_deepanshi.txt");
    int count = 0, line_count = 0;
    vector<int> all_answers;
    unordered_map<char,int> char_count;
    while (getline(in, input)) {
        if (input.empty()) {
            // group ends here...
            // PART-1
            // count += answers.size();
            // answers.clear();
            
            // PART - 2
            sort(all_answers.begin(), all_answers.end());
            for (int i=0; i < all_answers.size(); i++) {
                char_count[all_answers[i]]++;
            }
            
            for(auto itr: char_count) {
                if (itr.second == line_count) count++;
            }

            all_answers.clear();
            char_count.clear();
            line_count = 0;
            continue;
        }

        line_count++;
        for (int i = 0 ; i < input.length(); i++) {
            // PART - 1
            // answers.insert(input[i]);

            // PART - 2
            all_answers.push_back(input[i]);
            
        }
    }

    // PART - 1
    // count += answers.size();

    // PART - 2
    sort(all_answers.begin(), all_answers.end());
    for (int i=0; i < all_answers.size(); i++) {
        char_count[all_answers[i]]++;
    }
    
    for(auto itr: char_count) {
        if (itr.second == line_count) count++;
    }

    all_answers.clear();
    char_count.clear();
    line_count = 0;

    cout << count;
    return 0;
}