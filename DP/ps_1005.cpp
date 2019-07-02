//ACM Craft
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

vector <int> topologicalSort;
vector< vector<int> > buildOrder(1002);

int value[1002];
int visit[1002];
int dp[1002];

void sorting(int temp) {
	visit[temp] = 1;
	for (int i = 0; i < buildOrder[temp].size(); i++) {
		
		int next = buildOrder[temp][i];

		if (!visit[next]) {
			sorting(next);
		}
	}
	topologicalSort.push_back(temp);
}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {

		int n, k, x, y, w;

		cin >> n >> k;

		for (int j = 1; j <= n; j++) {
			cin >> value[j];
			dp[j] = -1;
		}
		for (int j = 0; j < k; j++) {
			//input ordering A to B
			cin >> x >> y;
			buildOrder[x].push_back(y);
		}

		cin >> w;

		for (int j = 1; j <= n; j++) {
			if (buildOrder[j].size() != 0) {
				if (!visit[j]) { //node 재방문 막기 위해!
					visit[j] = 1;
					sorting(j);
				}
			}
		}

		// 맨 처음 시작값은 value값과 똑같이!
		int start = topologicalSort.back();
		dp[start] = value[start];

		while (!topologicalSort.empty()) {
			int temp = topologicalSort.back();
			topologicalSort.pop_back();

			for (int j = 0; j < buildOrder[temp].size(); j++) {
				int next = buildOrder[temp][j];
				dp[next] = max(dp[next], dp[temp] + value[next]);
			}
		}
		cout << dp[w] << '\n';

		//reInitialize
		for (int j = 1; j <= n; j++) {
			value[j] = 0;
			buildOrder[j].clear();
			topologicalSort.clear();
			visit[j] = 0;
			dp[j] = -1;
		}
	}
}