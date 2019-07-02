#include <iostream>
#include <algorithm>

using namespace std;
int land[1000][1000];
int value[1000][1000];
int upper[2][1000];

int main() {
	int N, M;

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &land[i][j]);
		}
	}

	//(0,0)에서 시작!
	value[0][0] = land[0][0];

	//첫 줄 최대값은 자기 자신과 왼쪽에서 온 최대값의 합과 같다!
	for (int j = 1; j < M; j++) {
		value[0][j] = land[0][j] + value[0][j - 1];
	}

	//(i, j)로 가는 탐사 최대값은
	// 방향은 아래->오른쪽 / 아래->왼쪽 이런 식으로 진행됨
	//(i-1, j) - 상 / (i,j-1) -  좌 / (i, j+1) - 우 에서 온 최대값 + 자기자신 중 최대값!
	for (int i = 1; i < N; i++) {

		for (int j = 0; j < M; j++) {// 위에서 온 경우
			upper[0][j] = value[i - 1][j] + land[i][j];
			upper[1][j] = value[i - 1][j] + land[i][j];
		}
		
		for (int j = 1; j < M; j++) {//위쪽&왼쪽 방향에서 온 경우
			//위쪽에서 그대로 내려온것 vs 위쪽에서 내려오고 & 왼쪽 값까지 더한 것
			upper[0][j] = max(upper[0][j], upper[0][j - 1] + land[i][j]);
		}
		
		for (int j = M - 2; j >= 0; j--) {//위쪽&오른쪽 방향에서 온 경우
			// 가장 끝값은 위에서 오는 경우 뿐!
			upper[1][j] = max(upper[1][j], upper[1][j + 1] + land[i][j]);
		}
		for (int j = 0; j < M; j++) {
			value[i][j] = max(upper[0][j], upper[1][j]);
		}
	}

	cout << value[N-1][M-1];
}