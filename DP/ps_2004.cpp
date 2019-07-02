#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int count(long long k, int num) {
	int cnt = 0, temp = k;
	while (k) {
		cnt += k / num;
		k = k / num;
	}
	return cnt;
}

int main() {
	long long n, m, value, cnt = 0;

	cin >> n >> m;
	if (n == 0 || m == 0) {
		cout << 0;
		return 0;
	}

	cnt = min(count(n, 2) - count(m, 2) - count(n - m, 2), count(n, 5) - count(m, 5) - count(n - m, 5));
	cout << cnt;
}