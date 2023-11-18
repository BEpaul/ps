class Solution {
    public String solution(int num) {
        num = Math.abs(num);
        if (num % 2 == 1) return "Odd";
        else return "Even";
    }
}