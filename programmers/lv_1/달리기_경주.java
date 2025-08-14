import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String, Integer> rank = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }
        
        for (String calling : callings) {
            Integer r = rank.get(calling);
            String prePlayer = players[r - 1];
            players[r - 1] = calling;
            players[r] = prePlayer;
            rank.put(calling, r - 1);
            rank.put(prePlayer, r);
        }
        
        return players;
    }
}