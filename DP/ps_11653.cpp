#include <iostream>
using namespace std;


int main() {
	long long n;
	long long prime = 2;
	cin >> n;

	while (n != 1) {

		while (n%prime == 0) {
			cout << prime << '\n';
			n /= prime;
		}
		prime++;

	}
}