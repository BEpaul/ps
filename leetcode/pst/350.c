#define max(a, b) ((a) > (b) ? (a) : (b))
#define LIMIT 1000

int* intersect(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize){
    int* res = (int *)malloc(sizeof(int) * max(nums1Size, nums2Size));
    int count[LIMIT+1], i;
    int j = 0;

    for (i = 0; i <= LIMIT; i++) count[i] = 0;
    for (i = 0; i < nums1Size; i++) count[nums1[i]]++;
    for (i = 0; i < nums2Size; i++) {
        if (count[nums2[i]] > 0) {
            count[nums2[i]]--;
            res[j++] = nums2[i];
        }
    }

    *returnSize = j;
    return res;
}