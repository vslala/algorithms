#include<iostream>
#include<vector>
#include<unordered_set>

using namespace std;

int nonDivisibleSubset(int k, vector<int> s) {
    int len = s.size();
    int* freq = new int[k]{0}; // dynamically initialize array with 0
    int count = 0, bucket = 0;

    for (int i=0; i < len; i++) {
        bucket = s[i] % k;
        freq[bucket]++;
    }

    /*
        counts[0] - this array-element holds the number of items in S (the set of 'N' integers, provided as input) 
        that are divisible (or evenly-divisible, or give remainder-zero when divided) by 'k'.

        Only one such number can be included in the maximal subset S' because if you add another such number, 
        then the sum of these two numbers will be divisble by 'k'; 
        thus, rendering the subset not a non-divisible maximal subset.
    */
    count = min(freq[0], 1);
    
    // this is a simple loop that allows us to get the values from front and the back
    // k - i  will get us the ith element from the back of the array
    // for ex - 1,2,3,4 -> if i  == 1; then k  - i = 3; k being the length of the array.
    int limit = (k/2) + 1;
    for (int i=1; i < limit; i++) {
        cout << i << "," << (k-i) << "\n";
        if (i != (k - i))
            count += max(freq[i], freq[k - i]);
    }

    if (k % 2 == 0)
        count++;

    return count;
}

int main() {
    vector<int> s;
    int n,k;
    cin >> n >> k;
    
    int input;
    for (int i=0; i < n; i++) {
        cin >> input;
        s.push_back(input);
    }

    cout << nonDivisibleSubset(k, s);

    return 0;
}