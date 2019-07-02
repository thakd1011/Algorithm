//파도반 수열
#include <iostream>
#include <algorithm>

using namespace std;

int t, N;
long long arr[101];

int main() {
	arr[1] = 1;
	arr[2] = 1;
	arr[3] = 1;
	arr[4] = 2;
	arr[5] = 2;
	arr[6] = 3;

	for (int i = 7; i <= 101; i++) {
		arr[i] = arr[i - 1] + arr[i - 5];
	}

	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> N;
		cout << arr[N] << '\n';
	}
}