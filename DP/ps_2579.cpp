//계단오르기
#include <iostream>
#include <algorithm>

using namespace std;

int stair[302];
int max_up[2][302];
int N;
//i안밟 = i-1밟(무조건)
//i밟 = i-1밟&i-2 안밟 | i-1안밟+i-2밟
//-> 이전 경우를 모두 유추하기 힘들다... 하

//max_up[0][i] = 1칸 연속으로 올라선 경우
//max_up[1][i] = jump해서 올라선 경우
int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		scanf("%d", &stair[i]);
	}

	max_up[1][1] = stair[1];
	max_up[2][1] = stair[1];

	for (int i = 2; i <= N; i++) {
		max_up[1][i] = max_up[2][i - 1] + stair[i]; //1칸만 올라서 i에 도착한 경우
		max_up[2][i] = max(max_up[1][i - 2], max_up[2][i - 2]) + stair[i];
	}
	cout << max(max_up[1][N], max_up[2][N]);
}