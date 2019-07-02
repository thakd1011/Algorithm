//LCS2 - Longest Common Subsequence, 최장 공통 부분 수열
#include <iostream>
#include <algorithm>
#include <string>
#include <utility>
#include <vector>
#include <queue>

using namespace std;

string s1, s2, longest;
int word[1001][1001];

int main() {
	cin >> s1 >> s2;
	int a = s1.length();
	int b = s2.length();

	for (int i = 1; i <= a; i++) {
		for (int j = 1; j <= b; j++) {
			if (s1.at(i - 1) == s2.at(j - 1)) {
				word[i][j] = word[i - 1][j - 1] + 1;
			}
			else {
				word[i][j] = max(word[i - 1][j], word[i][j - 1]);
			}
		}
	}
	cout << word[a][b] << '\n'; //최장 공통 수열의 길이 출력!
}

	/*
	//어떤 문자가 들어갔는지 역추적!
	while (word[a][b] != 0) {
		if (word[a][b] == word[a - 1][b]) {
			a--;
		}
		else if (word[a][b] == word[a][b - 1]) {
			b--;
		}
		else {
			longest = s1.at(a - 1) + longest;
			a--;
			b--;
		}
	}
	cout << longest << '\n';
}
*/

/*
//최장 공통 부분 수열 -> ㅎㅎ...시간초과 ㅎㅎ...

using namespace std;
char s1[1001];
char s2[1001];
int min_len, len1, len2;
string res, temp;
vector< pair<string, int> > v1;
vector< pair<string, int> > v2;
queue< pair<string, int> > q1;
queue< pair<string, int> > q2;


int main() {
	//&&연산해서 있는 것만 출력하면 되는거 아닐까
	scanf("%s", s1);
	scanf("%s", s2);
	for (int i = 0; s1[i] != NULL; i++) {
		v1.push_back({ string(1,s1[i]),i });
		//cout <<"초기 벡터 = "<< v1[i].first  << ", size = "<<v1.back().first.size() << '\n';
		len1++;
	}
	for (int i = 0; s2[i] != NULL; i++) {
		v2.push_back({ string(1,s2[i]), i });
		//cout << "초기 벡터 = " << v2[i].first << '\n';
		len2++;
	}
	c
	for (int i = 0; i < v1.size(); i++) {
		for (int j = 0; j < v2.size(); j++) {
			if (v1[i].first.compare( v2[j].first) == 0) {
				q1.push({ v1[i] });
				q2.push({ v2[j] });
				min_len = 1;
				res = "" + v1[i].first;
				break;
			}
		}
	}
	v1.clear();
	v2.clear();
	//cout <<"first v1 size = "<< v1.size() << "\n";

	do {
		while (!q1.empty()) {
			pair <string, int> temp = q1.front();
			q1.pop();
			for (int i = temp.second + 1; i < len1; i++) {
				v1.push_back({ temp.first + string(1, s1[i]), i });
				//cout << "push temp_string1 = " << temp.first + s1[i] << '\n';
			}
		}
		while (!q2.empty()) {
			pair <string, int> temp = q2.front();
			q2.pop();
			for (int i = temp.second + 1; i < len2; i++) {
				v2.push_back({ temp.first + string(1, s2[i]), i });
				//cout << "push temp_string2 = " << temp.first + s2[i] << '\n';
			}
		}

		for (int i = 0; i < v1.size(); i++) {
			for (int j = 0; j < v2.size(); j++) {
				if (v1[i].first.compare( v2[j].first) == 0) {
					//cout << "equal v1 = " << v1[i].first << '\n';
					q1.push({ v1[i] });
					q2.push({ v2[j] });
					min_len = v1[i].first.size();
					res = "" + v1[i].first;
					break;
				}
			}
		}
		v1.clear();
		v2.clear();
	} while (!q1.empty() && !q2.empty());
	cout << min_len << '\n' << res;

}
*/