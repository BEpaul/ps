import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int n, k;
	static Candidate[] candidates;
	static List<Candidate> list;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		candidates = new Candidate[101];
		list = new ArrayList<>();
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		for (int i = 0; i < k; i++) {
			int num = sc.nextInt();
			if (candidates[num] == null) { // 최초 호출됨
				candidates[num] = new Candidate(num, 0, 0, false);
			}
		
			if (candidates[num].isIn) { // 해당 후보가 사진틀에 존재
				candidates[num].count++;
			} else {
				if (list.size() == n) {
					Collections.sort(list);
					Candidate c = list.remove(n-1);
					c.isIn = false;
				}
				
				candidates[num].count = 1;
				candidates[num].isIn = true;
				candidates[num].timeStamp = i;
				list.add(candidates[num]);
			}
		}
		
		Collections.sort(list, new Comparator<Candidate>() {
			@Override
			public int compare(Candidate a, Candidate b) {
				return Integer.compare(a.num, b.num);
			}
		});

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).num);
			if (i != n - 1) {
				 System.out.print(" ");
			}
		}
	}
}

class Candidate implements Comparable<Candidate> {
	int num;
	int count;
	int timeStamp;
	boolean isIn;
	
	public Candidate(int num, int count, int timeStamp, boolean isIn) {
		this.num = num;
		this.count = count;
		this.timeStamp = timeStamp;
		this.isIn = isIn;
	}
	
	@Override
	public String toString() {
		return "Candidate [num=" + num + ", count=" + count + ", timeStamp=" + timeStamp + ", isIn=" + isIn + "]";
	}

	@Override
	public int compareTo(Candidate other) {
		int result = Integer.compare(other.count, count);
		if (result == 0) {
			return Integer.compare(other.timeStamp, timeStamp);
		}
		
		return result;
	}
}