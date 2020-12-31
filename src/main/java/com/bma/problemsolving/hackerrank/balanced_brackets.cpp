#include<iostream>
#include<string>
#include<stack>
#include<unordered_map>

using namespace std;

int main() {
    int   n;
    cin >> n;

    unordered_map<char,char> brackets = {{'{','}'}, {'[', ']'}, {'(', ')'}};
    
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;

        stack<char> st;
        for (int i=0; i < s.size() ; i++) {
            if (brackets.count(s[i]) == 1) {
                st.push(s[i]);
            } else {
                char lastOpen = st.top();
                if (brackets[lastOpen] == s[i]) {
                    st.pop();
                }
            }
        }

        if (st.empty()) cout << "YES\n";
        else cout << "NO\n";
    }

    return 0;
}