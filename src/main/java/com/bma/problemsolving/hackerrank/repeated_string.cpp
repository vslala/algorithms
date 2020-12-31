#include<iostream>
#include<string>

using namespace std;

inline void count_char_freq(char c, string s, long until, long &count) {
    for  (long i=0; i < until; i++) 
        if (s[i] == 'a') count ++;
}

long repeatedString(string s, long n) {
    long len = s.length();
    long count = 0;

    count_char_freq('a', s, len, count);

    long full_repeat = n / len;
    long remaining_chars = n % len;
    count *= full_repeat;
    count_char_freq('a', s, remaining_chars, count);

    return count;
}

int main() {
    long n;
    string s;

    cin >> s >> n;

    long count = repeatedString(s, n);
    cout << count;
    return 0;
}