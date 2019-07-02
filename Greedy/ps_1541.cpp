#include<iostream>
#include <algorithm>
#include <string>
using namespace std;
int main() {
	string s="";
	int flag = 0, sum = 0;
	char* arr = new char[51];
	scanf("%s", arr);
	for (int i = 0; arr[i]!=NULL; i++) {
		if (arr[i]-'0'>=0 && arr[i]-'0'<=9) {
			s += arr[i];

		}
		else {
			if (flag == 0) {//+
				sum += stoi(s);
				s = "";
			}
			else {
				sum -= stoi(s);
				s = "";
			}
		}
		if (arr[i] == '-') {
			flag = 1;
		}
	}
	if (flag == 1) {
		sum -= stoi(s);
	}
	else {
		sum += stoi(s);
	}
	cout << sum;
}