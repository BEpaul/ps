int arr[300001];

bool splitArraySameAverage(int* nums, int n) {
    int sum = 0;
    memset(arr, 0, 300000 * sizeof(int));
    arr[0] = 1;

    for (int i = 0; i < n; i++) {
        sum += nums[i];
        for (int j = sum; j >= nums[i]; j--) {
            arr[j] |= arr[j - nums[i]] << 1;
        }
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j <= sum; j++) {
            if (arr[j] & (1 << i) && j * (n - i) == i * (sum - j)) {
                return 1;
            }
        }
    }

    return 0;
}