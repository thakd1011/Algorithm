// insert sort & bubble sort 구현하기
#include <iostream>

using namespace std;

int arr[1001];

void bubble_sort(int* a, int len) {
	int temp;
	for (int i = 1; i < len; i++) {
		for (int j = 0; j < len - i; j++) {
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}
}

void insert_sort(int* a, int len) {
	int temp;
	for (int i = 0; i < len - 1; i++) {
		if (arr[i] > arr[i + 1]) {
			temp = arr[i + 1];
			int index = i;
			while (index >= 0 && arr[index] > temp) {
				arr[index + 1] = arr[index];
				arr[index] = temp;
				index--;
			}
		}
	}
}

int main() {
	int N;
	cin >> N;

	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
	}
	//bubble_sort(arr, N);
	insert_sort(arr, N);
	for (int i = 0; i < N; i++) {
		cout << arr[i] << '\n';
	}
}