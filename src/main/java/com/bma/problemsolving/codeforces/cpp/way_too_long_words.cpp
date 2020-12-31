#include<iostream>
#include<string>

using namespace std;

int main() {
    int n;
    cin>>n;

    string words[n];

    for (int i=0; i<n; i++) {
        cin>>words[i];
    }

    for (int i=0; i<n; i++) {
        string word = words[i];
        if (word.length() > 10) {
            cout <<word.at(0);
            cout <<word.length() - 2;
            cout <<word.at(word.length() - 1);
        } else {
            cout <<word;
        }
        cout<<"\n";
    }

    return 0;
}