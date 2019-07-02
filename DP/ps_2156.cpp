//포도주 시식
#include <iostream>
#include <algorithm>

using namespace std;

int N, maximum;
int arr[10001];
int grape[2][10001];
//i번째 포도주 먹었을 때 -> i-1마시고 i-2 안마셔야함 | i-1안마셨을 때
//i번째 포도주 안먹었을 때 -> i-1마셨을 때 | i-1 안마셨을 때

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
	}
	for (int i = 0; i < N; i++) {
		if (i == 0) {
			grape[0][i] = 0; //i번째 포도주를 안 마셨을 때
			grape[1][i] = arr[i]; //i번째 포도주를 마셨을 때
			continue;
		}
		if (i == 1) {
			grape[0][i] = grape[1][i] = max(grape[0][i - 1], grape[1][i - 1]);
		}
		grape[0][i] = max(grape[1][i - 1], grape[0][i - 1]);
		grape[1][i] = max(grape[0][i - 2] + arr[i - 1] + arr[i], grape[0][i - 1] + arr[i]);
	}
	maximum = max(grape[0][N - 1], grape[1][N - 1]);
	cout << maximum;
}