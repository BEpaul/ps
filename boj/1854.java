import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int n, m, k;
	static List<City>[] adjs;
	static PriorityQueue<Integer>[] dist;
	static PriorityQueue<City> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		adjs = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adjs[i] = new ArrayList<>();
		}
		
		int a, b, c;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjs[a].add(new City(b, c));
		}
		
		dist = new PriorityQueue[n + 1];
		Comparator<Integer> cp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 > o2 ? -1 : 1;
			}
		};
		
		for (int i = 0; i < n + 1; i++) {
			dist[i] = new PriorityQueue<Integer>(k, cp);
		}
		
		dist[1].add(0);
		
		pq = new PriorityQueue<>(Comparator.comparingInt(City::getCost));
		pq.add(new City(1, 0));
		
		City now, next;
		while (!pq.isEmpty()) {
			now = pq.poll();
			
			for (int i = 0; i < adjs[now.getIndex()].size(); i++) {
				next = adjs[now.getIndex()].get(i);
				
				if (dist[next.getIndex()].size() < k) {
					// 저장된 경로가 k개보다 적은 경우 그냥 추가한다.
					dist[next.getIndex()].add(now.getCost() + next.getCost());
					pq.add(new City(next.getIndex(), now.getCost() + next.getCost()));
				} else if (dist[next.getIndex()].peek() > now.getCost() + next.getCost()) {
					// 저장된 경로가 k개이고, 현재 가장 큰 값보다 작다면
					dist[next.getIndex()].poll();
					dist[next.getIndex()].add(now.getCost() + next.getCost());
					pq.add(new City(next.getIndex(), now.getCost() + next.getCost()));
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			if (dist[i].size() == k) {
				bw.write(dist[i].peek() + "\n");
			} else {
				bw.write(-1 + "\n");
			}
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
}

class City {
	int index;
	int cost;

	public City(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	public int getIndex() {
		return index;
	}

	public int getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return "city [index=" + index + ", cost=" + cost + "]";
	}
}