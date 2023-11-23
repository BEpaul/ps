int arr[20001];

int get_sum (int *nums, int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += nums[i];
    }

    return sum;
}

int is_even (int num) {
    if (num % 2 == 0) return true;

    return false;
}

bool canPartition(int* nums, int numsSize) {
    int sum = get_sum(nums, numsSize);
    
    if (!is_even(sum)) {
        return 0;
    }
    
    for (int i = 0; i <= sum / 2; i++) {
        arr[i] = 0;
    }
    arr[0] = 1;

    for (int i = 0; i < numsSize; i++) {
        for (int j = sum / 2; j >= nums[i]; j--) {
            if (arr[j - nums[i]]) {
                arr[j] = 1;
            }
        }
    }

    return arr[sum / 2];
}
