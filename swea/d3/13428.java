import java.io.*;
import java.util.*;

public class Solution {
    static int t;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sbMax = new StringBuilder();
        StringBuilder sbMin = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= t; testCase++) {
            sbMax.setLength(0);
            sbMin.setLength(0);

            int[] max = {-1, -1};
            int[] min = {-1, -1};

            String n = br.readLine();
            int[] nums = new int[n.length()];
            int[] maxNums = new int[n.length()];
            int[] minNums = new int[n.length()];

            String[] split = n.split("");

            for (int i = 0; i < split.length; i++) {
                nums[i] = Integer.parseInt(split[i]);
                maxNums[i] = Integer.parseInt(split[i]);
                minNums[i] = Integer.parseInt(split[i]);
            }

            boolean maxFlag = false;
            boolean minFlag = false;
            for (int i = 0; i < nums.length - 1; i++) {
                int maxVal = nums[i];
                int minVal = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    if (!maxFlag) {
                        if (maxVal <= nums[j]) {
                            maxVal = nums[j];
                            max[0] = i;
                            max[1] = j;
                        }
                    }
                    if (!minFlag) {
                        if (minVal >= nums[j] && !(i == 0 && nums[j] == 0)) {
                            minVal = nums[j];
                            min[0] = i;
                            min[1] = j;
                        }
                    }
                }
                if (max[0] != -1 && nums[max[0]] != nums[max[1]]) maxFlag = true;
                if (min[0] != -1 && nums[min[0]] != nums[min[1]]) minFlag = true;

                if (maxFlag && minFlag) break;
            }

            if (max[0] != -1) {
                int temp = maxNums[max[0]];
                maxNums[max[0]] = maxNums[max[1]];
                maxNums[max[1]] = temp;
            }

            if (min[0] != -1) {
                int temp = minNums[min[0]];
                minNums[min[0]] = minNums[min[1]];
                minNums[min[1]] = temp;
            }

            for (int ma : maxNums) {
                sbMax.append(ma);
            }

            for (int mi : minNums) {
                sbMin.append(mi);
            }

            bw.write("#" + testCase + " " + sbMin.toString() + " " + sbMax.toString() + "\n");
        }

        br.close();
        bw.close();
    }
}