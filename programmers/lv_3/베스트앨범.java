import java.util.*;

class Solution {
    static Map<String, Integer> genreTotal = new HashMap<>();
    static Map<String, PriorityQueue<Song>> genreSongs = new HashMap<>();
    static List<Integer> answer = new ArrayList<>();
    
    public List<Integer> solution(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            if (genreTotal.get(genres[i]) != null) {
                genreTotal.put(genres[i], genreTotal.get(genres[i]) + plays[i]);
                PriorityQueue<Song> pq = genreSongs.get(genres[i]);
                pq.offer(new Song(i, plays[i]));
                genreSongs.put(genres[i], pq);
            } else {
                genreTotal.put(genres[i], plays[i]);
                PriorityQueue<Song> pq = new PriorityQueue<>(Comparator.comparingInt(Song::getPlay).reversed()
                                                             .thenComparingInt(Song::getNum));
                pq.offer(new Song(i, plays[i]));
                genreSongs.put(genres[i], pq);
            }
        }
        
        List<String> totalKeySet = new ArrayList<>(genreTotal.keySet());
        totalKeySet.sort((o1, o2) -> genreTotal.get(o2) - genreTotal.get(o1));
        
        for (String key : totalKeySet) {
            PriorityQueue<Song> pq = genreSongs.get(key);
            if (pq.size() > 1) {
                Song s1 = pq.poll();
                Song s2 = pq.poll();
                answer.add(s1.num);
                answer.add(s2.num);
            } else {
                Song s = pq.poll();
                answer.add(s.num);
            }
        }
        
        return answer;
    }
}

class Song {
    int num;
    int play;
    
    public Song(int num, int play) {
        this.num = num;
        this.play = play;
    }
    
    public int getNum() {
        return num;
    }
    
    public int getPlay() {
        return play;
    }
    
    @Override
    public String toString() {
        return "Song{" + "num=" + num + ", play=" + play + '}';
    }
}