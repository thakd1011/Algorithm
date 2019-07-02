//tree ordering
#include <iostream>
#include <algorithm>

using namespace std;

int n;
int inorder[1000000];
int postorder[1000000];

int searchIndex(int start, int end, int value) {
	for (int i = start; i < end; i++) {
		if (inorder[i] == value) {
			return i;
		}
	}
	return end;
}

void printRoot(int start, int end, int inRoot, int postRoot) {

	printf("%d ", inorder[inRoot]);

	int leftStart = start;
	int leftEnd = inRoot - 1;
	int rightStart = inRoot + 1;
	int rightEnd = end;
	int leftCnt = inRoot - start;
	int rightCnt = end - inRoot;

	if (leftCnt > 0) {
		// calling recursive function
		int leftRootValue = postorder[postRoot - rightCnt - 1];
		int nextRoot = searchIndex(leftStart, leftEnd, leftRootValue);

		printRoot(leftStart, leftEnd, nextRoot, postRoot - rightCnt - 1);
	}
	if (rightCnt > 0) {
		// calling recursive function
		int rightRootValue = postorder[postRoot - 1];
		int nextRoot = searchIndex(rightStart, rightEnd, rightRootValue);

		printRoot(rightStart, rightEnd, nextRoot, postRoot - 1);
	}
}

int main() {
	int root;

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> inorder[i];
	}
	for (int i = 0; i < n; i++) {
		cin >> postorder[i];
	}

	root = postorder[n - 1]; ///first root value
	int startRoot = searchIndex(0, n - 1, root);

	printRoot(0, n - 1, startRoot, n - 1);

}