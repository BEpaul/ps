bool isUgly(int n){
    if (n <= 0) {
        return false;
    } else {
        int divider = 2;
        while (n > 1 && divider <= 5) {
            if (n % divider == 0) {
                n /= divider;
            } else {
                divider++;
                continue;
            }
        }

        if (divider > 5) {
            return false;
        }
        return true;
    }
}