#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
queue <int> bfs_q;

void dfs(int start, vector< vector <int> > graph, int* check) {
	cout << start << ' ';
	check[start] = 1;

	for (int i = 0; i < graph[start].size(); i++) {
		int temp = graph[start][i];
		if (check[temp] == 0) { // not check vertex
			dfs(temp, graph, check);
		}
	}
}

void bfs(int start, vector< vector <int> > graph, int* check) {
	int temp;

	bfs_q.push(start);
	check[start] = 1;

	while(!bfs_q.empty()) {
		temp = bfs_q.front();
		cout << temp << ' ';
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
	int vertex, edge, start, temp;

	cin >> vertex >> edge >> start;

	vector < vector <int> > graph(vertex + 1);
	int* check = new int[vertex + 1];

	for (int i = 1; i <= vertex; i++) {
		check[i] = 0;
	}

	for (int i = 0; i < edge; i++) {
		int u, v;
		scanf("%d %d", &u, &v);
		graph[u].push_back(v);
		graph[v].push_back(u);
	}

	for (int i = 1; i <= vertex; i++) {
		sort(graph[i].begin(), graph[i].end());
	}

	dfs(start, graph, check);
	cout << '\n';

	for (int i = 1; i <= vertex; i++) {
		check[i] = 0;
	}

	bfs(start, graph, check);
}