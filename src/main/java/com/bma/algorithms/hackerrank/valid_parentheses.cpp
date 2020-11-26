#include<iostream>
#include<stack>
#include<string>

using  namespace std;

int main() {
    string s = "[]";
    char open[] = {'(', '{', '['};
    char close[] = {')', '}', ']'};
    

    stack<int> stk;
    for (int i=0; i<s.length(); i++) {
        for (int j = 0; j < 3; j++) {
            if (s[i] == open[j]) {
                stk.push(s[i]);
                break;
            };
        }

        if (stk.empty()) {
            cout << "FALSE";
            return 0;
        }
        
        for (int j = 0; j < 3; j++) {
            if (s[i] == close[j]) {
                if (stk.top() == open[j]) {
                    stk.pop();
                    break;
                } else {
                    return false;
                }
            };
        }
    }

    if (stk.empty()) cout << "true";
    else cout << "false";

    return 0;
}