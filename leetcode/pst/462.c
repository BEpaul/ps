#define abs(x) ((x) < 0 ? (-(x)) : (x))

int cmp (const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

int minMoves2(int* nums, int numsSize) {
    qsort(nums, numsSize, sizeof(int), cmp);

    int median = nums[numsSize / 2];
    int sum = 0;

    for (int i = 0; i < numsSize; i++) {
        sum += abs(nums[i] - median);
    }

    return sum;
}