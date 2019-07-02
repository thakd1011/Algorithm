//뱀
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int main() {
	int N, K, L, X;
	char C;
	int y_dir[4] = { -1, 0, 1, 0 };
	int x_dir[4] = { 0, 1, 0, -1 };
	int dir_index = 2;
	queue< pair<int, char> > change_dir;
	
	queue< pair<int, int> > dummy;

	cin >> N; // 보드 크기

	int** board = new int*[N + 1];
	for (int i = 1; i <= N; i++) {
		board[i] = new int[N + 1];
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			board[i][j] = 0;
		}
	}
	board[1][1] = 1; // 처음 뱀의 위치는 1로

	cin >> K; // 사과 개수
	for (int i = 0; i < K; i++) { // 사과 위치, 행&열로
		int x, y;
		cin >> x >> y;
		board[x][y] = 2; // 사과는 2로 표기
	}

	dummy.push(make_pair(1, 1));

	cin >> L; // 변환 횟수
	for (int i = 0; i < L; i++) {
		int time;
		char dir;
		cin >> time >> dir;
		change_dir.push(make_pair(time, dir));
	}

	int flag = 1, time = 0;
	while (flag) {
		int x, y;
		// 현재 뱀 머리 위치
		x = dummy.back().first;
		y = dummy.back().second;

		int next_x = x + x_dir[dir_index], next_y = y + y_dir[dir_index];
		
		if (next_x > N || next_y > N || next_x < 1 || next_y < 1) {
			// 뱀이 벽과 부딪히는 경우
			flag = 0;
		}
		else {
			int tail_x = dummy.front().first, tail_y = dummy.front().second;
			// 주요 연산 :board 비교연산, 시간복잡도 : O(N);
			if (board[next_x][next_y] == 2) { // 사과 먹는 경우(사과가 있으므로 몸은 없다!)
				board[next_x][next_y] = 1;
				dummy.push(make_pair(next_x, next_y));
			}
			else if (board[next_x][next_y] == 1) {
				flag = 0;
			}
			else {
				dummy.pop();
				board[tail_x][tail_y] = 0;
				dummy.push(make_pair(next_x, next_y));
				board[next_x][next_y] = 1;
			}
		}
		time += 1;

		//방향 바꿔주기
		if (!change_dir.empty()) {
			if (time == change_dir.front().first) {
				if (change_dir.front().second == 'D') {
					dir_index = (dir_index + 3) % 4;
				}
				else {
					dir_index = (dir_index + 1) % 4;
				}
				// 벡터 맨 앞 꺼내줘
				change_dir.pop();
			}
		}
	}
	
	cout << time << '\n';

}