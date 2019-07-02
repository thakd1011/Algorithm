//연속합
#include <iostream>
#include <algorithm>
#define INF -987654321;

using namespace std;

int N;
int arr[100001];
int sum[100001];
int m;

int main() {
	m = INF;
	cin >> N;
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
		sum[i] += arr[i];
	}

	for (int i = 1; i < N; i++) {
		sum[i] = max(sum[i - 1] + arr[i], sum[i]);
		sum[i] = max(sum[i], arr[i]);
	}

	for (int i = 0; i < N; i++) {
		m = max(m, sum[i]);
	}
	/*
	//O(N^2) -> 시간초과 ^^
	for (int i = 0; i < N; i++) {
		int temp = arr[i];
		for (int j = i + 1; j < N; j++) {
			temp += arr[j];
			sum[j] = max(sum[j], temp);
		}
	}
	for (int i = 0; i < N; i++) {
		m = max(m, sum[i]);
	}
	*/
	cout << m;
}