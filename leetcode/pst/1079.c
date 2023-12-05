int count = 0;
int arr[26];

void gen() {
    for (int i = 0; i < 26; i++) {
        if (arr[i]) {
            arr[i]--;
            count++;
            gen();
            arr[i]++;
        }
    }
}

int numTilePossibilities(char * tiles){
    int i;
    count = 0;
    
    for (i = 0; i < 26; i++) {
        arr[i] = 0;
    }
    
    for (i = 0; i < strlen(tiles); i++) {
        arr[tiles[i] - 'A']++;
    }
    gen();

    return count;
}