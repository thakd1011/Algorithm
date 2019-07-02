#include <iostream>

using namespace  std;

// gcd(a, b) = gcd(a mod b, b) (a>b라고 가정할 때)
long long gcd(long long a, long long b) { //a>b일 때
	if (a%b == 0) {
		return b;
	}
	else {
		return gcd(b, a%b);
	}
}

// 최소공배수 = a*b/gcd(a,b)이다!
long long lcm(long long a, long long b) {
	return a * (b / gcd(a, b));
}

int main() {
	int T, M, N, x, y;
	cin >> T; //testcase

	for (int i = 0; i < T; i++) {
		cin >> M >> N >> x >> y;

		long long maximum = lcm(M, N);
		long long res = 0;

		while (1) {
			if (x == y) {
				res = x;
				break;
			}

			if (x < y) { //반복되는 수만큼 덧셈연산
				x += M;
			}
			else {
				y += N;
			}

			if (x > maximum && y > maximum) { //최소공배수 넘지 않을때까지만!
				res = -1;
				break;
			}
		}
		cout << res << '\n';
	}
}