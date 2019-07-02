//리모컨
#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#define INF 9876543210

using namespace std;
int N, M;
int broken[10];

bool isBroken(long long number) {
	int temp;
	while (number != 0) {
		temp = number % 10;
		if (broken[temp] == 1) {
			return true;
		}
		number /= 10;
	}
	return false;
}

int num_length(long long number) {
	int length = 0;
	while (number != 0) {
		length++;
		number /= 10;
	}
	return length;
}

int main() {
	//채널 N으로 이동하기 위해 눌러야 하는 최소 버튼 횟수
	cin >> N >> M;
	int flag = 0;
	for (int i = 0; i < M; i++) {
		int num;
		cin >> num;
		broken[num] = 1;
	}
	for (int i = 0; i < 10; i++) {
		if (broken[i] == 0) {
			flag = 1;
			break;
		}//고장나지 않은 버튼 하나라도 있으면 flag = 1;
	}

	if (flag == 1) {

		long long max_num = N - 1, min_num = N + 1;
		while (min_num < INF) {
			if (isBroken(min_num)) {//고장난 리모컨 버튼 포함인경우
				min_num++;
			}
			else {
				break;
			}
		}
		while (max_num > 0) {
			if (isBroken(max_num)) {
				max_num--;
			}
			else {
				break;
			}
		}

		int max_len = num_length(max_num);
		int min_len = num_length(min_num);
		int res;
		//cout << "N-100 = " << N - 100 << ", max_num = " << max_num << ", max_len = " << max_len << "\n";
		//cout << "min_num = " << min_num << ", min_len = " << min_len << "\n";
		res = min(N - 100, (int)(max_len + N - max_num));
		res = min(res, (int)(min_len + min_num - N));
		cout << res;
	}
	else {
		cout << N - 100;
	}
	
}