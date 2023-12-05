char *s, p[2001];
int n, m, memo[2001][2001];

bool match(int i, int j)
{
    if (memo[i][j] >= 0) {
        return memo[i][j];
    }

    if (i == n) {
        if (j == m) {
            return memo[i][j] = 1; 
        }
    
        if (p[j] == '*') {
            return memo[i][j] = match(i, j + 1);
        }
    
        return memo[i][j] = 0;
    }

    if ('a' <= p[j] && p[j] <= 'z' && s[i] == p[j]) {
        return memo[i][j] = match(i + 1, j + 1);
    } else if (p[j] == '?') {
        return memo[i][j] = match(i + 1, j + 1);
    } else if (p[j] == '*') {
        for (int k = 0; i + k <= n; k++) {
            if (memo[i + k][j + 1] = match(i + k, j + 1)) {
                return memo[i][j] = 1;
            }
        }
    }

    return memo[i][j] = 0;
}

bool isMatch(char *s_, char *p_)
{
    s = s_;
    p[0] = p_[0];
    m = 1;

    for (int i = 1; i < strlen(p_); i++) {
        if (p[m - 1] == '*' && p_[i] == '*') {
            continue;
        }
        p[m++] = p_[i];
    }
    p[m] = 0;

    n = strlen(s);
    m = strlen(p); 

    memset(memo, -1, sizeof(memo));
    return match(0, 0);
}