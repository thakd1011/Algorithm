#include <iostream>
#include <algorithm>

using namespace std;
int fibo[41];

// 0 1 2 3 4 5
// 0이 호출되는 횟수
// 1이 호출되는 횟수
int arr[2][41];

int main() {
	int T, N;

	arr[0][0] = 1;
	arr[1][0] = 0;
	arr[0][1] = 0;
	arr[1][1] = 1;

	for (int i = 2; i < 41; i++) {
		arr[0][i] = arr[0][i - 1] + arr[0][i - 2];
		arr[1][i] = arr[1][i - 1] + arr[1][i - 2];
	}

	cin >> T;

	for (int i = 0; i < T; i++) {
		cin >> N;
		cout << arr[0][N] << ' ' << arr[1][N] << '\n';
	}

}