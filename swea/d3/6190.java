import java.io.*;
import java.util.*;

public class Solution {
    static int t;
    static int n;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= t; testCase++) {
            n = Integer.parseInt(br.readLine());
            nums = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int answer = -1;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int temp = nums[i] * nums[j];

                    if (answer >= temp) continue;

                    String strTemp = Integer.toString(temp);
                    boolean isAscending = true;
                    for (int k = 1; k < strTemp.length(); k++) {
                        if (strTemp.charAt(k - 1) > strTemp.charAt(k)) {
                            isAscending = false;
                            break;
                        }
                    }
                    if (isAscending) answer = temp;
                }
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }
}