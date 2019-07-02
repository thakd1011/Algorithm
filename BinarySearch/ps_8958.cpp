#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		char arr[81];
		int sum = 0, temp = 0, index = 0;

		scanf("%s", arr);
		while (arr[index] != NULL) {
			if (arr[index] == 'O') {
				while (arr[index] == 'O') {
					temp++;
					index++;
					sum += temp;
				}
				temp = 0;
			}
			else {
				index++;
			}
		}
		cout << sum << '\n';
		sum = 0;
	}
}