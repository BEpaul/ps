int majorityElement(int* nums, int numsSize) {
    int majorityNumber = nums[0];
    int count = 1;
    for (int i = 1; i < numsSize; i++) {
        if (count == 0) {
            majorityNumber = nums[i];
            count++;
        } else if (nums[i] == majorityNumber)
            count++;
        else
            count--;
    }
    return majorityNumber;
}


/**
 * @brief Using majority property in the array

    int majorityElement(int* nums, int numsSize) {
        quickSort(nums, 0, numsSize - 1);

        return nums[numsSize/2];
    }

    void quickSort(int arr[], int L, int R) {
        int left = L, right = R;
        int pivot = arr[(L + R) / 2]; 
        int temp;
        do
        {
            while (arr[left] < pivot)
                left++;
            while (arr[right] > pivot)
                right--;
            if (left<= right)
            {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        } while (left<= right);
    
        if (L < right)
            quickSort(arr, L, right);
    
        if (left < R)
            quickSort(arr, left, R);
    }
*/

/**
 * @brief Using double-for-loop-statement

    int majorityElement(int* nums, int numsSize) {
        int majority = numsSize / 2;
        for (int i = 0; i < numsSize; ++i) {
            int count = 0;
            for (int j = 0; j < numsSize; ++j) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > majority) {
                return nums[i];
            }
        }
        return 0;
    }
 */