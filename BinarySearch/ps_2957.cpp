//이진 검색 트리
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <stack>
#include <vector>

using namespace std;

int cnt;

struct node {
	int data;
	node *right = NULL;
	node *left = NULL;
};

node *makeNode(int d) {
	node *N = (node*)malloc(sizeof(node));
	N->data = d;
	N->right = NULL;
	N->left = NULL;

	return N;
}

void insert(node *root, int data) {
	cnt++;
	if (data < root->data) {
		if (root->left == NULL) {
			root->left = makeNode(data);
		}
		else {
			insert(root->left, data);
		}
	}
	else if (data > root->data) {
		if (root->right == NULL) {
			root->right = makeNode(data);
		}
		else {
			insert(root->right, data);
		}
	}
}

int main() {
	int N, data;
	cin >> N;

	cin >> data;
	node *root = makeNode(data);
	cout << cnt << '\n';

	for (int i = 1; i < N; i++) {
		cin >> data;
		insert(root, data);
		cout << cnt << '\n';
	}

}