#include <iostream>

using namespace std;

int main() {
	int T, H, W, N;

	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> H >> W >> N;
		int horizontal, vertical;
		// 1열부터 차례대로 수직으로 줄세워 넣는다!
		if (N%H == 0) { //꼭대기 층인 경우!
			vertical = H;
			horizontal = N / H;
		}
		else {
			vertical = N - (N / H)*H;
			horizontal = N / H + 1;
		}
		cout << vertical * 100 + horizontal << '\n';
	}
}