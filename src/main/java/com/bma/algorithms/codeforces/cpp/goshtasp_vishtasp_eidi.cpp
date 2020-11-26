#include<iostream>
#include<list>

using namespace std;

const int PRIMES_LENGTH = 1000;
int primes[PRIMES_LENGTH];
int* findprimes();
int searchprime(int num);

int main() {
    int num, temp, justless = 1, r, sum = 0;
    cin >> num;
    temp = num;
    findprimes();

    while (sum < num) {
        justless = searchprime(temp);
        r = temp - justless;
        temp = r;
        
        sum += justless;

        cout << justless;

        if (sum < num)
            cout << "+";
    }

    cout  << "=" << sum;

    return 0;
}

int* findprimes() {
    int primeindex = 0;
    primes[primeindex++] = 1;
    primes[primeindex++] = 1;

    int isprime = 0;
    for (int i=3; primeindex < PRIMES_LENGTH; i+= 2) {
        for (int num = 2; num < i/2 + 1; num++) {
            if (i % num == 0) {
                isprime = 1;
                break;
            }
        }

        if (isprime == 0) {
            primes[primeindex++] = i;
        }

        isprime  = 0;
    }

    return primes;
}

int searchprime(int num) {
    int  mid = PRIMES_LENGTH / 2;
    if (*(primes + mid) > num) {
        for (int i=0; i < mid; i++) {
            if (*(primes+i) > num) {
                return primes[i - 1] ;
            }
        }
    } else if (*(primes + mid) < num) {
        for  (int i=mid; i< num; i++){
            if (primes[i] > num) {
                return primes[i - 1];
            }
        }
    } 
        
    return primes[mid];
}