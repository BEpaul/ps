import java.io.*;
import java.util.*;

public class Solution {

	static int t;
	static int cnt;
	static int[] arr;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= t; testCase++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			cnt = Integer.parseInt(st.nextToken());
			arr = new int[s.length()];
			
			for (int i = 0; i < s.length(); i++) {
				arr[i] = s.charAt(i) - '0';
			}
			
			if (cnt > arr.length) cnt = arr.length;
			
			dfs(0, 0);
			
			bw.write("#" + testCase + " " + answer + "\n");
			
			answer = 0;
		}
		
		br.close();
		bw.close();	
	}
	
	static void dfs(int start, int depth) {
		if (depth == cnt) {
			int res = 0;
			for (int i = 0; i < arr.length; i++) {
				res += Math.pow(10, i) * arr[arr.length - i - 1];
			}
			
			answer = Math.max(answer, res);
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				swap(i, j);
				dfs(i, depth + 1);
				swap(i, j);
			}
		}
	}
	
	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
