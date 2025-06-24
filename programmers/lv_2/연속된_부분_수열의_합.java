import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] prefixSum = buildPrefixSum(sequence);
        return findShortestSubsequence(prefixSum, k, sequence.length);
    }
    
    private int[] buildPrefixSum(int[] sequence) {
        int length = sequence.length;
        int[] prefixSum = new int[length + 1];
        
        for (int i = 0; i < length; i++) {
            prefixSum[i + 1] = prefixSum[i] + sequence[i];
        }
        
        return prefixSum;
    }
    
    private int[] findShortestSubsequence(int[] prefixSum, int target, int length) {
        int left = 0;
        int right = 1;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        while (right <= length) {
            int currentSum = prefixSum[right] - prefixSum[left];
            
            if (currentSum == target) {
                int currentLength = right - left;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    result[0] = left;
                    result[1] = right - 1; // 실제 인덱스로 변환
                }
                right++;
            } else if (currentSum > target) {
                left++;
            } else {
                right++;
            }
        }
        
        return result;
    }
}