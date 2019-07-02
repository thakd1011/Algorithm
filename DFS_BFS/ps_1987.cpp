#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N, M;
int al_check[26]; // alphabet checking
int res = 0;

void dfs(int x, int y, char** graph, int cnt){
	// 더 큰 값으로 넣어줌
	res = max(res, cnt);

	// down, 재귀함수 탈출 후 다시 알파벳 체크 값을 0으로 돌려주는 게 포인트!
	// 다른 길을 갈 때의 알파벳을 체크해줘야 하기 때문에 -> 백트래킹

	if (y + 1 < N && al_check[graph[y + 1][x] - 'A'] == 0) {
		al_check[graph[y + 1][x] - 'A'] = 1;
		dfs(x, y + 1, graph, cnt + 1);
		al_check[graph[y + 1][x] - 'A'] = 0;
	}
	// up
	if (y - 1 >= 0 && al_check[graph[y - 1][x] - 'A'] == 0) {
		al_check[graph[y - 1][x] - 'A'] = 1;
		dfs(x, y - 1, graph, cnt + 1);
		al_check[graph[y - 1][x] - 'A'] = 0;
	}
	// left
	if (x - 1 >= 0 && al_check[graph[y][x - 1] - 'A'] == 0) {
		al_check[graph[y][x - 1] - 'A'] = 1;
		dfs(x - 1, y, graph, cnt + 1);
		al_check[graph[y][x - 1] - 'A'] = 0;
	}
	// right
	if (x + 1 < M && al_check[graph[y][x + 1] - 'A'] == 0) {
		al_check[graph[y][x + 1] - 'A'] = 1;
		dfs(x + 1, y, graph, cnt + 1);
		al_check[graph[y][x + 1] - 'A'] = 0;
	}
}

int main() {
	cin >> N >> M;

	char** graph = new char*[N];
	int* check = new int[26];
	for (int i = 0; i < N; i++) {
		graph[i] = new char [M];
	}

	for (int i = 0; i < N; i++) {
		scanf("%s", graph[i]);
	}

	int temp = graph[0][0] - 'A';
	al_check[temp] = 1;
	dfs(0, 0, graph, 1);

	cout << res;
} 