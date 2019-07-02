#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
int N;

void dfs(int x, int y, int** house, int** check, int cnt) {
	// 상,하,좌,우 체크 후 1인 경우 -> 다시 dfs 호출
	check[x][y] = cnt;

	//up check
	if (y - 1 >= 0 && house[x][y - 1] == 1 && check[x][y - 1] == 0) {
		dfs(x, y - 1, house, check, cnt);
	}
	// bottom check
	if (y + 1 < N && house[x][y+1] == 1 && check[x][y + 1] == 0) {
		dfs(x, y + 1, house, check, cnt);
	}
	// left check
	if (x - 1 >= 0 && house[x - 1][y] == 1 && check[x - 1][y] == 0) {
		dfs(x - 1, y, house, check, cnt);
	}
	// right check
	if (x + 1 < N && house[x + 1][y] == 1 && check[x + 1][y] == 0) {
		dfs(x + 1, y, house, check, cnt);
	}
}

int main() {
	cin >> N;
	int** house = new int*[N];
	int** check = new int*[N];

	for (int i = 0; i < N; i++) {
		house[i] = new int[N];
		check[i] = new int[N];
	}
	
	// data input and check array initialize
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &house[i][j]);
			check[i][j] = 0;
		}
	}

	// 처음 1이 등장하는 지점에서부터 탐색 시작
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (house[i][j] == 1 && check[i][j] == 0) {
				cnt++;
				dfs(i, j, house, check, cnt);
			}
		}
	}

	vector<int> same_house(cnt, 0);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int num = check[i][j];
			if (num != 0) {
				same_house[num - 1]++;
			}
		}
	}
	cout << cnt << '\n';
	
	//오름차순으로 정렬하여 출력
	sort(same_house.begin(), same_house.end());
	for (int i = 0; i < cnt; i++) {
		cout << same_house[i] << '\n';
	}
}