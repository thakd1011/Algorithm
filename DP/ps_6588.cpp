#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int *prime = new int[1000002];

int main() {
	int n;
	for (int i = 0; i <= 1000000; i++) {
		prime[i] = 1;
	}

	//에라토스테네스의 체
	for (int i = 2; i*i <= 1000000; i++) {
		//i제곱이 n보다 작을때까지! 소수는 자기 자신까지만 약수이므로
		//자기자신으로 한 번 더 나눌 수 있으면 소수 아님
		if (prime[i]) {
			for (int j = i * i; j <= 1000000; j += i) {
				prime[j] = 0;
			}
		}
	}
	while (1) {
		scanf("%d", &n);
		if (n == 0) {
			break;
		}

		for (int i = 3; i < (n + 3) / 2 + 1; i++) {
			if (i == n - 1) {
				printf("Goldbach's conjecture is worng.\n");
			}
			if (prime[i] && prime[n - i]) {//소수이면 빼보기				
				printf("%d = %d + %d\n", n, i, n - i);
				break;
			}
		}
	}

}