// 프린터 큐
#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int testcase, N, M, check_point, temp_import;
	// N = 문서의 수, M = 궁금한 문서의 위치
	cin >> testcase;

	for (int i = 0; i < testcase; i++) {
		scanf("%d %d", &N, &M);

		int cnt = 0;
		queue<int> docs;
		vector<int> importance(N);

		check_point = M; // 맨 처음 문서의 위치, 바뀔 수 있다는 것
		for (int j = 0; j < N; j++) {
			scanf("%1d", &temp_import);
			docs.push(temp_import);
			importance.push_back(temp_import);
		}

		// 중요도를 오름차순으로 정렬
		sort(importance.begin(), importance.end());
		int flag = 1;
		while (flag) {
			if (docs.front() == importance.back()) { // 문서 출력할 때
				if (check_point == 0) {//출력하는 문서가 궁금한 문서일 때
					cout << cnt + 1<< '\n';
					flag = 0;
				}
				docs.pop();
				importance.pop_back();
				check_point -= 1;
				cnt += 1;
			}
			else {
				if (check_point == 0) {
					//맨 앞이 궁금한 문서인데 중요도가 낮아 출력하지 못할 때
					check_point = docs.size() - 1;
				}
				else {
					check_point -= 1;
				}
				int temp = docs.front();
				docs.pop();
				docs.push(temp);
			}
		}

	}
}