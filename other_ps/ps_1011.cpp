#include <iostream>
#include <math.h>

using namespace std;

long long arr[100001];

int main() {

	int t;
	cin >> t; //test case

	for (long long i = 0; i < 100001; i++) {
		arr[i] = pow(i, 2);
	}

	for (int i = 0; i < t; i++) {
		long long x, y;

		cin >> x >> y;

		long long diff = y - x;
		long long N = 0;
		// 해보면 1 2 3 2 1과 같은 형태 존재! 대칭성을 이용
		// 아래 코드는 비교 횟수와 sum 계산을 계속 해야하므로 시간 초과
		for (long long i = 1; i < 100000; i++) {
			if (diff >= arr[i] && diff < arr[i + 1]) {
				N = i;
				break;
			}
		}

		long long cnt = 2 * N - 1;
		
		diff = diff - arr[N];

		while (diff != 0) {
			if (diff - N >= 0) {
				diff = diff - N;
				cnt++;
			}
			else {
				N -= 1;
			}
		}
		cout << cnt << '\n';
	}
}

/*
long long arr[5][100001];
//2^31 = 2147483648
//sum_100001 = 5000150001

long long sum(long long n) {
	long long s = 0;
	for (long long i = 1; i <= n; i++) {
		s += i;
	}
	return s;
}

int main() {
	int T;
	cin >> T;

	for (int i = 0; i < T; i++) { //testcase
		long long x, y;
		cin >> x >> y;
		
		long long temp = x + 1;
		long long k = 1;
		long long k_b = 0; //k - 1;
		long long k_sum = 0;
		long long back = y - temp - 1;

		long long cnt = 1;
		long long index = 1;

		arr[0][1] = k;
		arr[1][1] = k_b;
		arr[2][1] = k_sum;
		arr[3][1] = temp;
		arr[4][1] = back;
		int flag = 1;

		while(arr[4][index] != y && flag == 1) {
			//cout << "k = " << arr[0][index] << ", kn-1 = " << arr[1][index] << ", k-1_sum = " << arr[2][index]
				//<< ", temp = " << arr[3][index] << ",back = " << back << '\n';
			for (long long i = arr[0][index] + 1; i >= arr[0][index] - 1; i--) {
				k = i;
				k_b = i - 1;
				k_sum = sum(k_b);
				temp = arr[3][index] + k;
				back = y - temp - 1;
				
				if (back == 0) {
					if (k_b == 0 || k_b == 1) {
						cnt += 2;
						flag = 0;
						break;
					}
				}
				else {
					if (back >= k_sum) {
						cnt++;
						index += 1;
						arr[0][index] = k;
						arr[1][index] = k_b;
						arr[2][index] = k_sum;
						arr[3][index] = temp;
						arr[4][index] = back;
						break;
					}
				}
			}
		}
		cout << cnt;

	}
}
*/