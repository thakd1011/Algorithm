#include <iostream>
#include <algorithm>

using namespace std;

char eng[1000001];
int res;

int main() {

	cin.getline(eng, 1000001);
	if (eng[0] == ' ') {
		res = 0;
	}
	else res = 1;

	for (int i = 0; eng[i] != NULL; i++) {
		if (eng[i] == ' ') {
			if (eng[i + 1] == NULL) {
				continue;
			}
			res++;
		}
	}
	cout << res;
}