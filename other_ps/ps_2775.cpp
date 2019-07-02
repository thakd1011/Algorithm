#include <iostream>
using namespace std;

int arr[15][15];

int main() {
	int T, k, n;
	cin >> T;

	for (int i = 0; i < 15; i++) {
		arr[0][i] = i;
	}

	for (int i = 1; i < 15; i++) {
		arr[i][1] = 1;
		for (int j = 2; j < 15; j++) {
			arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
		}
	}

	for(int i=0; i<T; i++) {
		int sum = 0;
		cin >> k >> n; //k층, n호
		cout << arr[k][n] << '\n';
	}
}