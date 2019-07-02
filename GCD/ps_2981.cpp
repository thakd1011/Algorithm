#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int arr[100];
int diff[100];

int gcd(int a, int b) {
	if (b == 0) {
		return a;
	}
	else {
		return gcd(b, a%b);
	}
}

void print_divisor(int m) {
	vector<int> div_vec;

	for (int i = 1; i*i <= m; i++) {
		if (m%i == 0) {
			div_vec.push_back(i);
			if (i*i != m) {
				div_vec.push_back(m / i);
			}
		}
	}
	sort(div_vec.begin(), div_vec.end());
	for (int i = 1; i < div_vec.size(); i++) {
		cout << div_vec[i] <<' ';
	}
}

int main() {
	int N, M, flag_out = 1, flag_in = 1;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	//몫*M + k(나머지)로 표기 -> k값이 같은 M을 찾는 것
	//몫은 M의 배수 -> 가장 작은 수의 배수로 이루어진 배열값!
	//->각 원소 차이 값들의 최대공약수!
	sort(arr, arr + N);

	for (int i = 0; i < N - 1; i++) {
		diff[i] = arr[i + 1] - arr[i];
	}

	int temp = gcd(diff[0], diff[1]);

	for (int i = 2; i < N - 1; i++) {
		temp = gcd(temp, diff[i]);
	}

	print_divisor(temp);
}