#include <string.h>

int romanToInt(char * s){
    int i, result = 0;
    for (i = 0; i < strlen(s); i++) {
        if (s[i] == 'I') {
            if (i != strlen(s) - 1 && s[i+1] == 'V') {
                result += 4;
                i++;
            } else if (i != strlen(s) - 1 && s[i+1] == 'X'){
                result += 9;
                i++;
            } else result += 1;
        } else if (s[i] == 'V') {
            result += 5;
        } else if (s[i] == 'X') {
            if (i != strlen(s) - 1 && s[i+1] == 'L') {
                result += 40;
                i++;
            } else if (i != strlen(s) - 1 && s[i+1] == 'C') {
                result += 90;
                i++;
            } else result += 10;
        } else if (s[i] == 'L') {
            result += 50;
        } else if (s[i] == 'C') {
            if (i != strlen(s) - 1 && s[i+1] == 'D') {
                result += 400;
                i++;
            } else if (i != strlen(s) - 1 && s[i+1] == 'M') {
                result += 900;
                i++;
            } else result += 100;
        } else if (s[i] == 'D') {
            result += 500;
        } else if (s[i] == 'M') {
            result += 1000;
        }
    }

    return result;
}