#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int R, C;
int rowDir[4] = { 1, -1, 0, 0 };
int colDir[4] = { 0, 0, 1, -1 };

int check[50][50];
char map[50][50];
queue< pair<int, int> > water;
queue< pair<int, int> > dochi;

pair<int, int> start;
pair<int, int> dest;

int bfs() {
	while (!dochi.empty()) { 
		// dochi가 갈 수 있는 모든 경로를 탐색했을 경우
		// 번져나가는 물의 위치 먼저 체크
		int water_size = water.size();
		for (int i = 0; i < water_size; i++) {
			int tempRow = water.front().first;
			int tempCol = water.front().second;
			water.pop();
			
			for (int j = 0; j < 4; j++) { // 4방향 확인
				int nextRow = tempRow + rowDir[j];
				int nextCol = tempCol + colDir[j];

				if (nextRow < R && nextRow >= 0 && nextCol < C && nextCol >= 0) {
					if (map[nextRow][nextCol] == '.') {
						// 물 번져나감 처리
						map[nextRow][nextCol] = '*';
						water.push(make_pair(nextRow, nextCol));
					}
				}
			}
		}

		int dochi_size = dochi.size();
		for (int j = 0; j < dochi_size; j++) { // size는 미리 fix시켜놓는 게 포인트!
			int dochiRow = dochi.front().first;
			int dochiCol = dochi.front().second;
			dochi.pop();

			if (dochiRow == dest.first && dochiCol == dest.second) {
				//도착한 경우
				return check[dochiRow][dochiCol] - 1;
			}

			for (int i = 0; i < 4; i++) {//고슴도치의 이동방향의 경로 확인
				int nextRow = dochiRow + rowDir[i];
				int nextCol = dochiCol + colDir[i];

				if (check[nextRow][nextCol] == 0) { // 갔던 길은 다시 안 가는 것이 메모리 초과 안 나는 핵심!!
					if (nextRow < R && nextRow >= 0 && nextCol < C && nextCol >= 0) {
						if (map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == 'D') { // 물, 돌이 없을 때
							// 도치 이동 및 이동횟수 저장
							dochi.push(make_pair(nextRow, nextCol));
							check[nextRow][nextCol] = check[dochiRow][dochiCol] + 1;
						}
					}
				}
			}
		}
	}
	// while문을 빠져나왔다
	// == 도치가 갈 수 있는 모든 경로를 탐색했지만 도착지를 발견 못했다.
	return -1;
}

int main() {
	cin >> R >> C;

	for (int i = 0; i < R; i++) {
		scanf("%s", map[i]);
		for (int j = 0; j < C; j++) {
			if (map[i][j] == 'S') {
				// 고슴도치의 초기 위치 저장 & bfs 시작점으로 설정
				start.first = i;
				start.second = j;
				dochi.push(make_pair(i, j));
				check[i][j] = 1;
			}
			if (map[i][j] == 'D') {
				dest.first = i;
				dest.second = j;
			}
			if (map[i][j] == '*') {
				// 물 위치 큐에 저장
				water.push(make_pair(i, j));
			}
		}
	}

	int result = bfs();
	if (result == -1) {
		cout << "KAKTUS";
	}
	else {
		cout << result;
	}
}