#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int max_num = 0;
	int temp = 0;
	for (int i = 0; i < 4; i++) {
		int in, out;
		cin >> out >> in;
		
		temp = temp + in - out;
		max_num = max(temp, max_num);
	}
	cout << max_num;
}