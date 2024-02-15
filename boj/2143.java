import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static long t;
	static int n, m;
	static long count = 0;
	static Long[] A;
	static Long[] B;
	static List<Long> subA;
	static List<Long> subB;
	static long prefixSum, sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Long.parseLong(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		A = new Long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		
		B = new Long[m];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < m; i++) {
			B[i] = Long.parseLong(st.nextToken());
		}
		
		subA = new ArrayList<>();
		subB = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			prefixSum = 0;
			for (int j = i; j < n; j++) {
				prefixSum += A[j];
				subA.add(prefixSum);
			}
		}
		
		for (int i = 0; i < m; i++) {
			prefixSum = 0;
			for (int j = i; j < m; j++) {
				prefixSum += B[j];
				subB.add(prefixSum);
			}
		}

		Collections.sort(subA);
		Collections.sort(subB, Collections.reverseOrder());
		
		int pA = 0;
		int pB = 0;
		
		while(true) {
			sum = subA.get(pA) + subB.get(pB);
			long currentA = subA.get(pA);
			long currentB = subB.get(pB);
			
			if (sum > t) {
				pB++;
			} else if (sum < t) {
				pA++;
			} else {
				long countInA = 0;
				long countInB = 0;
				while (pA < subA.size() && subA.get(pA) == currentA) {
					countInA++;
					pA++;
				}
				while (pB < subB.size() && subB.get(pB) == currentB) {
					countInB++;
					pB++;
				}
				
				count += countInA * countInB;
			}
			
			if (pA >= subA.size() || pB >= subB.size()) {
				break;
			}
		}
		
		System.out.println(count);
	}

}
