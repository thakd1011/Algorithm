#include <iostream>
#include <algorithm>

using namespace std;

//level N짜리 tree의 모든 노드에 *2 -> sub_tree라고 하고
//sub_tree+1(not_leeaf node) = left tree
//sub_tree+1(leaf node) = right tree

/*
1
3		2
4	6	5	7

              1
       3			  2
  7	       5	  6		  4
8	12	10	14	9	13	11	15

*/

struct node {
	int data;
	node *left = NULL;
	node *right = NULL;
};

node *makeNode(int n) {
	node *N= (node*)malloc(sizeof(node));
	N->data = n;
	N->left = NULL;
	N->right = NULL;

	return N;
}

node *copySubTree(node *treeOriginal) {
	if(treeOriginal != NULL) {
		node *newNode = (node*)malloc(sizeof(node));
		newNode->data = (treeOriginal->data) * 2;
		newNode->left = copySubTree(treeOriginal->left);
		newNode->right = copySubTree(treeOriginal->right);
		return newNode;
	}
	else {
		return treeOriginal;
	}
}

void subLeft(node *treeOriginal) {
	if (treeOriginal != NULL) {
		if (treeOriginal->left != NULL && treeOriginal->right != NULL) {
			treeOriginal->data += 1;
			subLeft(treeOriginal->left);
			subLeft(treeOriginal->right);
		}
	}
}

void subRight(node *treeOriginal) {
	if (treeOriginal != NULL) {
		if (treeOriginal->left == NULL && treeOriginal->right == NULL) {
			treeOriginal->data += 1;
		}
		else {
			subRight(treeOriginal->left);
			subRight(treeOriginal->right);
		}
	}
}

void preorder(node *temp) {
	if (temp != NULL) {
		cout << temp->data << '\n';
		preorder(temp->left);
		preorder(temp->right);
	}
}

int main() {
	int N;
	node *conTree[16];
	cin >> N;

	for (int i = 0; i < 16; i++) {
		conTree[i] = (node*)malloc(sizeof(node));
	}

	conTree[1]->data = 1;

	conTree[2]->data = 1;
	conTree[2]->left = makeNode(2);
	conTree[2]->right = makeNode(3);

	// N==2일 때 트리 구조
	for (int i = 3; i <= 15; i++) {
		node *leftTree;
		node *rightTree;

		leftTree = copySubTree(conTree[i - 1]);
		rightTree = copySubTree(conTree[i - 1]);
		subLeft(leftTree);
		subRight(rightTree);

		conTree[i] = makeNode(1);
		conTree[i]->right = rightTree;
		conTree[i]->left = leftTree;
	}
	
	preorder(conTree[N]);
}