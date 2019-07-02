//Count Circle Groups
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

int T, N, row, col, r;
vector< pair< pair<int, int>, int > > location;
vector< vector<int> > enemy;


bool overlap(pair<int, int> a, pair<int, int> b, int flag) {
	int compA = a.first + a.second;
	int compB = b.first + b.second;

	if (flag == 1) {
		if (compA > compB) return false; //안 겹치는 경우
		else return true;
	}
	else {
		if (compA < compB) return false;
		else return true;
	}
}


void linkEnemy(pair< pair<int, int>, int > rec1, pair< pair<int, int>, int > rec2, int i, int j) {
	int flag = 1;
	int a = rec1.first.first - rec2.first.first; //row1 - row2
	int b = rec1.first.second - rec2.first.second; //col1 - col2
	pair<int, int> A;
	pair<int, int> B;

	if (a > 0 && b >= 0) {
		// up & down
		A = { rec1.first.first - rec1.second, rec1.first.second }; //row1-R1, col1
		B = { rec2.first.first + rec2.second, rec2.first.second };
	}
	else if (a >= 0 && b < 0) {
		// right & left
		A = { rec1.first.first, rec1.first.second + rec1.second };
		B = { rec2.first.first, rec2.first.second - rec2.second };
	}
	else if (a <= 0 && b > 0) {
		// left & right
		A = { rec1.first.first, rec1.first.second - rec1.second };
		B = { rec2.first.first, rec2.first.second + rec2.second };
		flag = 0;
	}
	else if (a < 0 && b <= 0) {
		// down & up
		A = { rec1.first.first + rec1.second, rec1.first.second };
		B = { rec2.first.first - rec2.second, rec2.first.second };
		flag = 0;
	}

	if (overlap(A, B, flag)) { //마름모 겹치는 경우 진영 연결
		enemy[i].push_back(j);
		enemy[j].push_back(i);
	}
}

void bfs(int* visit, queue<int> q) {
	while (!q.empty()) {
		int temp = q.front();
		visit[temp] = 1;
		q.pop();

		for (int i = 0; i < enemy[temp].size(); i++) {
			int nextEnemy = enemy[temp][i];

			if (!visit[nextEnemy]) {
				q.push(nextEnemy);
			}
		}
	}
}

int main() {
	cin >> T;
	for (int k = 0; k < T; k++) {
		queue<int> q;
		int cnt = 0;

		cin >> N;
		
		enemy.resize(N);
		int *visit = new int[N];

		for (int i = 0; i < N; i++) {
			visit[i] = 0;
		}

		for (int m = 0; m < N; m++) {
			cin >> row >> col >> r;
			location.push_back({ {row, col}, r });
		}

		//O(N^2) -> O(NlogN)으로 바꿀 수 있는 방법은?
		//현재 : all linked node check

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				linkEnemy(location[i], location[j], i, j);
			}
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				q.push(i);
				bfs(visit, q);
				cnt++;
			}
		}
		
		cout << cnt << "\n";

		enemy.clear();
		location.clear();
	}
}

/* ------------------------------memory out ------------------------
int T, N, row, col, r;
int enemy[5001][5001];
int visit[5001][5001];
int dir[2][4] = { {0, 0, 1, -1}, {1, -1, 0, 0} };
int cnt;

queue < pair<int, int> > q;
queue< pair<int, int> >base;

void coloring(int centerRow, int centerCol, int radius) {
	
	for (int i = centerRow - radius; i <= centerRow + radius; i++) {
		
		int dist = radius - abs(centerRow - i);

		for (int j = centerCol - dist; j <= centerCol + dist; j++) {
			if (i >= 0 && i < 5001 && j >= 0 && j < 5001) {
				enemy[i][j] = 1;
			}
		}
	}
}

void bfs() {
	while (!q.empty()) {
		int row = q.front().first;
		int col = q.front().second;
		visit[row][col] = 1;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nextRow = row + dir[0][i];
			int nextCol = col + dir[1][i];
			if (nextRow >= 0 && nextRow < 5001 && nextCol >= 0 && nextCol < 5001) {
				if (!visit[nextRow][nextCol] && enemy[nextRow][nextCol] == 1) {
					q.push({ nextRow, nextCol });
				}
			}
		}
	}
}

int main() {
	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> row >> col >> r;
			coloring(row, col, r);
			base.push({ row, col });
		}

		while (!base.empty()) {
			int row = base.front().first;
			int col = base.front().second;
			base.pop();

			if (!visit[row][col]) {
				q.push({ row, col });
				bfs();
				cnt++;
			}
		}

		cout << cnt << '\n';
	}
}
*/