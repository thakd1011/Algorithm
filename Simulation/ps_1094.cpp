#include <iostream>
#include <vector>

using namespace std;

vector <int> bar;

int main() {
	int X, sum = 0;
	cin >> X;
	bar.push_back(64);

	while (sum != X) {
		// 갖고 있는 막대의 길이 모두 더함
		sum = 0;
		for (int i = 0; i < bar.size(); i++) {
			sum += bar[i];
		}

		if (sum > X) {
			int temp_sum = 0;
			int temp_bar = bar.back();
			bar.pop_back();

			int short_bar = temp_bar / 2;

			// 우선 하나를 버리고 계산
			bar.push_back(short_bar);

			for (int i = 0; i < bar.size(); i++) {
				temp_sum += bar[i];
			}
			if (temp_sum < X) { // X보다 작으면 버리지 않고 갖고 있는다.
				bar.push_back(short_bar);
			}
		}
	}
	cout << bar.size();
}