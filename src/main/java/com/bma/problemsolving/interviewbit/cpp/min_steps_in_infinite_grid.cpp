#include<iostream>
#include<string>
#include<vector>
#include<math.h>

using namespace std;

class Coordinate {
    int x = 0;
    int y = 0;

    public:
	Coordinate(int x, int y) {
	    this->x = x;
	    this->y = y;
	}

	bool operator < (const Coordinate &coord) {
	    return pow(this->x,2) + pow(this->y,2) < pow(coord.getX(), 2) + pow(coord.getY(),  2);
	    
	}

	int getX() {
	    return this->x;
	}

	int getY() {
	    return this->y;
	}
};

class Cartesian {
    vector<Coordinate> coordinates;

    public:  
	void plot(Coordinate coordinate) {
	    coordinates.push_back(coordinate);
	}

	void print() {
	    for (auto coordinate: coordinates) {
		cout << "X: " << coordinate.getX() << ", B:" << coordinate.getY() << endl;
	    }
	}

	void sortAsc() {
	    sort(coordinates.begin(), coordinates.end());
	}
};

int coverPoints(vector<int> &A, vector<int> &B) {
    Cartesian frame;

    for  (int i=0; i < A.size(); i++) {
	Coordinate coord(A[i], B[i]);
	frame.plot(coord);
    }
    
    frame.sortAsc();
    frame.print();



    int steps = 0;
    return steps;
}

int main() {
    vector<int> coordinates1 = {0,1,1};
    vector<int> coordinates2 = {0,1,2};
    int totalSteps = coverPoints(coordinates1, coordinates2);

    cout << "Ans:" << totalSteps << endl;

    return 0;
}
