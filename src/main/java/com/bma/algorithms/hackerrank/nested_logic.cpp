#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int day = 0;
const int month = 1;
const int year = 2;

int main() {
    
    int actual[3];
    int expected[3];
    
    for (int i=0; i < 3;  i++) scanf("%d", (actual + i));
    for (int i=0; i < 3;  i++) scanf("%d", (expected + i));
    
    
    if (actual[year] > expected[year]) {
        printf("%d", 10000);
    }
    
    else if (actual[month] > expected[month]) {
        printf("%d", (500 * (actual[month] - expected[month])));
    }
    
    else if (actual[day] > expected[day]) {
        printf("%d", (15 * (actual[day] - expected[day])) );
    }
    
    return 0;
}