#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
int T;
vector < vector<int> > cycle(1002);
int arr[1001];
queue<int> q;

void bfs(int *visit) {
	while (!q.empty()) {
		int temp = q.front();
		q.pop();
		visit[temp] = 1;

		int next = cycle[temp][0];
		if (!visit[next]) {
			q.push(next);
		}
	}
}

int main() {
	cin >> T;
	for (int i = 0; i < T; i++) {
		int N;

		cin >> N;
		int num, cnt = 0;
		int *visit = new int[N + 1];

		for (int j = 1; j <= N; j++) {
			cin >> num;
			arr[j] = num;
			visit[j] = 0;
		}

		for (int j = 1; j <= N; j++) {
			cycle[j].push_back(arr[j]);
		}

		for (int j = 1; j <= N; j++) {
			if (!visit[j]) {
				q.push(j);
				bfs(visit);
				cnt++;
			}
		}
		cout << cnt << '\n';

		for (int j = 0; j < cycle.size(); j++) {
			cycle[j].clear();
		}
	}
}