#include <iostream>
#include <vector>
#include <stack>
#include <queue>
using namespace std;

int main() {
	// search the graph using DFS&BFS algo.
	// caution: If vertex has many node, visit smaller number
	int vertex, edge, temp, start;
	int u, v;
	
	stack <int> dfs;
	queue <int> bfs;

	scanf("%d %d %d", &vertex, &edge, &start);

	int* check = new int[vertex + 1];
	int* result = new int[vertex + 1];
	
	for (int i = 1; i <= vertex; i++) {
		check[i] = 0;
	}

	
	// 2d array for graph
	int** graph = new int*[vertex + 1];
	for (int i = 1; i <= vertex; i++) {
		graph[i] = new int[vertex + 1];
	}
	for (int i = 1; i <= vertex; i++) {
		for (int j = 1; j <= vertex; j++) {
			graph[i][j] = 0;
		}
	}

	// input edge
	for (int i = 0; i < edge; i++) {
		scanf("%d %d", &u, &v);
		graph[u][v] = 1;
		graph[v][u] = 1;
	}

	// dfs searching
	temp = start;
	dfs.push(temp);
	check[temp] = 1;
	result[1] = dfs.top();

	int index = 2;
	int flag = 0;

	while (!dfs.empty()) {
		for (int i = 1; i <= vertex; i++) {
			if (graph[temp][i] == 1 && check[i] == 0) {
				dfs.push(i);
				result[index] = dfs.top();
				index++;
				check[i] = 1;
				temp = i;
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			dfs.pop();
			if (dfs.size() != 0) {
				temp = dfs.top();
			}
		}
		else {
			flag = 0;
		}
	}
	// dfs result print
	for(int i=1; i<=vertex; i++) {
		cout << result[i] << ' ';
	}

	// bfs searching
	cout << '\n';
	temp = start;
	for (int i = 1; i <= vertex; i++) {
		check[i] = 0;
	}

	bfs.push(temp);
	check[temp] = 1;

	result[1] = temp;
	index = 2;
	while (!bfs.empty()) {
		for (int i = 1; i <= vertex; i++) {
			if (graph[temp][i] == 1 && check[i] == 0) {
				bfs.push(i);
				check[i] = 1;
				result[index] = i;
				index++;
			}
		}
		bfs.pop();
		if (!bfs.empty()) {
			temp = bfs.front();
		}
	}
	for (int i = 1; i <= vertex; i++) {
		cout << result[i] << " ";
	}

	return 0;
}