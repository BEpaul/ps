import java.util.Scanner;

public class Main {

	static long[] fibo;
	static int n;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		fibo = new long[n+1];
		
		fibo[0] = 0;
		fibo[1] = 1;
		
		if (n == 1) {
			System.out.println(fibo[n]);
			return;
		}
		
		for (int i = 2; i <= n; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		System.out.println(fibo[n]);
	}
}