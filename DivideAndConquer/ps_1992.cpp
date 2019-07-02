//quad tree
#include <iostream>
#include <algorithm>

using namespace std;

int arr[65][65];
int N;

bool same(int r, int c, int size) {
	int num = arr[r][c];

	for (int i = r; i < r + size; i++) {
		for (int j = c; j < c + size; j++) {
			if (arr[i][j] != num) {
				return 0;
			}
		}
	}
	return 1;
}

void quadTree(int r, int c, int size) {
	if (same(r, c, size)) {
		printf("%d", arr[r][c]);
	}
	else {
		size /= 2;
		printf("(");
		quadTree(r, c, size);
		quadTree(r, c + size, size);
		quadTree(r + size, c, size);
		quadTree(r + size, c + size, size);
		printf(")");
	}
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &arr[i][j]);
		}
	}
	quadTree(0, 0, N);
}