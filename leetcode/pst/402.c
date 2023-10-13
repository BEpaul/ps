char * removeKdigits(char * num, int k){
    int length = strlen(num);
    int non_zero = 0;

    for (int i = 0; i < length; i++) if (num[i] != '0') non_zero++;
    if (non_zero <= k) return "0";

    char *ans = (char *)malloc(sizeof(char) * length);
    int ans_length = length - k;
    int j = 0;

    ans[0] = num[0];
    for (int i = 1; i < length; i++) {
        while (j >= 0 && ans[j] > num[i] && k) j--, k--;
        ans[++j] = num[i];
    }
    ans[ans_length] = 0;
    for (; *ans == '0'; ans++);

    return ans;
}