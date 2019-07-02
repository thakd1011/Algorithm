#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
queue <int> bfs_q;

void bfs(int start, vector< vector<int> > graph, int* check) {
	// O(vertex + edge)
	bfs_q.push(start);
	check[start] = 1;

	while (!bfs_q.empty()) {
		int temp = bfs_q.front();
		bfs_q.pop();

		for (int i = 0; i < graph[temp].size(); i++) {
			int n = graph[temp][i];
			if (check[n] == 0) {
				bfs_q.push(n);
				check[n] = 1;
			}
		}
	}
}

int main() {
	int N, M, u, v;
	int cnt = 0;

	cin >> N >> M;
	
	int* check = new int[N + 1];
	vector < vector<int> > graph(N + 1);

	// input graph
	for (int i = 1; i <= M; i++) {
		scanf("%d %d", &u, &v);
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	
	for (int i = 1; i <= N; i++) {
		sort(graph[i].begin(), graph[i].end());
	}
	for (int i = 1; i <= N; i++) {
		check[i] = 0;
	}

	int start = 1, flag = 0;

	do {
		// 한 붓 그리기 될 때마다 cnt 증가
		if (check[start] == 0) {
			bfs(start, graph, check);
			cnt++;
		}
		else {
			flag = 1;
		}

		// 아직 지나오지 않은 점부터 다시 한붓그리기
		for (int i = 1; i <= N; i++) {
			if (check[i] == 0) {
				start = i;
				break;
			}
		}
	} while (flag == 0);

	cout << cnt;
}