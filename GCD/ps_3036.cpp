#include <iostream>
#include <algorithm>

using namespace std;
//12 3 8 4 14
int arr[100];
int GCD[100];

int gcd(int a, int b) {
	if (b == 0) {
		return a;
	}
	else {
		return gcd(b, a%b);
	}
}

int main() {
	int N;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 1; i < N; i++) {
		GCD[i] = gcd(max(arr[0], arr[i]), min(arr[0], arr[i]));
	}
	for (int i = 1; i < N; i++) {
		cout << arr[0] / GCD[i] << '/' << arr[i] / GCD[i] << '\n';
	}
}