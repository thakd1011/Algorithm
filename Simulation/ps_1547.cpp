//공
#include <iostream>
#include <algorithm>

using namespace std;

int ball[3] = { 1,2,3 };

void swap(int a, int b) {
	int i, j;
	for (int k = 0; k < 3; k++) {
		if (ball[k] == a) {
			i = k;
		}
		if (ball[k] == b) {
			j = k;
		}
	}
	int temp = ball[i];
	ball[i] = ball[j];
	ball[j] = temp;
}

int main() {
	int M, a, b;
	cin >> M;
	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		swap(a, b);
	}
	cout << ball[0];
}