//떨어지는 개미
#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>

using namespace std;

int T, N, L, K, p, a;

vector< pair<int, int> > ant;
vector<int> drop_order;
deque <int> id;

int main() {
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N >> L >> K;
		for (int i = 0; i < N; i++) {
			cin >> p >> a;
			if (a > 0) {
				p = L - p;
			}
			ant.push_back(make_pair( p, a ));
			id.push_back(a);
		}

		sort(ant.begin(), ant.end());

		for (int i = 0; i < ant.size(); i++) {
			int front = id.front(), back = id.back();
			if (i < ant.size() - 1) {
				if (ant[i].first == ant[i + 1].first) {
					if (front < back) {
						drop_order.push_back(front);
						drop_order.push_back(back);
						//cout << "front = " << id.front() << ", back = " << id.back() << '\n';
					}
					else {
						drop_order.push_back(back);
						drop_order.push_back(front);
						//cout << "back = " << id.back() << ", front = " << id.front() << '\n';
					}
					id.pop_back();
					id.pop_front();
					i++;
				}
			}
			else if(ant[i].second < 0){
				drop_order.push_back(front);
				id.pop_front();
			}
			else {
				drop_order.push_back(back);
				id.pop_back();
			}
		}
		cout << drop_order[K - 1] << '\n';
		ant.clear();
		drop_order.clear();
		while (!id.empty()) {
			id.pop_back();
		}

	}
}

/* ---------------시간초과 꿀잼 ㅡㅡ
int main() {
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N >> L >> k;
		for (int i = 0; i < N; i++) {
			cin >> p >> a;
			ant.push_back({ p, a });
			origin_id.push_back(a);
		}

		while (cnt != k) {
			//다음 원소의 locate값과 1차이 나고 만날경우 -> 방향만 바꾸고 제자리
			//다음 원소의 locate값과 2차이 나고 && 만날경우(이동한 위치 같을 경우)
			//-> first값 이동 후 방향 바꾸기
			//두 경우 모두 아닐 경우 -> 방향대로 이동
			int left = ant[0].first, right = ant.back().first;
			int left_id = ant[0].second, right_id = ant.back().second;

			if ((left == 0) && (right == L)) {
				if (abs(left_id) < abs(right_id)) {
					cnt++;
					dropId = ant[0].second;
					ant.erase(ant.begin()); //첫번째 인자 삭제
					flag = 0;
					continue;
				}
				else if (abs(left_id) > abs(right_id)) {
					cnt++;
					dropId = right_id;
					ant.pop_back();
					flag = 0;
					continue;
				}
			}
			else if (left == 0) {
				cnt++;
				dropId = left_id;
				ant.erase(ant.begin());
				flag = 1;
				continue;
			}
			else if (right == L) {
				cnt++;
				dropId = right_id;
				ant.pop_back();
				flag = 1;
				continue;
			}

			if (flag == 1) {
				for (int i = 0; i < ant.size(); i++) {
					int temp_location = ant[i].first;
					int temp_id = ant[i].second;
					int dir;

					if (temp_id > 0) dir = 1;
					else dir = -1;

					if (i != ant.size() - 1) {
						int next_location = ant[i + 1].first;
						int next_id = ant[i + 1].second;
						int next_dir;

						if (next_id > 0) next_dir = 1;
						else next_dir = -1;

						if (next_location - temp_location == 1 && dir > 0 && next_dir < 0) {
							ant[i].second = -ant[i].second;
							ant[i + 1].second = -ant[i + 1].second;
							i += 1;
						}
						else if (next_location - temp_location == 2 && dir > 0 && next_dir < 0) {
							ant[i].first = ant[i].first + dir;
							ant[i].second = -ant[i].second;
							ant[i + 1].first = ant[i + 1].first + next_dir;
							ant[i + 1].second = -ant[i + 1].second;
							i += 1;
						}
						else {
							ant[i].first = ant[i].first + dir;
						}
					}
					else {
						ant[i].first = ant[i].first + dir;
					}
				}
			}
		}

		for (int i = 0; i < origin_id.size(); i++) {
			if (abs(origin_id[i]) == dropId) {
				cout << origin_id[i] << '\n';
				break;
			}
		}
		cnt = 0;
		flag = 1;
		ant.clear();
		origin_id.clear();
	}
}
*/