//virus
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

int N, t;
int start, dest;
int visit[101];
int cnt;


vector<vector<int> > com(101);
queue <int> q;

void bfs(vector< vector<int> > com) {
	while (!q.empty()) {
		int temp = q.front();
		q.pop();

		for (int i = 0; i < com[temp].size(); i++) {
			int nextCom = com[temp][i];
			if (!visit[nextCom]) {
				visit[nextCom] = 1;
				q.push(nextCom);
			}
		}
	}
}

int main() {
	cin >> N >> t;

	visit[1] = 1;

	for (int i = 0; i < t; i++) {
		cin >> start >> dest;
		com[start].push_back(dest);
		com[dest].push_back(start);
	}
	q.push(1);
	bfs(com);
	
	for (int i = 2; i <= N; i++) {
		//cout << visit[i] << ' ';
		if (visit[i] == 1) {
			cnt++;
		}
	}
	cout << cnt;
}