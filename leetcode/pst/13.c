int map (char c) {
    if (c == 'I') return 1;
    if (c == 'V') return 5;
    if (c == 'X') return 10;
    if (c == 'L') return 50;
    if (c == 'C') return 100;
    if (c == 'D') return 500;
    if (c == 'M') return 1000;
    return 0;
} 

int romanToInt(char* s) {
    int len = strlen(s), sum = 0;
    for (int i = 0; i < len; i++) {
        if (map(s[i]) < map(s[i + 1]))
            sum -= map(s[i]);
        else
            sum += map(s[i]);
    }
    return sum;
}