#include<iostream>
#include<string>
#include<fstream>

using namespace std;

class PasswordPolicy {
    int minimum;
    int maximum;
    char character;
    string word;

    inline bool isHyphen(string line, int ptr) {
        return line[ptr] == '-';
    }

    inline bool isWhiteSpace(string line, int ptr) {
        return line[ptr] == ' ';
    }

    inline bool isColon(string line, int ptr) {
        return line[ptr]  == ':';
    }

    public:
    PasswordPolicy(string line) {
        int len = line.length();
        char buffer[len];
        int buffer_ptr = 0;
        int ptr = 0;

        while (ptr < len) {
            if (isHyphen(line, ptr)) {
                this->minimum = atoi(buffer);
                buffer_ptr = 0;
                ptr++;
            }

            if (isWhiteSpace(line, ptr)) { 
                this->maximum = atoi(buffer);
                buffer_ptr  = 0;
                ptr++;
            }

            if (isColon(line, ptr)) {
                this->character =  line[ptr-1];
                ptr++;
                buffer_ptr = 0;
            }

            if (isWhiteSpace(line, ptr)) {
                ptr ++;
                buffer_ptr = 0;
            }

            buffer[buffer_ptr++] = line[ptr++]; 
        }

        this->word = buffer;
    }

    bool isValid() {
        int count = 0;
        for (int i=0; i < word.length(); i++) 
            if (word[i] == character) 
                count++;
        
        if (count >= minimum && count <= maximum) return true;
        return false;
    }

    bool isCorrect() {
        if (word[minimum - 1] == character && word[maximum  - 1] != character) 
            return true;
        return false;
    }

};

class Day2 {
    int valid_passwords = 0;
    int correct_passwords = 0;
    
    public:
    Day2() {
        ifstream day2_input("inputs/day2.txt");
        
        string line;
        while (getline(day2_input, line))  {
            PasswordPolicy policy(line);
            if (policy.isValid()) valid_passwords++;
            if (policy.isCorrect()) correct_passwords++;
        }

        day2_input.close();
    }

    int validPasswords() {
        return this->valid_passwords;
    }

    int correctPasswords() {
        return this->correct_passwords;
    }
};

int main () {
    Day2 *day2 = new Day2();
    cout << day2->validPasswords() << endl;
    cout << day2->correctPasswords() << endl;
    return 0;
}