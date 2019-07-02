//주사위 굴리기
#include <iostream>
#include <algorithm>

using namespace std;
int dice[6] = { 0, 0, 0, 0, 0, 0 };
int rowDir[4] = { 0, 0, -1, 1 }; //동서북남
int colDir[4] = { 1, -1, 0, 0 };
//공간복잡도 O(1), 시간복잡도 O(n)


void swap(int a, int b) {
	int temp;
	temp = dice[a];
	dice[a] = dice[b];
	dice[b] = temp;
}

int main() {
	int N, M, row, col, K;
	int temp;
	int semi;

	scanf("%d %d %d %d %d", &N, &M, &row, &col, &K);
	int** map = new int* [N];
	for (int i = 0; i < N; i++) {
		map[i] = new int[M];
	}
	

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	//명령 input
	for (int i = 0; i < K; i++) {
		cin >> temp;
		int nextRow = row + rowDir[temp - 1];
		int nextCol = col + colDir[temp - 1];

		if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
			//방향에 따라서 주사위 이동시킴
			switch (temp) {
			case 1: //동(오른쪽)
				semi = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[2];
				dice[2] = dice[5];
				dice[5] = semi;
				break;
			case 2: //서(왼쪽)
				semi = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[2];
				dice[2] = dice[4];
				dice[4] = semi;
				break;
			case 3: //북(상)
				semi = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[2];
				dice[2] = dice[1];
				dice[1] = semi;
				break;
			case 4: //남(하)
				semi = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[3];
				dice[3] = semi;
				break;
			}
			if (map[nextRow][nextCol] == 0) {
				map[nextRow][nextCol] = dice[2];
			}
			else {
				dice[2] = map[nextRow][nextCol];
				map[nextRow][nextCol] = 0;
			}
			cout << dice[0] << '\n';
			row = nextRow;
			col = nextCol;
		}
	}
}