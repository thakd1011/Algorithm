//헨리
#include <iostream>
#include <algorithm>

using namespace std;

int T;
long long a, b, x;

int gcd(int a, int b) {
	if (b == 0) {
		return a;
	}
	else {
		gcd(b, a % b);
	}
}

int main() {
	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> a >> b;
		while (a != 1) {
			x = (b / a) + 1;
			a = a * x - b;
			b = b * x;
			long long div = gcd(a, b);
			a = a / div;
			b = b / div;
		}
		x = b / a;
		cout << x << '\n';
	}
}