import java.io.*;
import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int i = 0;
        int j = A.length - 1;
        while (true) {
            answer += A[i] * B[j];
            i++;
            j--;
            
            if (j == -1) {
                break;
            }
        }

        return answer;
    }
}