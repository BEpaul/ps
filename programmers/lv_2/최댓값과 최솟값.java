class Solution {
    public String solution(String s) {
        String[] splitted = s.split(" ");
        int max = Integer.parseInt(splitted[0]);
        int min = Integer.parseInt(splitted[0]);
        for (int i = 1; i < splitted.length; i++) {
            max = Math.max(max, Integer.parseInt(splitted[i]));
            min = Math.min(min, Integer.parseInt(splitted[i]));
        }
        return Integer.toString(min) + " " + Integer.toString(max);
    }
}