//jump
#include <iostream>
#include <algorithm>

using namespace std;
int map[1001][1001];
long dp[1001][1001]; //2^63범위이므로 long타입
int check[1001][1001];
int N, cnt;

int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> map[i][j];
		}
	}
	dp[1][1] = 1;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (!(i == N && j == N) && dp[i][j] != 0) {
				int right = j + map[i][j];
				int down = i + map[i][j];
				if (right <= N) {
					dp[i][right] += dp[i][j];
				}
				if (down <= N) {
					dp[down][j] += dp[i][j] ;
				}
			}
		}
	}

	cout << dp[N][N];
}