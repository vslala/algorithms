#include<iostream>
#include<string>
#include<vector>
#include<unordered_map>

using namespace std;

void findAll(string combinations, string& digits, int digit_index, unordered_map<char,string> telephone_num_chars, vector<string> &output) {
    if (digit_index == digits.length()) {
        output.push_back(combinations);
        return;
    }

    for (char eachChar: telephone_num_chars[digits[digit_index]]) {
        combinations[digit_index] = eachChar;
        findAll(combinations, digits, digit_index + 1, telephone_num_chars, output);
    }
    
}

int main() {
    string digits;
    cin  >> digits;

    if (digits.empty()) return {};

    unordered_map<char,string> telephone_num_chars;
    telephone_num_chars.insert({'2', "abc"});
    telephone_num_chars.insert({'3', "def"});
    telephone_num_chars.insert({'4', "ghi"});
    telephone_num_chars.insert({'5', "jkl"});
    telephone_num_chars.insert({'6', "mno"});
    telephone_num_chars.insert({'7', "pqrs"});
    telephone_num_chars.insert({'8', "tuv"});
    telephone_num_chars.insert({'9', "wxyz"});

   
    vector<string> output;
    string combinations(digits.length(), ' ');
    findAll(combinations, digits, 0, telephone_num_chars, output);

    for (string combination: output) {
        cout << combination  << ",";
    }
}