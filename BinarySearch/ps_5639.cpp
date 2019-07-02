//이진 검색 트리
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <stack>
#include <vector>

using namespace std;

struct node {
	int data;
	node *right = NULL;
	node *left = NULL;
};

node *makeNode(int d) {
	node *N = (node*)malloc(sizeof(node));
	N -> data = d;
	N -> right = NULL;
	N ->left = NULL;

	return N;
}

void insert(node *root, int data) {
	if (data < root->data) {
		if (root->left == NULL) {
			root->left = makeNode(data);
		}
		else {
			insert(root->left, data);
		}
	}
	else if(data > root->data){
		if (root->right == NULL) {
			root->right = makeNode(data);
		}
		else {
			insert(root->right, data);
		}
	}
}

void postOrder(node *temp) {
	if (temp != NULL) {
		postOrder(temp->left);
		postOrder(temp->right);
		printf("%d\n", temp->data);
	}
}


int main() {
	int data;

	cin >> data;
	node *root = makeNode(data);

	while (scanf("%d", &data) != EOF) {
		insert(root, data);
	}
	postOrder(root);
}