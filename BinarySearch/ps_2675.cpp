#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		char s[20];
		int n;
		scanf("%d %s", &n, s);
		for (int j = 0; s[j] != NULL; j++) {
			for (int k = 0; k < n; k++) {
				cout << s[j];
			}
		}
		cout << '\n';
	}
}