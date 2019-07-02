//Biggiest Histogram
#include <iostream>
#include <algorithm>
#include <stack>
#include <math.h>

using namespace std;

int histogram[100000];
int maximum;
stack <int> st;
int segTree[1000000];
int N;

void selectNode(int start, int end, int tempIndex, int i, int j) {
	if (segTree[tempIndex] == 0) {
		return;
	}

	int mid = ((i + j) / 2);

	if ((i >= start) && (j <= end)) {
		st.push(segTree[tempIndex]);
	}
	else {
		selectNode(start, end, 2 * tempIndex, i, mid);
		selectNode(start, end, 2 * tempIndex + 1, mid + 1, j);
	}
}

int getMinIndex() {
	int m = 123456789;
	int index = 0;

	while (!st.empty()) {
		int temp = st.top();
		int height = histogram[temp];
		st.pop();

		if (m > height) {
			index = temp;
			m = height;
		}
	}
	return index;
}

void dfs(int start, int end) {
	if (start == end) {
		maximum = max(histogram[start], maximum);
		return;
	}

	int minIndex = 0;
	selectNode(start, end, 1, 1, N);
	minIndex = getMinIndex();

	maximum = max((end - start + 1)*histogram[minIndex], maximum);

	int leftStart, leftEnd, rightStart, rightEnd;
	leftStart = start;
	rightEnd = end;
	leftEnd = minIndex - 1;
	rightStart = minIndex + 1;
	/*
	if (start != minIndex) {
		leftEnd = minIndex - 1;
	}
	else {
		leftEnd = start;
	}

	if (end != minIndex) {
		rightStart = minIndex + 1;
	}
	else {
		rightStart = end;
	}
	*/
	if (leftStart>leftEnd) {
		return;
	}
	else { dfs(leftStart, leftEnd); }
	if (rightStart > rightEnd) {
		return;
	}
	else { dfs(rightStart, rightEnd); }

}

void makeTree() {
	int k = int(ceil((double)log(N) / (double)log(2)));
	int start = pow(2, k), end = pow(2, k) + N;
	int cnt = 1;

	if (N % 2 == 1) {
		end--;
	}
	// put leaf node in tree
	for (int i = start; i < end; i++) {
		segTree[i] = cnt;
		cnt++;
	}

	if (N % 2 == 1) {
		segTree[start - 1] = N;
	}

	while (k != 0) {
		k--;
		for (int i = pow(2, k); i <= pow(2, k + 1) - 1; i++) {
			int leftValue = histogram[segTree[2 * i]];
			int rightValue = histogram[segTree[2 * i + 1]];
			int minIndex = 0;

			if (segTree[i] != 0) {
				continue;
			}
			if (leftValue > rightValue) {
				segTree[i] = segTree[2 * i + 1];
			}
			else {
				segTree[i] = segTree[2 * i];
			}
		}
	}
}

int main() {
	while (cin >> N && N != 0) {
		for (int i = 1; i <= N; i++) {
			cin >> histogram[i];
		}

		makeTree();
		/*
		for (int i = 1; i < 16; i++) {
			cout << segTree[i] << ' ';
		}
		*/

		/*
		cout << "histogram value = " << ' ';
		for (int i = 1; i < 8; i++) {
			cout << histogram[i] << ' ';
		}
		cout << '\n';
		*/
		dfs(1, N);
		cout << maximum << '\n';

		//reInitialize
		maximum = 0;
		for (int i = 1; i <= N; i++) {
			histogram[i] = 0;
		}
		for (int i = 0; i < 262145; i++) {
			segTree[i] = 0;
		}
	}
}