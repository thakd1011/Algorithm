#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int T, M, N, K;


void dfs(int y, int x, int** land, int** check) {
	// 상,하,좌,우 체크 후 1인 경우 -> 다시 dfs 호출
	check[y][x] = 1;

	//up check
	if (y - 1 >= 0 && land[y - 1][x] == 1 && check[y - 1][x] == 0) {
		dfs(y - 1, x, land, check);
	}
	// bottom check
	if (y + 1 < N && land[y + 1][x] == 1 && check[y + 1][x] == 0) {
		dfs(y + 1, x, land, check);
	}
	// left check
	if (x - 1 >= 0 && land[y][x - 1] == 1 && check[y][x - 1] == 0) {
		dfs(y, x - 1, land, check);
	}
	// right check
	if (x + 1 < M && land[y][x + 1] == 1 && check[y][x + 1] == 0) {
		dfs(y, x + 1, land, check);
	}
}

int main() {
	cin >> T;

	for (int i = 0; i < T; i++) {

		cin >> M >> N >> K;
		// N by M;

		int** land = new int*[N];
		int** check = new int*[N];
		int cnt = 0;

		for (int j = 0; j < N; j++) {
			land[j] = new int[M];
			check[j] = new int[M];
		}

		for (int j = 0; j < N; j++) {
			for (int p = 0; p < M; p++) {
				land[j][p] = 0;
				check[j][p] = 0;
			}
		}

		for (int j = 0; j < K; j++) {
			int x, y;
			cin >> x >> y;
			land[y][x] = 1;
		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (land[y][x] == 1 && check[y][x] == 0) {
					cnt++;
					dfs(y, x, land, check);
				}
			}
		}
		cout << cnt << '\n';
	}
}