#include<iostream>
#include<unordered_map>
#include<unordered_set>
#include<string>
#include<vector>
#include<queue>

using namespace std;

int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
    unordered_set<string> wordSet(wordList.begin(), wordList.end());
    
    for (auto word: wordSet) cout << word << " ";
    cout << endl;

    if (wordSet.find(endWord) == wordSet.end()) {
	return 0;
    }

    queue<string> curr;
    curr.push(beginWord);
    int level = 1;
    while (!curr.empty()) {
	string word = curr.front();
	curr.pop();
	
	
	for (int i=0; i < word.size(); i++) {
	    string currWord = word;
	    for (char c = 'a'; c <= 'z'; c++) {
		currWord[i] = c;

		if (currWord.compare(word) == 0) continue;

		cout << currWord << ",";

		if (currWord.compare(endWord) == 0) return level;

		if (wordSet.find(currWord) != wordSet.end()) {
		    cout << currWord << ",";
		    curr.push(currWord);
		    wordSet.erase(currWord);
		}

	    }
	    cout << endl;
	}

	level++;
    }    

    return 0;
}

/*
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
*/
int main() {
    
    vector<string> wordList = {"hot","dot","dog","lot","log","cog"};
    string beginWord = "hit";
    string endWord = "cog";

    int ans = ladderLength(beginWord, endWord, wordList);

    cout << "ANS:" << ans << endl;

    return 0;
}
