//쉬운 계단 수
#include <iostream>
#include <algorithm>
#define M 1000000000;

using namespace std;

int N;
long long arr[101][10];
long long sum;

int main() {
	cin >> N;
	
	if (N == 1) {
		cout << 9;
		return 0;
	}

	for (int i = 1; i < 10; i++) {
		arr[1][i] = 1;
	}
	for (int i = 2; i <= N; i++) {
		for (int j = 0; j < 10; j++) {
			if (j == 0) {
				arr[i][j] = arr[i - 1][j + 1];
			}
			else if (j == 9) {
				arr[i][j] = arr[i - 1][8];
			}
			else {
				arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j + 1]);
			}
			arr[i][j] %= M;
		}
	}
	for (int i = 0; i < 10; i++) {
		sum += arr[N][i];
	}
	cout << sum % M;
}