#include <iostream>

using namespace std;
int main(int argc, char** argv) {
	int N, M;
	int i, j, k;
	cin >> N >> M;
	
	int arr[N + 1] = {0, };
	for (int iter = 0; iter < M; iter++) {
		cin >> i >> j >> k;
		for (int l = i; l <= j; l++) {
			arr[l] = k;
		}
	}
	
	for (int i = 1; i <= N; i++) {
		cout << arr[i] << " ";
	}
	
	return 0;
}