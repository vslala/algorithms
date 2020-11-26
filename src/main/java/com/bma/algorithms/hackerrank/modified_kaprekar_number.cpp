#include<iostream>

using namespace std;

int low;
int hi;

void test_cases() {
    cin >> low; cin.ignore();
    cin >> hi;  cin.ignore();
}

int num_len(int num) {
    int count = 0;
    while (num != 0) {
        num = num / 10;
        count++;
    }

    return count;
}

int find_sq(int num) {
    return num * num;
}

int pow[10] = {1, 1, 10, 100, 1000, 10000, 100000};
int find_digit_sum(int sq, int digits) {
    int r = 0, num = sq, right = 0, sum = 0;
    int mem[2 * digits];
    int mem_pt = 0;

    while (num != 0) {

        if (right >= digits) {
            right = 0;
            mem[mem_pt++] = sum;
            sum = 0;
        }

        r = num % 10;
        num = num / 10;
        right ++;

        sum = sum + r * pow[right];
    }

    if (right != 0) {
        mem[mem_pt++] = sum;
        sum = 0;
    }

    for (int i = 0; i < mem_pt; i++) {
        sum += mem[i];
    }

    return sum;

}

int main() {
    test_cases();

    int count = 0;
    int digits = 0;
    int sq = 0;
    int digit_sum = 0;
    for (int i = low; i < hi + 1; i++) {
        digits = num_len(i);
        sq = find_sq(i);
        digit_sum = find_digit_sum(sq, digits);

        if (digit_sum == i)  {
            count++;
            cout << i << " ";
        }
    }

    if (count == 0) {
        cout << "INVALID RANGE";
    }

    return 0;
}