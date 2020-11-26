#include<iostream>
#include<vector>

using namespace std;

int main() {
    vector<int> my_list = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
    int  num = 34;
    int expected = 40;

    int left = 0,right = my_list.size(),  mid  = 0;
    while (left < right) {
        mid = left + (right - left) / 2;
        if (num < my_list[mid]) {
            left = mid + 1;
        } else if (num > my_list[mid]) {
            right = mid;
        } else {
            cout << "FOUND";
            break;
        }

        cout  << my_list[left] << ":" << my_list[right] << "\n";
    }

    cout << "NUM: " << my_list[left-1] << " > " << num << " > " << my_list[right];

    return 0;
}