#include<iostream>
#include<stack>
#include<string>
#include<vector>

using  namespace std;

class Solution {
public:
    int evalRPN(vector<string>& tokens) {
	int a = 0;
	int b = 0;
	stack<int> operands;
	for (auto token: tokens) {
		if(token.compare("+") == 0) {
		    b = operands.top();
		    operands.pop();
		    a = operands.top();
		    operands.pop();
		    operands.push(a + b);

		    continue;
		}
		else if (token.compare("-") == 0) {
		    b = operands.top();
		    operands.pop();
		    a = operands.top();
		    operands.pop();
		    operands.push(a - b);
		    
		    continue;
		}
		else if (token.compare("*") == 0) {
		    b = operands.top();
		    operands.pop();
		    a = operands.top();
		    operands.pop();
		    operands.push(a * b);
	    
		    continue;
		}
		else if (token.compare("/") == 0) {
		    b = operands.top();
		    operands.pop();
		    a = operands.top();
		    operands.pop();
		    operands.push(a / b);
	    
		    continue;
		}
		else {
		    operands.push(stoi(token));
		    continue;
		}
	}

	return operands.top();
    }
};

int main() {
    Solution solve;

    vector<string> tokens = {"2","1","+","3","*"};
    int result = solve.evalRPN(tokens);
    cout << result;
    return 0;
}
