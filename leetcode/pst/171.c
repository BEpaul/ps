int titleToNumber(char * columnTitle){
    int res = 0;
    for (int i = 0; i < strlen(columnTitle); i++) {
        res = res * 26 + (columnTitle[i] - 'A' + 1);
    }

    return res;
}