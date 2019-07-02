#include <iostream>
#include <stdio.h>
#include <algorithm>

using namespace std;
int arr[1002];
int D[1002];

int main() {
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		scanf("%d", &arr[i]);
	}

	for (int i = 1; i <= n; i++) {
		D[i] = arr[i];
		for (int j = 1; j <= i; j++) {
			D[i] = max(D[i - j] + arr[j], D[i]);
		}
	}
	cout << D[n];
}