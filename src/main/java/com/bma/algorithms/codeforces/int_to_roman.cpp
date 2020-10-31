#include<iostream>
#include<unordered_map>
#include<vector>
#include<string>

using namespace std;

int lower_bound(vector<int> arr, int num) {
    int k = 0;
    for (int b = arr.size()/2; b >= 1; b = b / 2) {
        while (b + k < arr.size() && arr[k + b] <= num) k = k + b;
    }

    return arr[k];
}

int main() {
    vector<int> decimals = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    unordered_map<int,string> int_to_roman;
    int_to_roman.insert({1,     "I"});
    int_to_roman.insert({4,     "IV"});
    int_to_roman.insert({5,     "V"});
    int_to_roman.insert({9,     "IX"});
    int_to_roman.insert({10,    "X"});
    int_to_roman.insert({40,    "XL"});
    int_to_roman.insert({50,    "L"});
    int_to_roman.insert({90,    "XC"});
    int_to_roman.insert({100,   "C"});
    int_to_roman.insert({400,   "CD"});
    int_to_roman.insert({500,   "D"});
    int_to_roman.insert({900,   "CM"});
    int_to_roman.insert({1000,  "M"});


    int  num;
    cin >> num;

    while (num > 0) {
        int lower_bound_val = lower_bound(decimals, num);
        string roman = int_to_roman[lower_bound_val];
        cout << roman;

        num = num - lower_bound_val; 
    }
    

}