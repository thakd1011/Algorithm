//분수찾기
#include <iostream>

using namespace std;
long long num[1000001];
int main() {
	long long X;
	cin >> X;
	for (long long i = 1; i < 1000001; i++) {
		num[i] = num[i - 1] + i;
		if (X > num[i - 1] && X <= num[i]) {
			int temp = i;
			int a = X - num[i - 1];
			int b = i - (X - num[i - 1]) + 1;
			if (temp % 2 == 0) {
				cout << a << '/' << b;
			}
			else {
				cout << b << '/' << a;
			}
			break;
		}
	}
}