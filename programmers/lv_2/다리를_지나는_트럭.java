import java.util.*;

class Solution {
    static Queue<Truck> queue;
    static int weightSum;
    static int answer;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        queue = new ArrayDeque<>();
        int truckIdx = 0;
        
        while (true) {
            answer++;
            if (!queue.isEmpty() && queue.peek().arriveTime == answer) {
                Truck truck = queue.poll();
                weightSum -= truck.weight;
            }
            
            if (truckIdx < truck_weights.length && 
                weightSum + truck_weights[truckIdx] <= weight) {
                queue.offer(new Truck(truck_weights[truckIdx], answer + bridge_length));
                weightSum += truck_weights[truckIdx];
                truckIdx++;
            }
            
            if (queue.isEmpty()) break;
        }
        
        return answer;
    }
}

class Truck {
    int weight;
    int arriveTime;
    
    public Truck(int weight, int arriveTime) {
        this.weight = weight;
        this.arriveTime = arriveTime;
    }
    
    @Override
    public String toString() {
        return "Truck{weight=" + weight 
            + ", arriveTime=" + arriveTime + '}';
    }
}