#include<iostream>
#include<vector>

#define pb push_back

using namespace std;

class EqualStacks {
    vector<int> v1;
    vector<int> v2;
    vector<int> v3;

    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;

    inline int sum(vector<int> h1, int from) {
        int sum = 0;
        for (int i = from; i < h1.size(); i++) {
            sum += h1[i];
        }
        return sum;
    }

    public:
    EqualStacks(vector<int> v1, vector<int> v2, vector<int> v3) {
        this->v1 = v1;
        this->v2 = v2;
        this->v3 = v3;

        sum1 = sum(v1, 0);
        sum2 = sum(v2, 0);
        sum3 = sum(v3, 0);
    }

    void update(int p1, int p2, int p3) {
        sum1 = sum(v1, p1);
        sum2 = sum(v2, p2);
        sum3 = sum(v3, p3);
    }

    int min_sum() {
        int min_num = min(sum1, sum2);
        return min(min_num, sum3);
    }

    bool in_range(int p1, int p2, int p3) {
        return p1 < v1.size() && p2 < v2.size() && p3 < v3.size();
    }

    int sum_one() {
        return sum1;
    }

    int sum_two() {
        return sum2;
    }

    int sum_three() {
        return sum3;
    }

    bool is_equal() {
        return sum1 + sum2 + sum3;
    }
};

int equalStacks(vector<int> h1, vector<int> h2, vector<int> h3) {
    int p1 = 0, p2 = 0, p3 = 0;
    
    EqualStacks equal_stacks(h1,h2,h3);
    equal_stacks.update(p1,p2,p3);

    while (equal_stacks.in_range(p1, p2, p3)) {
        int min_sum = equal_stacks.min_sum();
        while  (equal_stacks.sum_one() > min_sum) {
            p1++;
            equal_stacks.update(p1,p2,p3);
        }
        while  (equal_stacks.sum_two() > min_sum) {
            p2++;
            equal_stacks.update(p1, p2, p3);
        }
        while  (equal_stacks.sum_three() > min_sum) {
            p3++;
            equal_stacks.update(p1, p2, p3);
        }

        if (equal_stacks.is_equal()) return equal_stacks.sum_one();
    }

    return equal_stacks.sum_one();
}
int main()  {
    vector<int> h1, h2, h3;
    int size1, size2, size3;
    cin >> size1 >> size2 >> size3;

    int  input;
    for (int  i=0; i < size1; i++) {
        cin >> input;
        h1.pb(input);
    }

    for (int  i=0; i < size2; i++) {
        cin >> input;
        h2.pb(input);
    }

    for (int  i=0; i < size3; i++) {
        cin >> input;
        h3.pb(input);
    }

    int height = equalStacks(h1, h2, h3);
    cout << endl;
    cout << height;
    
    return 0;
}