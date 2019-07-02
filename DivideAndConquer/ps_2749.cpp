//fibonacci numbers 3
//피사노 주기 : fibo[i] % K를 구할 때, 결과값은 일정한 주기를 갖는다.
//K = 10^n이면, 피사노 주기는 15*10^(n-1)이다.
#include <iostream>
#include <algorithm>
#define MOD 1000000
#define M 1500000 //주기

using namespace std;

long long int arr[M + 1];
long long int N;

int main() {
	cin >> N;
	arr[0] = 0;
	arr[1] = 1;
	for (int i = 2; i < M; i++) {
		arr[i] = (arr[i - 1] + arr[i - 2]) % MOD;
	}
	while (N >= M) {
		N %= M;
	}
	cout << arr[N];
}