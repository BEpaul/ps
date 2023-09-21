int charList[128];

int lengthOfLongestSubstring(char * s){
    int left = 0, res = 0, right;
    for (int i = 0; i < 128; i++) charList[i] = 0;

    for (right = 0; right < strlen(s); right++) {
        while(charList[s[right]] && left < right) charList[s[left++]]--;
        charList[s[right]]++;
        if (right - left + 1 > res) res = right - left + 1;
    }
    
    return res;
}