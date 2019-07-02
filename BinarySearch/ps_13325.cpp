//이진트리 - 가중치
#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>

using namespace std;

int Tree[(1 << 21) + 1]; //2^21+1까지가 최대
int k, treeSize, res;

int sumTree(int i) {
	int leftChild = i * 2, rightChild = i * 2 + 1;

	if (leftChild > treeSize || rightChild > treeSize) {
		//tempnode == leafNode
		return Tree[i];
	}
	
	int leftSum = sumTree(leftChild);
	int rightSum = sumTree(rightChild);

	if (leftSum > rightSum) { //값을 변경시켜줌
		Tree[leftChild] += leftSum - rightSum;
	}
	else {
		Tree[rightChild] += rightSum - leftSum;
	}

	return Tree[i] + max(leftSum, rightSum);
}

int main() {
	int num;
	cin >> k;

	treeSize = 1 << (k + 1);
	for (int i = 2; i < treeSize; i++) {
		cin >> Tree[i];
	}

	sumTree(1);

	for (int i = 2; i < treeSize; i++) {
		res += Tree[i];
	}
	cout << res;

}

/*
int k;
vector <int> Tree;
vector <int> last;

int main() {
	int num;
	int sum = 0;
	int maximum = -INF, minimum = INF;

	cin >> k;

	Tree.push_back(0); //0
	Tree.push_back(0); //1

	for (int i = 2; i <= pow(2, k + 1)-1; i++) {
		cin >> num;
		Tree.push_back(num);
		sum += num;
	}

	for (int i = 2; i <= pow(2, k + 1) - 1; i++) {
		
		Tree[i] += Tree[i / 2];

		if (i >= pow(2, k) && i <= (pow(2, k + 1) - 1)) {
			maximum = max(maximum, Tree[i]);
			minimum = min(minimum, Tree[i]);
		}
	}

	for (int i = pow(2, k); i <= pow(2, k + 1) - 1; i++) {
		last.push_back(maximum - Tree[i]);
	}

	int cnt = last.size() / 2;
	int lastSum = 0;
	int res = 0;
	int level = 1;

	while (last.size() != 1) {
		int j = 0;

		for (int i = 0; i < last.size(); i += 2) {
			int value = abs(last[i] - last[i + 1]);
			if (value == 0) {
				last[j] = last[i];
				if (level != 1) {
					lastSum += last[j];
				}
			}
			else {
				last[j] = value;
				lastSum += last[j];
			}
			j++;
		}

		int size = last.size();
		for (int i = cnt; i < size; i++) {
			last.pop_back();
		}
		cnt /= 2;
		level++;
	}
	
	res = sum + lastSum;
	cout << res;
}
*/