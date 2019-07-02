#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <utility>

using namespace std;
int T, N, E;
int a, b, c;

struct atom {
	int temp_v;
	int max_value;
	vector<int> path;
};

queue <atom> q;
struct atom res;

/*
void dfs(int start, vector< vector< pair<int, int> > > cave, int* value, int* check, int cnt, vector<int> &path) {
	if (res < cnt) {
		res = cnt;
		path.clear();

		for (int i = 1; i <= N; i++) {
			if (check[i] == 1) {
				path.push_back(i);
			}
		}
	}

	for (int i = 0; i < cave[start].size(); i++) {
		int next_cave = cave[start][i].first;
		int next_cave_cost = cave[start][i].second;
		if (check[next_cave] == 0) {
			check[next_cave] = 1;
			// 얻을 수 있는 이득 = 동굴 탐사 이익 - 소모되는 비용
			dfs(next_cave, cave, value, check, cnt + value[next_cave] - next_cave_cost, path);
			check[next_cave] = 0; // 백트래킹 해야하므로 다시 0으로 처리
		}
	}
}

*/

void bfs(vector< vector< pair<int, int> > > cave, int* value) {

	while (!q.empty()) {
		if (res.max_value < q.front().max_value) {
			res.max_value = q.front().max_value;
			res.path.clear();
			res.path.assign(q.front().path.begin(), q.front().path.end());
		}

		int temp = q.front().temp_v;
		for (int i = 0; i < cave[temp].size(); i++) {
			struct atom a;
			a.max_value = q.front().max_value + value[cave[temp][i].first] - cave[temp][i].second;
			a.temp_v = cave[temp][i].first;
			a.path.assign(q.front().path.begin(), q.front().path.end());
			a.path.push_back(a.temp_v);
			q.push(a);
		}
		q.pop();
	}

}
int main() {

	cin >> T;

	for (int i = 0; i < T; i++) {
		cin >> N >> E;

		int* value = new int[N + 1];
		int* check = new int[N + 1];
		vector<int> path(N + 1);

		vector < vector <pair<int, int> > > cave(N + 1);

		for (int j = 1; j <= N; j++) {
			check[j] = 0;
			path[j] = 0;
		}

		for (int j = 1; j <= N; j++) {
			scanf("%d", &value[j]);
		}

		for (int j = 0; j < E; j++) {
			cin >> a >> b >> c;
			// 더 깊이 있는 동굴만 탐사 가능 -> 방향성 있음
			cave[a].push_back(make_pair(b, c));
		}

		// 시작은 무조건 1번 동굴부터
		/*
		check[1] = 1;
		dfs(1, cave, value, check, value[1], path);
		
		*/
		struct atom a1;
		a1.temp_v = 1;
		a1.max_value = value[1];
		a1.path.push_back(1);

		res.max_value = 0;

		q.push(a1);
		bfs(cave, value);
		
		cout << res.max_value << ' ' << res.path.size() << '\n';
		for (int j = 0; j < res.path.size(); j++) {
			cout << res.path[j] << ' ';
		}
		cout << '\n';
		
		res.max_value = 0;
		res.path.clear();
	}
}