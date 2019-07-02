//RGB거리
#include <iostream>
#include <algorithm>

using namespace std;

int home[100001][3];

int main() {
	int T;
	cin >> T;

	for (int i = 0; i < T; i++) {
		for (int j = 0; j < 3; j++) {
			cin >> home[i][j];
		}
	}
	for (int i = 1; i < T; i++) {
		for (int j = 0; j < 3; j++) {
			home[i][j] = min(home[i - 1][(j + 1) % 3], home[i - 1][(j + 2) % 3]) + home[i][j];
		}
	}

	int res = home[T - 1][0];
	res = min(res, home[T - 1][1]);
	res = min(res, home[T - 1][2]);
	cout << res;
}