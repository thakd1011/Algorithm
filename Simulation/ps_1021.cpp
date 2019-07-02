//회전하는 큐 -> 복습~~~
#include <iostream>
#include <algorithm>
#include <deque>

using namespace std;

int main() {
	int N, M, res = 0;
	deque<int> d;
	
	cin >> N >> M;
	
	//N개 원소 입력
	for (int i = 1; i <= N; i++) {
		d.push_back(i);
	}
	for (int i = 0; i < M; i++) {
		int num, location = 1;
		cin >> num; // 찾고자 하는 수의 위치

		for (deque<int>::iterator iter = d.begin(); iter < d.end(); iter++) {
			if (*iter == num) break; // iterator가 가리키고 있는 원소가 찾고자 하는 원소일 경우
			location += 1;
		}

		int left = location - 1; // 왼쪽으로 쉬프트 연산
		int right = d.size() - location + 1; // 오른쪽으로 쉬프트 연산
		if (left < right) {
			// 쭉 밀어주고 pop해주면 됨!
			for (int j = 0; j < left; j++) {
				int temp = d.front();
				d.pop_front();
				d.push_back(temp);
				res++;
			}
			d.pop_front();
		}
		else {
			for (int j = 0; j < right; j++) {
				int temp = d.back();
				d.pop_back();
				d.push_front(temp);
				res++;
			}
			d.pop_front();
		}
	}
	cout << res;
}