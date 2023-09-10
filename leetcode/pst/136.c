int singleNumber(int* nums, int numsSize){
    int count = 0;
    int i;
    for (i = 0; i < numsSize; i++) {
        if (nums[i] == 0) {
            count++;
        }
    }
    if (count == 1) return 0;

    for (i = 0; i < numsSize; i++) {
        for (int j = i + 1; j < numsSize; j++) {
            if (nums[i] == nums[j]) {
                nums[i] = 0;
                nums[j] = 0;
            }
        }
    }

    for (i = 0; i < numsSize; i++) {
        if (nums[i] != 0) return nums[i];
    }

    return 0;
}