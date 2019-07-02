//파일합치기
#include <iostream>
#include <vector>
#include <algorithm>
#define INF 987654321;

using namespace std;

int T, K;
int fiction[501];
int value[501][501];

int main() {
	cin >> T;

	for (int i = 0; i < T; i++) {
		cin >> K;
		for (int j = 1; j <= K; j++) {
			int v;
			scanf("%d", &v);
			fiction[j] = fiction[j - 1] + v;
		}

		for (int k = 1; k < K; k++) {
			for (int i = 1; i <= K - k; i++) {
				value[i][i + k] = INF;
				for (int j = i; j < i + k; j++) {
					value[i][i + k] = min(value[i][i + k], value[i][j] + value[j + 1][i + k]);
				}

				value[i][i + k] += fiction[i + k] - fiction[i - 1];
			}
		}
		cout << value[1][K]<<'\n';
	}
}
/*
int T, K;
int fiction[501];

// 상위 파일은 하위 두 개 파일의 합!
int file_sum(int start, int end, int sum) {
	if (start == end || start > end) {
		return 0;
	}

	int right, left, temp_right, temp_left, diff, pivot;
	
	right = sum - fiction[start]; //가장 초기의 right, left값
	left = sum - right;

	if (right <= left) {
		pivot = start;
	}
	else {
		for (int i = 1; i <= end - start; i++) {
			temp_right = right - fiction[start + i];
			temp_left = sum - temp_right;

			if (temp_right <= temp_left) {
				if (temp_left - temp_right < right - left) {
					pivot = start + i;
					right = temp_right;
					left = temp_left;
				}
				else {
					pivot = start + i - 1;
				}
				break;
			}
			right = temp_right;
			left = temp_left;
		}
	}
	cout << "start = " << start << ",pivot = " << pivot << ",end = " << end <<'\n';
	return sum + file_sum(start, pivot, left) + file_sum(pivot + 1, end, right);
}
int main() {
	cin >> T;

	for (int i = 0; i < T; i++) {
		int sum = 0;
		cin >> K;
		for (int j = 0; j < K; j++) {
			scanf("%d", &fiction[j]);
			sum += fiction[j];
		}

		cout << file_sum(0, K - 1, sum)<<'\n';
	} 
}
*/