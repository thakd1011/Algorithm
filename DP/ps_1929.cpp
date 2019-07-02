#include <iostream>
#include <algorithm>
using namespace std;

int prime[1000001];


int main() {
	int M, N;
	scanf("%d %d", &M, &N);

	if (N == 1) {
		return 0;
	}
	//prime[1] = 0;
	for (int i = 2; i <= 1000000; i++) {
		prime[i] = 1;
	}
	for (int i = 2; i * i <= 1000000; i++) {
		if (prime[i]) {
			for (int j = i * i; j <= 1000000; j += i) {
				prime[j] = 0;
			}
		}
	}
	for (int i = M; i <= N; i++) {
		if (prime[i]) {
			printf("%d\n", i);
		}
	}
}