int stack[51], top, sum;

int scoreOfParentheses(char* s) {
    top = 0;

    while (*s) {
        if (*s == '(') {
            stack[top++] = -1;
        } else if (stack[top - 1] == -1) {
            stack[top - 1] = 1;
        } else {
            sum = 0;
            while(stack[--top] != -1) {
                sum += stack[top];
            }
            
            stack[top++] = sum * 2;
        }
        s++;
    }

    sum = 0;
    while (--top >= 0) {
        sum += stack[top];
    }
    
    return sum;
}