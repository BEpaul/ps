typedef struct {
    int s[201][201];
} NumMatrix;

NumMatrix *numMatrixCreate(int **matrix, int n, int *matrixColSize) {
    NumMatrix *a = malloc(sizeof(NumMatrix));
    int m = matrixColSize[0];
    memset(a->s, 0, 201 * 201 * sizeof(int));
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            a->s[i + 1][j + 1] = a->s[i + 1][j] + a->s[i][j + 1] - a->s[i][j] + matrix[i][j];
        }   
    }

    return a;
}

int numMatrixSumRegion(NumMatrix *a, int r1, int c1, int r2, int c2) {
    r1++; c1++; r2++; c2++;

    return a->s[r2][c2] - a->s[r1 - 1][c2] - a->s[r2][c1 - 1] + a->s[r1 - 1][c1 - 1];
}

void numMatrixFree(NumMatrix *obj) {
    free(obj);
}