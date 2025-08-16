import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();
        
        str1Map = createStrMap(str1, str1Map);
        str2Map = createStrMap(str2, str2Map);
        
        if (str1Map.isEmpty() && str2Map.isEmpty()) return 65536;
        int totalCount = 0;
        int sameCount = 0;
        
        for (String key : str1Map.keySet()) {
            if (str2Map.containsKey(key)) {
                sameCount += Math.min(str1Map.get(key), str2Map.get(key));
                totalCount += Math.max(str1Map.get(key), str2Map.get(key));
            } else {
                totalCount += str1Map.get(key);
            }
        }
        
        for (String key : str2Map.keySet()) {
            if (!str1Map.containsKey(key)) {
                totalCount += str2Map.get(key);
            }
        }
        
        float res = ((float) sameCount / (float) totalCount) * 65536;
        answer = (int) Math.floor(res);
        
        return answer;
    }
    
    Map<String, Integer> createStrMap(String str, Map<String, Integer> strMap) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (!(isAlphabet(str.charAt(i)) && isAlphabet(str.charAt(i + 1)))) continue;
            String subSet = str.substring(i, i + 2);
            int cnt = strMap.getOrDefault(subSet, 0);
            strMap.put(subSet, cnt + 1);
        }
        return strMap;
    }
    
    boolean isAlphabet(char c) {
        return ('a' <= c && c <= 'z');
    }
}