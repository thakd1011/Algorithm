//미로탐색
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

const int MAX = 987654321;

int N, M; //N=row, M = column

int arr[101][101];
int visit[101][101];
int dirRow[4] = { 0, 0, 1, -1 };
int dirCol[4] = { 1, -1, 0, 0 };

queue< pair<int, int> > q;

//-> 메모리초과
void BFS() {
	while (!q.empty()) {
		int row = q.front().first;
		int col = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nextRow = row + dirRow[i];
			int nextCol = col + dirCol[i];
			
			if (nextRow > 0 && nextRow <= N && nextCol > 0 && nextCol <= M) {
				//인근에 0이 있지 않은 이상, 돌아갔다 온 길이 최소일 리가 없음
				if (!visit[nextRow][nextCol] && arr[nextRow][nextCol] == 1) {
					q.push({ nextRow, nextCol });
					visit[nextRow][nextCol] = visit[row][col] + 1;
				}
			}
		}
	}
}


//DFS = timeout
void DFS(int row, int col, int cnt) {
	if (row == N && col == M) {
		//res = min(res, cnt);
	}
	else {

		for (int i = 0; i < 4; i++) {
			int nextRow = row + dirRow[i];
			int nextCol = col + dirCol[i];
			
			if (row > 0 && row <= N && col > 0 && row <= M) {
				
				if (!visit[nextRow][nextCol] && arr[nextRow][nextCol] == 1) { //길이 있는 경우
					visit[nextRow][nextCol] = 1;
					DFS(nextRow, nextCol, cnt + 1);
					visit[nextRow][nextCol] = 0; //다시 0으로 초기화
				}

			}
		}
	}
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			scanf("%1d", &arr[i][j]);
		}
	}
	q.push({ 1, 1 });
	visit[1][1] = 1;

	//DFS(1, 1, 1);
	BFS();
	cout << visit[N][M];
}