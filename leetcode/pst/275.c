int binary_search(int arr[], int l, int r, int target) {
    int ans = 0;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= target - mid) {
            ans = target - mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }

    return ans;
}

int hIndex(int* citations, int citationsSize) {
    return binary_search(citations, 0, citationsSize-1, citationsSize);
}