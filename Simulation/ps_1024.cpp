//수열의 합

#include <iostream>
#include <algorithm>

using namespace std;

long long N;
int L, start;
int arr[101];

int main() {
	cin >> N >> L;
	for (int i = 1; i <= 100; i++) {
		arr[i] = arr[i - 1] + i - 1; //0도 포함되는 연속된 정수
	}

	if (N < arr[L]) {
		cout << -1;
	}

	// 좀 더 쉽게 예외처리 하는 방법 : L(L-1)/2 = 0~(L-1)까지의 합임을 이용하자!
	if (L == 2) { //2인 경우는 예외다! arr[2] = 0+1 = 1이어서 아래 조건에 걸리지 않음..
		if(N==2){
			cout << -1;
			return 0;
		}
		else if (N % 2 == 0) {
			L++;
		}
		else {
			cout << N / 2 << ' ' << N / 2 + 1;
			return 0;
		}
	}

	while (N >= arr[L]) {
		if ((N - arr[L]) % L == 0) {
			long long sum = arr[L];

			while (N != sum) {
				//cout << "sum = " << sum << ", L = " << L << '\n';
				sum += L;
				start++;
			}
			//cout << "start = " << start << ", sum = " << sum << ", L = " << L << '\n';
			for (int i = start; i < start + L; i++) {
				cout << i << ' ';
			}
			return 0;
		}
		else {
			L++;
			//cout << "L = " << L << '\n';
		}
		if (N < arr[L] || L > 100) {
			//cout << "arr[L] = " << arr[L] << '\n';
			cout << -1;
			return 0;
		}
	}
}