#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int n, five = 0, two = 0;
	cin >> n;
	int temp = n;
	while (n) {
		five += n / 5;
		n = n / 5;
	}
	while (temp) {
		two += temp / 2;
		temp = temp / 2;
	}

	if (five == 0 || two == 0) {
		cout << '0';
		return 0;
	}
	int ten = min(five, two);
	cout << ten;
}