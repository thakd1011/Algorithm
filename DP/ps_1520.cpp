//내리막길
#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int road[502][502];
int visit[502][502];
int dp[502][502];
int dir[2][4] = { {0, 1, 0, -1 },{1, 0, -1, 0} };

int dfs(int row, int col) {
	if (row == M && col == N) {
		return 1;
	}
	if (row<1 || row>M || col < 1 || col>N) {
		return 0;
	}

	if (dp[row][col] != -1) {//이전에 방문한 적이 있다!
		return dp[row][col];
	}

	dp[row][col] = 0; //방문한 경우
	for (int i = 0; i < 4; i++) {
		int nextRow = row + dir[0][i];
		int nextCol = col + dir[1][i];

		if (road[nextRow][nextCol] < road[row][col]) {
			dp[row][col] += dfs(nextRow, nextCol);
		}
	}

	return dp[row][col];
}

int main() {
	cin >> M >> N; //col=M row=N; M by N 행렬
	for (int i = 1; i <= M; i++) {
		for (int j = 1; j <= N; j++) {
			scanf("%d", &road[i][j]);
			//killing point...
			dp[i][j] = -1; //방문 했던 곳과 안 했던 곳을 구분하기 위함!
		}
	}

	cout << dfs(1, 1) << '\n';

}