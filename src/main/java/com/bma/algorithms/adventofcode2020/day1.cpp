#include<iostream>
#include<vector>
#include<fstream>
#include<string>
#include<unordered_set>

using namespace std;

#define pb push_back

class Day1 {
    vector<int> input;

    void readInput() {
        ifstream day1_input("inputs/day1.txt");
        
        string num;
        while (getline(day1_input, num)) {
            input.pb(stoi(num));
        }
    }
    
    public:
    Day1() {
        readInput();
    }

    // APPROACH 2 - PART 1
    int two_pointer(int target) {
        vector<int> input = {1721,979,366,299,675,1456};
        sort(input.begin(), input.end());
        int j = input.size() - 1;
        for (int i = 0; i < input.size();) 
            if (input[i] + input[j] > target) j--;
            else if (input[i] + input[j] < target) i++;
            else return input[i] * input[j];
        
        return 0;
    }

    // Approach 3 - Part - 1 | HashSet approach
    int hash_set_approach(int target) {
        unordered_set<int> complements;
        vector<int> input = {1721,979,366,299,675,1456};
        for (auto year: input) {
            int complement = target - year;
            if (complements.count(year) != 0) 
                return year * complement;
            else 
                complements.insert(complement);
            
        }

        return 0;
    }

    // APPROACH 1 - BRUTE FORCE
    // Complexity - O(n3)
    int bruteforce(int target) {
        int  len = input.size();
        for (int i=0; i < len - 2; i++) 
            for (int j = i + 1; j < len - 1; j++) 
                for (int k = j + 1; k < len; k++) 
                    if (input[i] + input[j] + input[k] == target) 
                        return input[i]  * input[j] * input[k];

        return 0;
    }

    // APPROACH 2 - TWO POINTER
    // Complexity - O(nlogn) + O(n2) = O(n2)
    int three_pointer(int target) {
        sort(input.begin(), input.end());
        int len = input.size();
        for(int i = 0; i < len - 2; i++) 
            for (int j = i + 1, k = len - 1; j < k;) 
                if (input[i] + input[j] + input[k] > target) k--;
                else if (input[i] + input[j] + input[k] < target) j++;
                else return input[i]  * input[j] * input[k];
            
        return 0;
    }
    
};

int main() {
    int target = 2020;

    Day1 *day1 = new Day1();

    int result = day1->two_pointer(target);
    cout << "TWO POINTER RESULT:\t\t" << result << endl;

    result = day1->hash_set_approach(target);
    cout << "HASHSET APPROACH RESULT:\t" << result << endl;

    result = day1->bruteforce(target);
    cout << "BRUTE FORCE RESULT:\t\t" << result << endl;

    result = day1->three_pointer(target);
    cout << "THREE POINTER RESULT:\t\t" << result << endl;
    return 0;
}