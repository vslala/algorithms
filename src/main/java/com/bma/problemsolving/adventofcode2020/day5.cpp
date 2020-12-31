#include<iostream>
#include<vector>
#include<string>
#include<math.h>
#include<fstream>

using  namespace std;


int main () {
    string input = "FBFBBFFRLR";
    // 0101100
    // 32 + 8 + 4 = 44

    ifstream in("inputs/day5.txt");
    int a = 64;
    int row = 0, col = 0, seat_id = 0, highest_seat_id = INT_MIN;
    vector<int> seat_ids;

    while (getline(in, input)) {
        a = 64;
        row = 0;
        for (int i=0; i < input.length() - 3 ; i++) {
            if (input[i] == 'B') {
                row += a;
            }
            a = a / 2;
        }

        a = 4;
        col = 0;
        for (int i = input.length() - 3; i < input.length(); i++) {
            if (input[i] == 'R') {
                col += a;
            }
            a = a / 2;
        }

        seat_id = row * 8 + col;
        seat_ids.push_back(seat_id);    
        if (seat_id > highest_seat_id) {
            highest_seat_id = seat_id;
        }

    }

    sort(seat_ids.begin(), seat_ids.end());
    for (int i = 0; i < seat_ids.size() - 1; i++) {
        if (seat_ids[i + 1] - seat_ids[i] == 2) {
            cout << seat_ids[i] << " ";
        }
    }

    // cout << highest_seat_id;

    // part 1
    // int highest_seat_id = INT_MIN;
    // while (getline(in, input)) {
    //     char r_last, c_last;
    //     int r_low = 0, c_low = 0;
    //     int r_high = 127, c_high = 7;
    //     for (int i=0; i < input.size(); i++) {
    //         if (input[i] == 'F') {
    //             r_high = r_high - floor((r_high - r_low) / 2);
    //             r_last = 'F';
    //         } else if (input[i] == 'B') {
    //             r_low = r_low + ceil((r_high - r_low) / 2);
    //             r_last = 'B';
    //         } else if (input[i] == 'R') {
    //             c_low = c_low + ceil((c_high -  c_low) / 2);
    //             c_last = 'R';
    //         } else if (input[i] == 'L') {
    //             c_high = c_low + floor((c_high - c_low) / 2);
    //             c_last = 'L';
    //         }
    //     }
    //     int row, col;
    //     if (input[0] ==  'B') {
    //         row = max(r_low,r_high);
    //         col = max(c_low, c_high);
    //     } else {
    //         row = r_last == 'F' ? min(r_low,r_high): max(r_low, r_high);
    //         col = c_last == 'L' ? min(c_low, c_high): max(c_low, c_high);
    //     }
    //     int seat_id = row * 8 + col;
    //     // cout << endl << "r_low: " << r_low << ", r_high: " << r_high << ", c_low: " << c_low << ", c_high:" << c_high << endl; 
    //     // cout << row << "*" << 8 << "+" << col << "=" << seat_id;

    //     if (seat_id > highest_seat_id) highest_seat_id = seat_id;
    // }

    // cout << endl << "Highest Seat ID: " << highest_seat_id;

    // Part 2
    // vector<int> seat_ids;
    // int seat_index = 0;
    // int highest_seat_id = INT_MIN;
    // while (getline(in, input)) {
    //     char r_last, c_last;
    //     int r_low = 0, c_low = 0;
    //     int r_high = 127, c_high = 7;
    //     for (int i=0; i < input.size(); i++) {
    //         if (input[i] == 'F') {
    //             r_high = r_high - floor((r_high - r_low) / 2);
    //             r_last = 'F';
    //         } else if (input[i] == 'B') {
    //             r_low = r_low + ceil((r_high - r_low) / 2);
    //             r_last = 'B';
    //         } else if (input[i] == 'R') {
    //             c_low = c_low + ceil((c_high -  c_low) / 2);
    //             c_last = 'R';
    //         } else if (input[i] == 'L') {
    //             c_high = c_low + floor((c_high - c_low) / 2);
    //             c_last = 'L';
    //         }
    //     }
    //     int row, col;
    //     if (input[0] ==  'B') {
    //         row = max(r_low,r_high);
    //         col = max(c_low, c_high);
    //     } else {
    //         row = r_last == 'F' ? min(r_low,r_high): max(r_low, r_high);
    //         col = c_last == 'L' ? min(c_low, c_high): max(c_low, c_high);
    //     }
    //     int seat_id = row * 8 + col;
    //     seat_ids.push_back(seat_id);

    //     // cout << endl << "r_low: " << r_low << ", r_high: " << r_high << ", c_low: " << c_low << ", c_high:" << c_high << endl; 
    //     // cout << row << "*" << 8 << "+" << col << "=" << seat_id;

    //     if (seat_id > highest_seat_id) highest_seat_id = seat_id;

    // }

    // sort(seat_ids.begin(), seat_ids.end());
    // for (auto  id: seat_ids) {
    //     cout << id << ", ";
    // }
    // cout << endl;
    // for (int i=seat_ids.size(); i > 0; i--) {
    //     if (seat_ids[i] - seat_ids[i-1] > 1) {
    //         cout << seat_ids[i-1] << "-" << seat_ids[i] << endl;
    //     }
    // }

    return 0;
}