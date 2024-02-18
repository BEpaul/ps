import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		Stack stack = new Stack();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.countTokens() == 2) {
				String command = st.nextToken();
				int val = Integer.parseInt(st.nextToken());
				
				stack.push(val);
			} else {
				String command = st.nextToken();
				if (command.equals("pop")) {
					System.out.println(stack.pop());
				} else if (command.equals("size")) {
					System.out.println(stack.size());
				} else if (command.equals("empty")) {
					System.out.println(stack.empty());
				} else {
					System.out.println(stack.top());
				}
			}
		}
	}
}

class Stack {
	List<Integer> list;

	public Stack() {
		list = new ArrayList<>();
	}
	
	public void push(int num) {
		list.add(num);
	}
	
	public int pop() {
		if (list.isEmpty()) {
			return -1;
		}
		
		int ret = list.get(list.size() - 1); 
		list.remove(list.size() - 1);
		
		return ret;
	}
	
	public int size() {
		return list.size();
	}
	
	public int empty() {
		if (list.isEmpty()) {
			return 1;
		} 
		
		return 0;
	}
	
	public int top() {
		if (list.isEmpty()) {
			return -1;
		}
		
		return list.get(list.size() - 1);
	}
}