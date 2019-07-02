#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int combination[13]; // 6개짜리 조합

// start = 0, depth = 0부터 시작
void dfs(int start, int depth, int* set, int K) {
	// 집합의 각 원소에서 갈 수 있는 원소 -> 트리구조
	// depth가 6일 때까지만 탐색하고 종료
	if (depth == 6) {
		for (int i = 0; i < 6; i++) {
			cout << combination[i] << ' ';
		}
		cout << "\n";
		return;
	}
	else {
		for (int i = start; i < K; i++) {
			// combination은 depth에 따라 결정되므로 index = depth로 주는 것이 핵심!
			combination[depth] = set[i];
			dfs(i + 1, depth + 1, set, K);
		}
	}
}

int main() {
	int K;
	int set[13];

	while(1) {
		scanf("%d", &K);
		if (K == 0) {
			break;
		}
		else {
			for (int i = 0; i < K; i++) {
				cin >> set[i];
			}
			dfs(0, 0, set, K);
			cout << '\n';
		}
	}
}