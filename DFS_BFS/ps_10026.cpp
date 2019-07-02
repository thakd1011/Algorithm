#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;

void dfs(int x, int y, char** color, int** check) {
	char temp_color = color[x][y];
	check[x][y] = 1;
	// up
	if (y - 1 >= 0 && color[x][y - 1] == temp_color && check[x][y - 1] == 0) {
		dfs(x, y - 1, color, check);
	}
	// down
	if (y + 1 < N && color[x][y + 1] == temp_color && check[x][y + 1] == 0) {
		dfs(x, y + 1, color, check);
	}
	// left
	if (x - 1 >= 0 && color[x - 1][y] == temp_color && check[x - 1][y] == 0) {
		dfs(x - 1, y, color, check);
	}
	// right
	if (x + 1 < N && color[x + 1][y] == temp_color && check[x + 1][y] == 0) {
		dfs(x + 1, y, color, check);
	}
}


int main() {
	cin >> N;
	char** color = new char*[N];
	int** check = new int*[N];

	for (int i = 0; i < N; i++) {
		color[i] = new char[N];
		check[i] = new int[N];
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			check[i][j] = 0;
		}
	}

	for (int i = 0; i < N; i++) {
		scanf("%s", color[i]);
	}
	
	int normal_cnt = 0;
	int abnormal_cnt = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (check[i][j] == 0) {
				dfs(i, j, color, check);
				normal_cnt++;
			}
		}
	}
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			check[i][j] = 0;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (color[i][j]=='G') {
				color[i][j] = 'R';
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (check[i][j] == 0) {
				dfs(i, j, color, check);
				abnormal_cnt++;
			}
		}
	}
	cout << normal_cnt << ' ' << abnormal_cnt;
}