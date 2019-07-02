#include <iostream>
#include <algorithm>

using namespace std;

int scale[8];

int main() {

	for (int i = 0; i < 8; i++) {
		cin >> scale[i];
	}

	if (scale[0] == 1) {
		for (int i = 1; i < 8; i++) {
			if (scale[i - 1] > scale[i]) {
				cout << "mixed" << '\n';
				return 0;
			}
		}
		cout << "ascending";
		return 0;
	}
	else if (scale[0] == 8) {
		for (int i = 1; i < 8; i++) {
			if (scale[i - 1] < scale[i]) {
				cout << "mixed" << '\n';
				return 0;
			}
		}
		cout << "descending";
		return 0;
	}
}