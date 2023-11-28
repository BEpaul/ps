int generate_subset(int* nums, int numsSize, int i, int currentTotal) {
    if (i == numsSize) {
        return currentTotal;
    }

    int total1 = generate_subset(nums, numsSize, i + 1, currentTotal ^ nums[i]);
    int total2 = generate_subset(nums, numsSize, i + 1, currentTotal);

    return total1 + total2;
}

int subsetXORSum(int* nums, int numsSize) {
    return generate_subset(nums, numsSize, 0, 0);
}