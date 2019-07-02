#include <iostream>

using namespace std;
long long num[10000001];
int main() {
	int N;
	cin >> N;

	num[1] = 1;
	if (N == 1) {
		cout << num[1];
	}
	else {
		for (int i = 2; i < 1000000; i++) {
			num[i] = num[i - 1] + 6 * (i - 1);
			if (num[i - 1] < N && num[i] >= N) {
				cout << i;
				break;
			}
		}
	}
}