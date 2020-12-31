#include<iostream>
#include<string>

using namespace std;

void swap (int * arr, int index1, int index2) {
    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
}

int quicksort(int* arr, int low, int high) {
    int mid = low + (high - low)/2;
    int pivot = high - 1;
    swap(arr, mid, pivot);

    int i = low, j = low;
    while (i < pivot) {
        if (arr[i] > arr[pivot]) {
            swap(arr, i, j);
            j++;
        }
        i++;
    }

    swap(arr, j, pivot);

    return j;
}

int main() {
    bool isEmpty = true, issame = true;
    int testcases = 0, barrels = 0, pourings = 0;
    cin >> testcases; cin.ignore();
    
    int result[testcases];
    string testinputstr;
    for (int i=0; i < testcases; i++) {
        cin >> barrels >> pourings;
        if (pourings < barrels)  {
            pourings += 1;  
        }

        int barrelcapacityarr[barrels];
        for (int k=0; k < barrels; k++) {
            cin >> barrelcapacityarr[k];
            
            if (issame && k > 0 && barrelcapacityarr[k] != barrelcapacityarr[k - 1])  {
                issame  = false;
            }
            if (isEmpty && barrelcapacityarr[k] != 0) {
                isEmpty = false;
            }
        }

        if (issame) {
            int sum = 0;
            for (int i = 0; i < pourings; i++) {
                sum += barrelcapacityarr[i];
            }
            result[i] = sum;
            continue;
        }
        if (isEmpty) {
            result[i] = 0;
            continue;
        }

        int times =  0;
        int pivot = -1;
        while (pivot != pourings  && times < barrels) {
            pivot = quicksort(barrelcapacityarr, 0, barrels);

            if (pivot == pourings) {
                break;
            }

            if (pivot < pourings) {
                pivot = quicksort(barrelcapacityarr, pivot, barrels);
            } else if (pivot > pourings) {
                pivot = quicksort(barrelcapacityarr, 0, pivot);
            }

            times++;
        }

        int sum = 0;
        for (int i = 0; i < pourings; i++) {
            sum  += barrelcapacityarr[i];
        }

        result[i] = sum;
    }

    for (int i = 0; i < testcases; i++) {
        cout << result[i] << "\n";
    }
    
}