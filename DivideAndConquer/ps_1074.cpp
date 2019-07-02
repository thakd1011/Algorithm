#include <iostream>
#include <math.h>
#include <algorithm>

using namespace std;

int N, r, c;
int cnt;

int location(int startRow, int startCol, int n, int row, int col) {
	int size = pow(2, n);
	int midRow = startRow + size / 2;
	int midCol = startCol + size / 2;
	int a = midRow - row;
	int b = midCol - col;
	int nr, nc;

	if (startRow == row && startCol == col) {
		return cnt;
	}

	int flag = 0;
	if (a <= 0) {
		if (b <= 0) {
			nr = midRow;
			nc = midCol;
			flag = 4;
		}
		else {
			nr = midRow;
			nc = startCol;
			flag = 3;
		}
	}
	else {
		if (b <= 0) {
			nr = startRow;
			nc = midCol;
			flag = 2;
		}
		else {
			nr = startRow;
			nc = startCol;
			flag = 1;
		}
	}

	cnt += ((size * size) / 4) *(flag - 1);
	location(nr, nc, n - 1, row, col);
}

int main() {
	cin >> N >> r >> c;
	cout << location(0, 0, N, r, c);
}