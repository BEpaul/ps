import java.util.*;

class Solution {
    static String[][] map;
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        map = new String[n][n];
        for (String[] m : map) {
            Arrays.fill(m, " ");
        }
        
        decode(arr1);
        decode(arr2);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                sb.append(map[i][j]);
            }
            answer[i] = sb.toString();
            sb.setLength(0);
        }
        
        return answer;
    }
    
    void decode(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String bin = Integer.toBinaryString(arr[i]);
            for (int j = bin.length() - 1; j >= 0; j--) {
                if (map[i][bin.length() - j - 1].equals("#")) continue;
                if (bin.charAt(j) == '1') {
                    map[i][bin.length() - j - 1] = "#";
                } else {
                    map[i][bin.length() - j - 1] = " ";
                }
            }
        }
    }
}