#include<iostream>
#include<vector>

using namespace std;


class Solution {
public:

    double memo[26][26][101];
    
    double f(int N, int K, int r, int c){
        
        if(r<0 or c<0 or r>=N or c>=N) return 0;
        if(K==0) return 1;
        
        if(memo[r][c][K]) return memo[r][c][K];
        
        double D =
        f(N,K-1,r-2,c-1) + f(N,K-1,r-2,c+1) + f(N,K-1,r+2,c-1) +
        f(N,K-1,r+2,c+1) + f(N,K-1,r-1,c+2) + f(N,K-1,r+1,c+2) + 
        f(N,K-1,r-1,c-2) + f(N,K-1,r+1,c-2);
        
        
        return memo[r][c][K]=(D)*(0.125);
    }
    
    
    double knightProbability(int N, int K, int r, int c) {
        
        return f(N,K,r,c);
    }
};

int main() {
    
    Solution s;
    double output = s.knightProbability(3,3,0,0);
    cout << output;
    return 0;
}
