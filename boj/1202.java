import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static long valueSum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());
		List<Jewel> jewels = new ArrayList<>();
		List<Bag> bags = new ArrayList<>();
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			jewels.add(new Jewel(weight, value));
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int capacity = Integer.parseInt(st.nextToken());
			bags.add(new Bag(capacity));
		}
		
		Collections.sort(bags, Comparator.comparingInt(Bag::getCapacity));
		Collections.sort(jewels, Comparator.comparingInt(Jewel::getWeight));
		
		
		int pJewel = 0;
		for (Bag bag : bags) {
			int curCapacity = bag.getCapacity();
			while (true) {
				if (pJewel < jewels.size() && jewels.get(pJewel).getWeight() <= curCapacity) {
					pq.add(jewels.get(pJewel));
					pJewel++;
				} else {
					break;
				}
			}
			
			if (!pq.isEmpty()) {
				valueSum += pq.poll().getValue();
			}
		}
		
		System.out.println(valueSum);
	}

}

class Bag {
	int capacity;

	public Bag(int capacity) {
		this.capacity = capacity;
	}

	
	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return "Bag [capacity=" + capacity + "]";
	}

}

class Jewel {
	int weight;
	int value;
	
	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public Jewel(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Jewel [weight=" + weight + ", value=" + value + "]";
	}
}