void moveZeroes(int* nums, int numsSize){
    int i, j, endIndex;

    for (i = 0; i < numsSize; i++) {
        endIndex = numsSize - 1;
        if (nums[i] == 0) {
            for (j = i; j < endIndex; j++) {
                nums[j] = nums[j+1];
            }
            nums[endIndex] = 0;
            numsSize--;
            i--;
        } else {
            continue;
        }
    }
}