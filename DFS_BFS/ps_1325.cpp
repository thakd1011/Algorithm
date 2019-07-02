//효율적인 해킹
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
int N, M;
int a, b;
int visit[10001];
int dp[10001];

vector< vector<int> > com(10001);
vector< pair<int, int> > hacking;
queue<int> q;

void dfs(int temp) {
	visit[temp] = 1;
	if (dp[temp] == 0) {
		dp[temp] = 1;
	}

	for (int j = 0; j < com[temp].size(); j++) {
		int next = com[temp][j];
		if (!visit[next]) {
			dfs(next);
			dp[temp] += dp[next];
		}
		else {
			dp[temp] = dp[next] + 1;
		}
	}
}

int main() {
	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		com[b].push_back(a);
	}
	for (int i = 1; i <= N; i++) {
		if (!visit[i]) {
			dp[i] = 1;
			dfs(i);
		}
	}
	for (int i = 1; i <= N; i++) {
		hacking.push_back({ i, dp[i] });
	}

	sort(hacking.begin(), hacking.end());

	int maximum = hacking[0].second;

	cout << hacking[0].first << ' ';
	for (int i = 1; i < hacking.size(); i++) {
		if (hacking[i].second == maximum) {
			cout << hacking[i].first << ' ';
		}
	}

}