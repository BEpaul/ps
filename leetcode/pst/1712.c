int sum[100001];

int waysToSplit(int* nums, int numsSize){
    sum[0] = nums[0];
    for (int i = 1; i < numsSize; i++) {
        sum[i] = sum[i-1] + nums[i];
    }

    int ans = 0;

    for (int l = 0, r_min = 0, r_max = 0; l < numsSize - 2; l++) {
        if (r_min <= l) {
            r_min = l + 1;
        }
        while (r_min < numsSize - 1 && sum[l] > sum[r_min] - sum[l]) {
            r_min++;
        }

        if (r_max < r_min) {
            r_max = r_min;
        }
        while (r_max < numsSize - 1 && sum[r_max] - sum[l] <= sum[numsSize - 1] - sum[r_max]) {
            r_max++;
        }

        ans = (ans + r_max - r_min) % 1000000007;
    }
    
    return ans;
}