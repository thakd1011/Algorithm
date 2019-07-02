//통계학
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

long long arr[500001];
vector<pair<long long, int> > many;
int N;

bool comp(pair<long long, int> &p1, pair<long long, int> &p2) {
	if (p1.second == p2.second) {
		return p1.first < p2.first;
	}
	else {
		return p1.second > p2.second;
	}
}

int main() {
	/*
	산술평균 : N개의 수들의 합을 N으로 나눈 값
	중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
	최빈값 : N개의 수들 중 가장 많이 나타나는 값
	범위 : N개의 수들 중 최댓값과 최솟값의 차이
	*/
	int N;
	double mean = 0;
	long long mid, diff, temp, second_many;


	cin >> N;

	for (int i = 0; i < N; i++) {
		scanf("%lld", &arr[i]);
	}
	sort(arr, arr + N);

	for (int i = 0; i < N; i++) {
	}
	for (int i = 0; i < N; i++) {
		mean += arr[i];
	}

	//최빈값 구하기 (key, value) == (array[i], cnt)
	for (int i = 0; i < N; i++) {
		if (many.empty()) {
			many.push_back({ arr[i], 1 });
			continue;
		}
		if (many.back().first == arr[i]) {
			pair<long long, int> temp = many.back();
			many.pop_back();
			temp.second++;
			many.push_back(temp);
		}
		else {
			many.push_back({ arr[i], 1 });
		}
	}

	sort(many.begin(), many.end(), comp);

	mean = floor((mean / (double)N) + 0.5);
	mid = arr[N / 2];
	diff = arr[N - 1] - arr[0];
	

		if (many[0].second == many[1].second) {
			second_many = many[1].first;
		}
		else {
			second_many = many[0].first;
		}

	cout << mean << '\n' << mid << '\n' << second_many << '\n' << diff;
}