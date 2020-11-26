#include<iostream>
#include<vector>
#include<string>

using namespace std;

const vector<vector<int>> inputs = {
    {1, 2, 3, 4},
    {4, 2},
    {1},
    {3, 1, 2},
    {1, 5, 4, 3, 2, 6},
    {1, 6, 3, 4, 5, 2},
    {1, 2, 3, 5, 4, 6},
    {1, 2, 4, 3, 5, 6}
};

string almost_sorted_nlogn(vector<int> &arr) {
    vector<int> sorted_arr = arr;
    sort(sorted_arr.begin(), sorted_arr.end());

    int start = -1, end = -1, count = 0, len = sorted_arr.size();

    for (int i=0; i < len; i++) {
        if (sorted_arr[i] != arr[i]) {
            if  (start == -1) start = i;
            else end = i;

            count++;
        }
    }

    if (count == 2) {
        // next to each other
        if ((start == 0  && end - start == 1) || 
            (end == len - 1 && end - start == 1) || 
            (start > 0 && end == len - 1 && arr[end] > arr[start - 1]) ||
            (arr[start] < arr[end + 1] && arr[end] > arr[start - 1])) {
            return "yes\nswap " + to_string(start + 1) + " " + to_string(end + 1);
        } 

        return "no";
    }

    if (count > 2) {
        // to check if the segment is in decreasing order
        for (int i = start; i < end; i++) 
            if (arr[i] < arr[i+1]) return "no";
        
        if ((start == 0 && end == len - 1)                                  || 
            ((start == 0 && end + 1 < len) && arr[start] < arr[end + 1])    || 
            ((end == len - 1 && arr[end] > arr[start - 1]))                 || 
            (arr[end] > arr[start - 1] && arr[start] < arr[end  + 1]))
                return "yes\nreverse " + to_string(start + 1) + " " + to_string(end + 1);
        
        return "no";
    }

    return "no";
}

string almost_sorted(vector<int> &arr) {
    if (arr.size() < 1) return "no";
    if (arr.size() == 2 && arr[1] < arr[0]) return "yes\nswap " + to_string(1) + " " + to_string(2);
    if (arr.size() == 3 && arr[0] > arr[1]  && arr[1] < arr[2]) return "no";

    for (int i = 0; i < arr.size() - 1; i++) {
            
        if (arr[i] > arr[i + 1]) {
            if (arr[i] > arr[i+1] && arr[i+1] < arr[i+2]) {
                if (i + 2 == arr.size() - 1)  {
                    return "yes\nswap " + to_string(i+1) + " " +  to_string(i + 2);
                }

                if  (arr[i+1] > arr[i-1] && arr[i] < arr[i + 2]) {
                    return "yes\nswap "  + to_string(i+1) + " " + to_string(i + 2);
                }

                // find a number to swap
                for (int j= i + 1; j < arr.size() - 1; j++) {
                    if (arr[j] > arr[j+1]) {
                        if  (arr[j+1] < arr[i+1] && arr[j+1] > arr[i-1] && arr[i] > arr[j]) {
                            return "yes\nswap "  + to_string(i+1) + " " + to_string(j + 2);
                        }
                    }
                }

                return "no";
            }  
            
            else {
                for (int j=i; j < arr.size() - 1; j++)  {
                    if (arr[j] < arr[j+1]) {
                        // check for reverse
                        if (arr[j] >> arr[i - 1] && arr[i] < arr[j + 1]) {
                            return "yes\nreverse " + to_string(arr[j])  + " " + to_string(arr[i]);
                        }
                    }
                }
            }
                
        }
    }

    return "no";
}

int main() {
    // freopen("input.txt", "r", stdin);
    // vector<int> arr;

    // int input = 0;
    // for (int i=0; i < 100000; i++) {
    //     cin >> input;
    //     arr.push_back(input);
    // }
    // cout << arr.size() << ":" << to_string(arr[34376]) << ":" << to_string(arr[66703]) << endl;
    // cout << almost_sorted(arr) << endl;
    
    for (auto arr: inputs) {
        cout << almost_sorted_nlogn(arr);
        cout << endl;
    }

    return 0;
}