#include<iostream>
#include<map>
#include<array>
#include<vector>

using namespace std;

map<int, array<int, 3> > keyval;
array<int,3> buildingTypes = {3, 5, 7};

bool findVal(int num) {
    bool found = false;
    array<int,3> value = {0,0,0};
    for (int j = 0; j < 3; j++)  {
        if (num % buildingTypes[j] == 0) {
            value[j] = num / buildingTypes[j];
            keyval.insert(make_pair(num, value));
            found = true;
            break;
        }
    }

    return found;
}


void init() {
    array<int,3> notfound = {-1, -1, -1};
    keyval.insert(make_pair(1, notfound));
    keyval.insert(make_pair(2, notfound));

    for (int  i = 3; i <= 1000; i++) {
        bool found = findVal(i);
        if (found)  continue;

        for (int j=0; j < 3; j++) {
            int num = i;
            num = num - buildingTypes[j];
            if (num < 3) num = 1;
            array<int, 3> arr = keyval.find(num)->second;
            
            if (arr[0] == -1) {
                keyval.insert(make_pair(i, notfound));
            } else {
                array<int, 3> newArr = arr;
                newArr[j]++;
                keyval.insert(make_pair(i, newArr));
            }

            break;
        }
        
    }
}

int main() {
    
    init();

    int testcases;
    cin  >> testcases; cin.ignore();

    const size_t resultsize = testcases;
    vector<string> result;
    int windows;
    for (int i=0;  i <  testcases; i++)  {
        cin >> windows;
        array<int, 3> arr = keyval.find(windows)->second;
        for (int i=0; i < 3; i++) {
            if (arr[0] == -1) {
                result.push_back("-1");
                break;
            } else {
                result.push_back(to_string(arr[i]).append(" "));
            }
        }
        result.back().append("\n");
    }

    for (int i=0; i < result.size(); i++) {
        cout << result.at(i);    
    }

    return 0;
}