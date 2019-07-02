#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;
vector<int> atm;
int arr[1001];
int main() {
	int N, temp, sum=0;
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> temp;
		atm.push_back(temp);
	}
	sort(atm.begin(), atm.end());

	for (int i = 1; i < N; i++) {
		atm[i] += atm[i - 1];
	}
	for (int i = 0; i < atm.size(); i++) {
		sum += atm[i];
	}
	cout << sum;
}