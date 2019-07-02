#include <iostream>
#include <algorithm>

using namespace std;

long long int gcd(long long int a, long long int b) {
	if (b == 0) {
		return a;
	}
	else {
		return gcd(b, a%b);
	}
}

int main() {
	
		long long int a, b, A, B;
		cin >> a >> b;

		A = max(a, b);
		B = min(a, b);

		long long int GCD = gcd(A, B);

		cout << GCD << '\n';
		cout << (a * b) / GCD << '\n';
}