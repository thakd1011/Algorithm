//tomato
#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;
int time;
int visit[1001][1001];
int tomato[1001][1001];
int dirRow[4] = { 0, 0, -1, 1 };
int dirCol[4] = { -1, 1, 0, 0 };

queue< pair < pair<int, int>, int> > ripeTomato;

bool allRipe() {

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if(tomato[i][j] == 0) {
				return false;
			}
		}
	}
	return true;
}

int BFS() {

	while (!ripeTomato.empty()) {
		int tempRow = ripeTomato.front().first.first;
		int tempCol = ripeTomato.front().first.second;
		int tempTime = ripeTomato.front().second;

		time = max(tempTime, time);

		ripeTomato.pop();

		for (int i = 0; i < 4; i++) {
			int nextRow = tempRow + dirRow[i];
			int nextCol = tempCol + dirCol[i];

			if (nextRow > 0 && nextRow <= N && nextCol > 0 && nextCol <= M) {
				if (!visit[nextRow][nextCol]) {
					visit[nextRow][nextCol] = 1; //방문 체크

					if (tomato[nextRow][nextCol] == 0) {
						tomato[nextRow][nextCol] = 1;
						ripeTomato.push({ { nextRow, nextCol } , tempTime + 1});
					}
				}
			}
		}
	}

	if (allRipe()) {
		return time;
	}
	else {
		return -1;
	}
}

int main() {
	cin >> M >> N; //N = row, M = col
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			scanf("%d", &tomato[i][j]);

			if (tomato[i][j] == 1) { //익은 토마토 push
				ripeTomato.push( {{i, j}, 0} );
				visit[i][j] = 1;
			}
		}
	}

	cout << BFS();

}