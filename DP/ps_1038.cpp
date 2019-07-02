//감소하는 수
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

queue<int> q;

int main() {
	int N, cnt = 0;
	cin >> N;


	if (N == 1022) { //감소하는 수는 9876543210이 끝! 이 이후로 감소하는 숫자 못만들어... -> 1022번째 감소하는 수가 98~~이것
		cout << 9876543210;
	}
	else if (N > 1022) {
		cout << -1;
	}
	else if (N <= 10) {
		cout << N;
	}
	else {
		for (int i = 1; i < 10; i++) {
			q.push(i);
			cnt++;
		}
		while (cnt < N) {
			int temp = q.front();
			int last = temp % 10;
			q.pop();

			for (int j = 0; j < last; j++) {
				q.push(temp * 10 + j);
				cnt++;

				if (cnt == N) { //현재 숫자가 N번째 감소하는 수라면 출력 후 종료
					cout << temp * 10 + j;
					break;
				}
			}
		}
	}
}