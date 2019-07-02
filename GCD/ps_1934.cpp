#include <iostream>
#include <algorithm>

using namespace std;

int gcd(int a, int b) {
	if (b == 0) {
		return a;
	}
	else {
		return gcd(b, a%b);
	}
}

int main() {
	int t;

	cin >> t;
	for (int i = 0; i < t; i++) {
		int a, b, A, B;
		cin >> a >> b;
		
		A = max(a, b);
		B = min(a, b);

		int GCD = gcd(A, B);

		cout << (a * b) / GCD << '\n';
	}
}