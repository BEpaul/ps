int longestMountain(int* arr, int arrSize){
    if (arrSize < 3) return 0;

    int ans, i, j;

    ans = i = 0;
    while(i < arrSize - 1) {
        int flag = 0; // if flag == 0, increasing trend, else if flag == 1, decreasing trend
        j = i;
        while (j < arrSize - 1) {
            if (!flag && arr[j] < arr[j+1] || flag && arr[j] > arr[j+1]) {
                j++;
            } else if (!flag && i < j && arr[j] > arr[j+1]) {
                flag = 1;
                j++;
            } else break;
        }

        if (i < j && flag) {
            if(j - i + 1 > ans) ans = j - i + 1;
            i = j;
        } else i++;
    }
    return ans;
}