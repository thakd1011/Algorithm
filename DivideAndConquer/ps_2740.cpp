#include <iostream>
#include <algorithm>

using namespace std;
int N, M, K;
int A[101][101];
int B[101][101];
int AB[101][101];

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> A[i][j];
		}
	}
	cin >> M >> K;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < K; j++) {
			cin >> B[i][j];
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < K; j++) {
			int sum = 0;
			for (int p = 0; p < M; p++) {
				sum += A[i][p] * B[p][j];
			}
			AB[i][j] = sum;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < K; j++) {
			cout << AB[i][j] << ' ';
		}
		cout << "\n";
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			A[i][j]=0;
		}
	}
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < K; j++) {
			B[i][j]=0;
		}
	}
}