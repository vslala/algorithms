#include<iostream>
#include<string>
#include<vector>

using namespace std;

bool isPalindrome(string s, int l, int r) {
    int left = l, right = r;
    bool result = true;

    while (left < right) {
        if (s[left] != s[right]) {
            result = false;
            break;
        }

        left++;
        right--;
    }

    return result;
}

string longestPalindromeBruteForce(string s) {
    if (s.size() == 1) return s;
    int best_len = -1;
    string best_str = "";
    for (int l=0; l < s.size(); l++) {
        for (int r = l; r < s.size(); r++) {
            if (isPalindrome(s, l, r) && r - l + 1 > best_len) {
                best_len = r - l + 1;
                best_str = s.substr(l, best_len);
                cout << "L: " << l << ";LEN:" << best_len << ";" << best_str << ",";
            }
        }
    }
    return best_str;
}

void expandFromMiddle(string& s, int begin, int end, int& best_len, string& best_str) {

    while (begin >= 0 && end < s.length() && s[begin] == s[end]) {
        begin--;
        end++;
    }
    
    // adjusting the pointers
    int len = end - begin - 1;
    begin = begin + 1;
    end = begin + len;
    
    if (best_len < len) {
        best_len = len;
        printf("BEGIN: %d, END: %d, LEN: %d\n", begin, end, (end - begin));
        best_str = s.substr(begin, end - begin);
    }
}

string longestPalindrome(string s) {
    if (s.empty() || s.length() < 2) return s;
    int start, end, best_len = -1;
    string best_str;

    for (int i=0; i < s.length() - 1; i++) {
        // to handle the strings like "racecar". 
        // Here if we start expanding from the center char 'e' 
        // then the strings won't match and that will give us the wrong result.
        expandFromMiddle(s, i, i, best_len, best_str); 

        // this will handle the case where middle expansion matches every char
        // e.g. aabbaa
        expandFromMiddle(s, i, i+1, best_len, best_str);

    }

    return best_str;
}

int  main() {
    string s;
    cin >> s;

    string output = longestPalindrome(s);
    cout << "\n";
    cout << output;

    return 0;
}