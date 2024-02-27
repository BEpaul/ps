import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] nums;
    static int[] dp;
    static int[] p;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        dp = new int[n + 1];
        p = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int len = 1;
        dp[1] = nums[1];
        p[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (nums[i] > dp[len]) {
                len++;
                dp[len] = nums[i];
                p[i] = len;
            } else {
                int idx = binarySearch(1, len, nums[i]);
                dp[idx] = nums[i];
                p[i] = idx;
            }
        }

        bw.write(String.valueOf(len));
        bw.newLine();
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(p));

        List<Integer> answers = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            if (p[i] == len) {
                answers.add(nums[i]);
                len--;
            }
            if (len < 1) {
                break;
            }
        }

        for (int i = answers.size() - 1; i >= 0; i--) {
            bw.write(answers.get(i) + " ");
        }

        br.close();
        bw.close();
    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else if (dp[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }

        return right;
    }
}