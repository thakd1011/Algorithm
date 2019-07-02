#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
queue <int> q;

void dfs(int start, int* check, vector< vector<int> > graph, int set){
	// 집합은 1 혹은 2로, 자신을 호출한 정점과 다른 집합으로 들어가야 함
	check[start] = set;
	for (int i = 0; i < graph[start].size(); i++) {
		int next = graph[start][i];
		if (check[next] == 0) {
			dfs(next, check, graph, 3 - set);
		}
	}
}

void bfs(int* check, vector< vector<int> >graph, int set) {
	while (!q.empty()) {
		int temp = q.front();
		q.pop();
		for (int i = 0; i < graph[temp].size(); i++) {
			int next = graph[temp][i];
			if (check[next] == 0) {
				q.push(next);
				// front의 set와 같지 않아야 하는 것이 포인트
				check[next] = 3 - check[temp];
			}
		}
	}
}

int main() {
	int K;
	int V, E;
	int u, v;

	scanf("%d", &K);

	for (int i = 0; i < K; i++) {
		scanf("%d %d", &V, &E);

		vector< vector<int> > graph(V + 1);
		int* check = new int[V + 1];
		
		for (int j = 1; j <= V; j++) {
			check[j] = 0;
		}

		for (int j = 0; j < E; j++) {
			scanf("%d %d", &u, &v);
			graph[u].push_back(v);
			graph[v].push_back(u);
		}

		// point!! 연결되지 않은 이분 그래프 여러개가 있을 수 있음.
		// check 안 된 정점이 없어야 한다

		/* dfs로 풀 때
		for (int j = 1; j <= V; j++) {
			if (check[j] == 0) {
				dfs(j, check, graph, 1);
			}
		}
		*/

		//bfs 풀이
		for (int j = 1; j <= V; j++) {
			if (check[j] == 0) {
				q.push(j);
				check[j] = 1;
				bfs(check, graph, 1);
			}
		}
		
		
		// 이분 그래프는 같은 집합에 있는 정점 사이에 간선이 없어야 한다.
		int flag = 1;
		for (int j = 1; j <= V; j++) {
			for (int k = 0; k < graph[j].size(); k++) {
				int next = graph[j][k];
				if (check[j] == check[next]) {
					flag = 0;
					break;
				}
			}
		}

		if (flag == 1) {
			cout << "YES" << '\n';
		}
		else {
			cout << "NO" << '\n';
		}
	}
}