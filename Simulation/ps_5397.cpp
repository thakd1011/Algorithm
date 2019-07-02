//키로커
#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
char temp[1000000];
//커서를 기준으로 왼쪽 문자열은 s1스택에, 오른쪽 문자열은 s2스택에!
//핵심은 s1, s2 스택이 비어있는 지 확인하고 push, pop 해야함

int main() {
	int T;
	cin >> T;

	for (int i = 0; i < T; i++) {
		int idx = 0;

		vector<char> v;
		stack<char> s1;
		stack<char> s2;

		scanf("%s", temp);
		for(int j=0; temp[j]!=NULL; j++) {
			if (temp[j] == '<') {
				if (!s1.empty()) {
					char t = s1.top();
					s1.pop();
					s2.push(t);
				}
			}
			else if (temp[j] == '>') {
				if (!s2.empty()) {
					char t = s2.top();
					s2.pop();
					s1.push(t);
				}
			}
			else if (temp[j] == '-') {
				if (!s1.empty()) {
					s1.pop();
				}
			}
			else {
				s1.push(temp[j]);
			}
		}
		while (!s2.empty()) {
			char t = s2.top();
			s2.pop();
			s1.push(t);
		}
		while (!s1.empty()) {
			v.push_back(s1.top());
			s1.pop();
		}
		for (int j = v.size()-1; j >= 0; j--) {
			cout << v[j];
		}
		cout << '\n';
	}
}