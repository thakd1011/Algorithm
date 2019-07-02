//로봇청소기
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
// 재귀!!!!! 공간복잡도 O(nm), 시간복잡도 O(nm)

using namespace std;
int rowDir[4] = { -1, 0, 1, 0 }; // 북, 동, 남, 서
int colDir[4] = { 0, 1, 0, -1 };
int N, M;

struct robot {
	int r;
	int c;
	int dir;
};

int main() {
	cin >> N >> M;

	int** map = new int*[N];
	struct robot r1;

	for (int i = 0; i < N; i++) {
		map[i] = new int[M];
	}

	cin >> r1.r >> r1.c >> r1.dir;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}

	int flag = 1;
	int clean = 0;
	int cnt = 0;

	while (flag) {
		map[r1.r][r1.c] = 2; //현재 위치 청소
		cnt++;

		int nextDir = (r1.dir + 3) % 4; // 3->2->1->0
		int nextRow = r1.r + rowDir[nextDir];
		int nextCol = r1.c + colDir[nextDir]; // 다음 위치

		if (map[nextRow][nextCol] == 0) { //청소 안 한 공간이라면
			r1.dir = nextDir;
			r1.r = nextRow;
			r1.c = nextCol;
		}
		else { // 청소할 공간이 없는 경우
			r1.dir = nextDir;
			clean++;
		}

		if (clean == 4 || map[nextRow][nextCol] == 1) {//4방향 모두 청소가 된 경우
			r1.r = r1.r + rowDir[(r1.dir + 2) % 3];
			r1.c = r1.c + colDir[(r1.dir + 2) % 3];
			//방향은 유지하고 후진
			clean = 0;
		}
		else if (clean == 4 && map[r1.r + rowDir[(r1.dir + 2) % 3]][r1.c + colDir[(r1.dir + 2) % 3]] == 1) {
			flag = 0;
			clean = 0;
		}
	}
	cout << cnt;
}