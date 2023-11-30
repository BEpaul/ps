int stack[1001], top;

bool validateStackSequences(int* pushed, int pushedSize, int* popped, int poppedSize) {
    top = 0;
    int j = 0;
    for (int i = 0; i < pushedSize; i++) {
        stack[top++] = pushed[i];

        while (top > 0 && stack[top - 1] == popped[j]) {
            top--;
            j++;
        }
    }

    return j == pushedSize;
}