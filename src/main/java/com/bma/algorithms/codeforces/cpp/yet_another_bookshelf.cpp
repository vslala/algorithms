#include<iostream>
#include<string>

using namespace std;

int main() {
    string placementstr;
    int testcases = 0, shelfspace = 0;

    cin >>  testcases; cin.ignore();

    int placements[testcases][51];
    int result[testcases];

    for (int i=0; i<testcases;  i++) {
        cin >> shelfspace; cin.ignore();
        getline (cin, placementstr);

        int count = 0, total = 0;
        bool startcounting = false, registercount = false;
        
        for (int j = 0, k = 0; j < shelfspace; j++) {

            if (placementstr.at(k) == ' ') {
                k++;
            }
            
            if (placementstr.at(k) == '1') {
                if (startcounting) {
                    registercount = true;
                }
                startcounting = true;
            }

            else if (startcounting && placementstr.at(k) == '0') {
                count++;
            }

            if (registercount) {
                total += count;
                count = 0;
            }

            registercount = false;
            k++;
        }

        result[i] = total;
    }

    for (int i=0; i < testcases; i++) {
        cout << result[i] << "\n";
    }

    return 0;
}