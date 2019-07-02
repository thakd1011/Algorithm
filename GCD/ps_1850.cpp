#include <iostream>
#include <algorithm>

using namespace std;

long long gcdOne(long long a, long long b) {
	if (b == 0) {
		return a;
	}
	else {
		return gcdOne(b, a%b);
	}
}

int main() {
	long long a, b, temp;
	cin >> a >> b;

	temp = gcdOne(max(a, b), min(a, b));
	for (int i = 0; i < temp; i++) {
		cout << 1;
	}
}