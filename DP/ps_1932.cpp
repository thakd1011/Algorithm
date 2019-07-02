//정수삼각형
#include <iostream>
#include <algorithm>

using namespace std;

int N;
int triangle[501][501];

int main() {
	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < i + 1; j++) {
			scanf("%d", &triangle[i][j]);
		}
	}
	for (int i = 0; i < N; i++) {
		if (i == 0) {
			continue;
		}
		for (int j = 0; j <= i; j++) {
			if (j == 0) {
				triangle[i][j] += triangle[i - 1][j];
				continue;
			}
			if (j == i) {
				triangle[i][j] += triangle[i - 1][j - 1];
				continue;
			}
			triangle[i][j] += max(triangle[i - 1][j - 1], triangle[i - 1][j]);
		}
	}

	int maximum = 0;
	for (int i = 0; i < N; i++) {
		maximum = max(maximum, triangle[N - 1][i]);
	}
	cout << maximum;
}