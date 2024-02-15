import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int start = 0, mid, end;
	static int[] trees;
	static int sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		trees = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		int max = -1;
		
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(trees[i], max);
		}
		
		end = max;
		binarySearch();
		
	}
	
	public static void binarySearch() {
		int result = 0;
		
		while (start <= end) {
			mid = (start + end) / 2;
			long sum = calcSum(mid);
			
			if (sum < m) {
				end = mid - 1;
			} else if (sum > m) {
				result = mid;
				start = mid + 1;
			} else {
				result = mid;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static long calcSum (int value) {
		long result = 0;
		for (int t : trees) {
			if (t > value) {
				result += t - value;
			}
		}
		
		return result;
	}
}