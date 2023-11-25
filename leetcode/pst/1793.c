int maximumScore(int* nums, int numsSize, int k) {
    int min = nums[k];
    int ans = min;
    int l = k;
    int r = k;

    while (min > 0) {
        int left_num = l > 0 ? nums[l-1] : 0;
        int right_num = r < numsSize - 1 ? nums[r+1] : 0;

        if (left_num > right_num) {
            if (min > left_num) {
                min = left_num;
            }
            l--;
        } else {
            if (min > right_num) {
                min = right_num;
            }
            r++;
        }

        if (min * (r - l + 1) > ans) {
            ans = min * (r - l + 1);
        }
    }

    return ans;
}