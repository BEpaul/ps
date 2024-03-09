import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] nums, max, min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n][3];
        max = new int[n][3];
        min = new int[n][3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max[0][0] = min[0][0] = nums[0][0];
        max[0][1] = min[0][1] = nums[0][1];
        max[0][2] = min[0][2] = nums[0][2];

        for (int i = 1; i < n; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + nums[i][0];
            max[i][1] = Math.max(max[i - 1][0], Math.max(max[i - 1][1], max[i - 1][2])) + nums[i][1];
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + nums[i][2];
            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + nums[i][0];
            min[i][1] = Math.min(min[i - 1][0], Math.min(min[i - 1][1], min[i - 1][2])) + nums[i][1];
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + nums[i][2];
        }

        bw.write(Math.max(max[n - 1][0], Math.max(max[n - 1][1], max[n - 1][2])) + " ");
        bw.write(String.valueOf(Math.min(min[n - 1][0], Math.min(min[n - 1][1], min[n - 1][2]))));

        br.close();
        bw.close();
    }
}