#include <iostream>
#include <algorithm>

using namespace std;

int arr[10];

int main() {
	long long N;
	int n, m = 0;

	cin >> N; //N<=1000000;
	if (N == 0) { //N!=0으로 체크하니까 0은 따로 빼줘야함
		cout << 1;
	}
	else {
		while (N != 0) {
			n = N % 10;
			if (n == 9) {
				n = 6;
			}
			arr[n] += 1;
			N = N / 10;
		}
		arr[6] = (arr[6] + 1) / 2;

		for (int i = 0; i < 10; i++) {
			m = max(arr[i], m);
		}
		cout <<  m;
	}
}