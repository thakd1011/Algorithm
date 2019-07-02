//반도체 설계
#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>

using namespace std;
int d[40001];
int c[40001];

int main() {
	int n ;
	cin >> n;

	for (int i = 1; i <= n; i++) {
		scanf("%1d", &c[i]);
	}

	// 증가하는 부분수열 개수 찾는 것과 동일!
	for (int i = 1; i <= n; i++) {
		d[i] = 1;
		if (i != 1) {
			for (int j = 1; j < i; j++) {
				if (c[j] < c[i]) {
					d[i] = max(d[i], d[j] + 1);
				}
			}
		}
	}

	int m = 0;
	for (int i = 1; i <= n; i++) {
		m = max(m, d[i]);
	}
	cout <<  m;

}