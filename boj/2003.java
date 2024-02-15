import java.util.Scanner;

public class Main {
	
	static long count = 0;
	static int n, m;
	static int low = 0, high = 0;
	static int[] nums;
	static long sum;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		n = sc.nextInt();
		m = sc.nextInt();
		
		nums = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			nums[i] = sc.nextInt();
		}
		
		for (int i = 1; i <= n; i++) {
			nums[i] = nums[i] + nums[i-1];
		}
        
		while (low <= high && high <= n) {
			sum = nums[high] - nums[low];
			
			if (sum < m) {
				high++;
			} else if (sum > m) {
				low++;
			} else {
				count++;
				high++;
			}
		}
		
		System.out.println(count);
	}
}