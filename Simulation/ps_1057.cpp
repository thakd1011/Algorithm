#include <iostream>
#include <algorithm>

using namespace std;

int N, kim, im;

int main() {
	int res = 1;
	cin >> N >> kim >> im;
	kim -= 1; im -= 1;
	
	kim = kim / 2;
	im = im / 2;

	while (kim != im) {
		res++;
		kim = kim / 2;
		im = im / 2;
	}
	cout << res;
}