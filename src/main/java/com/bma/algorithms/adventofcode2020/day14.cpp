#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include<unordered_map>
#include<unordered_set>
#include<set>
#include<map>
#include<queue>
#include<math.h>

using namespace std;

#define ll long long int

string dec_to_bit(ll decimal) {
    string dec_to_bit = bitset<36>(decimal).to_string();
    //cout << endl << dec_to_bit;
    return dec_to_bit;
}

unsigned long  mask_and_sum(ll val, string mask) {
    ll new_value = 0;
    int pos = 0;
    reverse(mask.begin(), mask.end());
    string new_val = dec_to_bit(val);
    reverse(new_val.begin(), new_val.end());
    for (int i=0; i < 36; i++) {
	char mask_bit = mask[i];
	char val_bit = new_val[i];

	if (mask_bit == 'X'){
	    if (val_bit == '1')
		new_value  += pow(2, pos);
	}

	else if (mask_bit == '1') {
	    new_value += pow(2, pos);
	}

	else if (mask_bit == '0') {
	    // pass
	}

	else {
	    assert(false);
	}

	pos++;
    }
    
    cout << "New Value: " << new_value << endl;
    return new_value;
}

int main (int argv, char** argvs) {
    ifstream in("inputs/day14.txt");
    vector<string> lines;
    string line;
    
    // mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX
    ll sum = 0;
    string mask = "";
    unordered_map<int, unsigned long> mem_val;

    
    while (getline(in, line)) {
	if (line.find("mask") == 0) {
	    mask = line.substr(line.find("=") + 2);
	    cout << endl << "Mask: "  << mask << endl;
	} else {
	    assert(mask.size() == 36);
	    int open_bracket_pos = line.find("[");
	    int close_bracket_pos = line.find("]");
	    int mem = stoi(line.substr(open_bracket_pos + 1, close_bracket_pos));
	    ll val = stoi(line.substr(line.find("=") + 2));
	    val = mask_and_sum(val, mask);
	    mem_val[mem] = val;
	}
    }
    
    for (auto mv: mem_val) {
	sum  += mv.second;
    }

    cout << endl << "Sum:" << sum;

    in.clear();
    in.close();
    return 0;
}
