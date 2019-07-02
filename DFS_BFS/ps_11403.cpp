#include <iostream>
#include <vector>
#include <queue>

using namespace std;

queue<int> q;

void bfs(int start, vector< vector<int> > graph, int** check) {
	while (!q.empty()) {
		int temp = q.front();
		q.pop();
		int size = graph[temp].size();

		for (int i = 0; i < graph[temp].size(); i++) {
			int num = graph[temp][i];
			if (check[start][num] == 0) {
				q.push(num);
				check[start][num] = 1;
			}
		}
	}
}

int main() {
	//가중치 없는 방향 그래프 G
	int vertex;
	cin >> vertex;

	vector< vector <int> > graph(vertex);
	int** check = new int*[vertex];

	for (int i = 0; i < vertex; i++) {
		check[i] = new int[vertex];
	}

	// check vertex array init
	for (int i = 0; i < vertex; i++) {
		for (int j = 0; j < vertex; j++) {
			check[i][j] = 0;
		}
	}
	int temp;
	for (int i = 0; i < vertex; i++) {
		for (int j = 0; j < vertex; j++) {
			scanf("%1d", &temp);
			if (temp == 1) {
				graph[i].push_back(j);
			}
		}
		
	}

	for (int i = 0; i < vertex; i++) {
		q.push(i);
		// **not check i
		bfs(i, graph, check);
	}
	for (int i = 0; i < vertex; i++) {
		for (int j = 0; j < vertex; j++) {
			cout << check[i][j] << ' ';
		}
		cout << '\n';
	}
}