#include <iostream>
#include <algorithm>
#include <stdio.h>
#include <string>

using namespace std;

int arr[5002];
int D[5002];
int MOD = 1000000;

int main() {
	string s;
	string sub_s;
	int temp;
	getline(cin, s);


	for (int i = 1; i <= s.length(); i++) {
		arr[i] = s[i - 1] - '0';
	}

	if (arr[1] == 0) {
		cout << '0';
		return 0;
	}
	D[0] = 1;
	D[1] = 1;
	for (int i = 2; i <= s.length(); i++) {
		if (arr[i] != 0) {
			D[i] = (D[i] + D[i - 1]) % MOD;
		}
		temp = arr[i - 1] * 10 + arr[i];
		if (temp >= 10 && temp <= 26) {
			D[i] = (D[i] + D[i - 2]) % MOD;
		};
	}
	cout << D[s.length()];
}