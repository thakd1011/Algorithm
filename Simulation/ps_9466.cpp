#include <iostream>
#include <algorithm>

using namespace std;

int check[100001];
int cycle[100001];
int student[100001];
int cnt;

void dfs(int temp) {
	check[temp] = 1;
	int next = student[temp];

	if (check[next] == 0) {
		dfs(next);
	}
	else {
		if (cycle[next] == 0) { // 아직 안 끝났을 경우!
			for (int i = next; i != temp; i = student[i]) {
				cnt++;
			}
			cnt++; // 자기 자신도 포함시켜줘야함
		}
	}
	cycle[temp] = 1;
}
int main() {
	int t, num;
	cin >> t;

	for (int i = 0; i < t; i++) {
		for (int j = 0; j < 100001; j++) {
			check[j] = 0;
			cycle[j] = 0;
		}
		cin >> num;
		for (int j = 1; j <= num; j++) {
			cin >> student[j];
		}
		cnt = 0;
		for (int j = 1; j <= num; j++) {
			if (check[j] == 0) {
				dfs(j);
			}
		}
		cout << num - cnt << '\n';
	}
}