#include <iostream>
#include <algorithm>
using namespace std;

int number[10];

int main() {
	int A, B, C, N, temp;
	cin >> A >> B >> C;
	N = (A * B) * C;

	while (N != 0) {
		temp = N % 10;
		number[temp]++;
		N /= 10;
	}
	for (int i = 0; i < 10; i++) {
		cout << number[i] << '\n';
	}
}