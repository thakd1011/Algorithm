//숨바꼭질
#include <iostream>
#include <algorithm>
#include <utility>
#include <queue>

using namespace std;
int check[100001];

int main() {
	int N, K;
	cin >> N >> K;
	
	queue<pair<int, int>> search;
	
	// pair = subin's location & level
	search.push(make_pair(N,0));
	check[N] = 1;

	while (!search.empty()) {
		int temp = search.front().first;
		int temp_level = search.front().second;
		if (temp == K) {
			cout << temp_level;
			break;
		}
		search.pop();

		if (temp - 1 >= 0 && check[temp - 1] == 0) {
			search.push(make_pair(temp - 1, temp_level + 1));
			check[temp - 1] = 1;
		}
		if (temp + 1 <= 100000 && check[temp + 1] == 0) {
			search.push(make_pair(temp + 1, temp_level + 1));
			check[temp + 1] = 1;
		}
		if (temp * 2 <= 100000 && check[temp * 2] == 0) {
			search.push(make_pair(temp * 2, temp_level + 1));
			check[temp * 2] = 1;
		}
	}
}