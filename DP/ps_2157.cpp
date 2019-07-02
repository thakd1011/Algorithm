#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
#include <queue>

using namespace std;
const int INF = 987654321;

int N, M, K, map[301][301];
vector<pair<int, int>> v[301];

int food(int x, int y) {
	if (x > 1 && y == 1) return -INF;
	if (x > 1 && !map[x][y]) {
		map[x][y] = -INF;
		for (auto it : v[x]) {
			map[x][y] = max(map[x][y], food(it.first, y - 1) + it.second);
		}
	}
	return map[x][y];
}

int main() {
	cin >> N >> M >> K;
	int start, dest, val;
	for (int i = 0; i < K; i++) {
		scanf("%d %d %d", &start, &dest, &val);
		if (start < dest) {
			v[dest].push_back({start, val});
		}
	}
	cout << food(N, M);
}
/*
vector< vector <pair<int, int> > > meal(301);

struct trip {
	int start;
	int cnt;
	int value;
};

queue <trip> q;


int main() {
	cin >> N >> M >> K;
	int start, dest, val;
	
	for (int i = 1; i <= K; i++) {
		scanf("%d %d %d", &start, &dest, &val);
		if (start < dest) {
			meal[start].push_back(make_pair(dest, val));
		}
	}

	//중복되는 경로 중 최대값으로 넣어줌
	for (int i = 1; i <= N; i++) {
		sort(meal[i].begin(), meal[i].end());

		for (int j = 0; j + 1 < meal[i].size(); j++) {
			if (meal[i][j].first == meal[i][j + 1].first && meal[i][j].second < meal[i][j + 1].second) {
				meal[i].erase(meal[i].begin() + j);
				j--;
			}
		}
		meal[i].erase(unique(meal[i].begin(), meal[i].end()), meal[i].end());
	}

	int max_val = 0;
	struct trip t1;
	t1.start = 1;
	t1.cnt = 1;
	t1.value = 0;

	q.push(t1);

	while (!q.empty()) {
		struct trip temp = q.front();
		max_val = max(max_val, temp.value);
		q.pop();

		if (temp.start == N && temp.cnt == M) {
			cout << max_val;
			break;
		}
		else if (temp.start == N && temp.cnt != M) {
			continue;
		}
		else if (temp.start != N && temp.cnt == M) {
			continue;
		}
		else {

			for (int i = 0; i < meal[temp.start].size(); i++) {
				struct trip next;
				next.start = meal[temp.start][i].first;
				next.cnt = temp.cnt + 1;
				next.value = temp.value + meal[temp.start][i].second;
				q.push(next);
			}
		}
	}
}
*/

/*
int check[301][301]; // 도시_i(행)에 j번째(열)로 방문했을 때 먹는 기내식의 최대값
int meal[301][301];

// top-down 방식으로 접근 재귀호출
int food(int temp_city, int num) {
	if (num == M) { //재귀로 들어왔는데 M번째 도시인 경우 -> M+1번으로 함수 돌아야 하므로 에러
		return -INF;
	}
	if (temp_city == N) {
		return 0; //N번째 도시에 도착했으면 종료
	}
	
	int val = check[temp_city][num]; //temp도시가 j번째 방문한 도시일 때의 value값
	if (val != 0) { //이미 방문했던 도시일 경우
		return val;
	}
	else {
		for (int i = temp_city + 1; i <= N; i++) {//오른쪽으로만 이동
			if (meal[temp_city][i]) { //항로가 있을 경우
				val = max(val, meal[temp_city][i] + food(i, num + 1));
			}
		}
		return val;
	}
}
int main() {
	cin >> N >> M >> K;
	int start, dest, val;
	for (int i = 0; i < K; i++) {
		scanf("%d %d %d", &start, &dest, &val);
		if (start < dest) {
			meal[start][dest] = max(meal[start][dest], val);
		}
	}
	cout << food(1, 1);
}
*/