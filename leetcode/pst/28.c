int strStr(char * haystack, char * needle){
    int n = strlen(haystack), m = strlen(needle);

    if (n < m) return -1;

    int sum = 0, needleSum = 0;
    for (int i = 0; i < m; i++) {
        sum += haystack[i];
        needleSum += needle[i];
    }

    for (int i = 0; i + m <= n; sum += haystack[i+m] - haystack[i], i++) {
        if (sum != needleSum) continue;

        int j;
        for (j = 0; j < m; j++) {
            if (haystack[i+j] != needle[j]) break;
        }
        
        if (j == m) return i;
    }
    
    return -1;
}