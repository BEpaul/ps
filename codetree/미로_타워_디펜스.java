import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. 입력을 받고, 좌표가 아닌 일차원 List를 활용하도록 한다.
 * 2. 방향과 주어진 공격 칸 수만큼 몬스터를 공격하여 없앤다. -> List remove -> 비어있는 공간만큼 자동으로 채워진다.
 * 3. 반복문을 돌면서, 몬스터의 종류가 4번 이상 반복하여 나오면 해당 몬스터 또한 삭제한다. (remove) 더 이상 4번 이상 반복되는 경우가 없으면 반목문 탈출한다.
 * 4. List를 순회하며 같은 숫자끼리 짝짓고, 새로운 list에 추가한다.
 * 5. 이때까지의 점수를 계산한다. (remove 될때마다 점수 계산한다.)
 * 6. 이를 라운드 수만큼 반복한다.
 *
 * [Remind]
 * - d: 0: 동쪽 / 1: 남쪽 / 2: 서쪽 / 3: 북쪽
 * - 연속된 숫자 자리 4개가아니라 4개 이상이면 모두 삭제한다.
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 서 남 동 북
    static final int[] dy = {-1, 0, 1, 0};

    static int n, m;
    static int d, p;
    static int[][] map;
    static List<Integer> monsters, tempMonsters;
    static long answer;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        totalCount = n * n - 1;
        map = new int[n][n];
        monsters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapToList();
        int i;

        for (i = 0; i < m; i++) {
            // 방향과 주어진 공격 칸 수만큼 몬스터를 공격하여 없앤다. -> List remove -> 비어있는 공간만큼 자동으로 채워진다.
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            int monsterCount = monsters.size();

            attack(monsterCount);

            // 반복문을 돌면서, 몬스터의 종류가 4번 이상 반복하여 나오면 해당 몬스터 또한 삭제한다. (remove) 더 이상 4번 이상 반복되는 경우가 없으면 반복문 탈출한다.
            removeSequence();

            copyMonsters();

            // List를 순회하며 같은 숫자끼리 짝짓고, 새로운 list에 추가한다.
            createNewMonsters();
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void mapToList() {
        int x = n / 2;
        int y = n / 2;
        int dist = 1;
        int i = 0;
        boolean flag = false;
        while (true) {
            for (int j = 0; j < dist; j++) {
                x += dx[i];
                y += dy[i];

                if (map[x][y] != 0) {
                    monsters.add(map[x][y]);
                }

                if (x == 0 && y == 0) {
                    flag = true;
                    break;
                }
            }

            if (flag) break;
            i++;
            if (i % 2 == 0) dist++;
            if (i == 4) i = 0;
        }
    }

    static void attack(int monsterCount) {
        int index;
        int addValue;
        if (d == 0) { // 동
            index = 5;
            addValue = 13;
            removeMonsters(monsterCount, index, addValue);
        } else if (d == 1) { // 남
            index = 3;
            addValue = 11;
            removeMonsters(monsterCount, index, addValue);
        } else if (d == 2) { // 서
            index = 1;
            addValue = 9;
            removeMonsters(monsterCount, index, addValue);
        } else { // 북
            index = 7;
            addValue = 15;
            removeMonsters(monsterCount, index, addValue);
        }
    }

    static void removeMonsters(int monsterCount, int index, int addValue) {
        List<Integer> removeList = new ArrayList<>();
        int iter = 1;
        while (true) {
            if (index > monsterCount) {
                break;
            }
            removeList.add(index - 1);

            if (iter == p) {
                break;
            }

            index += addValue;
            addValue += 8;

            iter++;
        }

        for (int i = removeList.size() - 1; i >= 0; i--) {
            int removeIdx = (int) removeList.get(i);
            Integer remove = monsters.remove(removeIdx);
            answer += remove;
        }
    }

    static void removeSequence() {
        List<int[]> seqList = new ArrayList<>();
        while (true) {
            if (monsters.size() < 4) break;
            int seq = 1;
            int startIdx = 0;

            for (int j = 0; j < monsters.size() - 1; j++) {
                if (monsters.get(j) == monsters.get(j + 1)) {
                    seq++;
                } else {
                    if (seq >= 4) {
                        int[] arr = {startIdx, seq};
                        seqList.add(arr);
                    }

                    seq = 1;
                    startIdx = j + 1;
                }

                if (j == monsters.size() - 2 && seq >= 4) {
                    int[] arr = {startIdx, seq};
                    seqList.add(arr);
                }
            }


            if (seqList.isEmpty()) break;

            for (int j = seqList.size() - 1; j >= 0; j--) {
                int[] arr = seqList.get(j);
                for (int k = 0; k < arr[1]; k++) {
                    Integer remove = monsters.remove(arr[0]);
                    answer += remove;
                }
            }
            seqList.clear();
        }
    }

    static void copyMonsters() {
        tempMonsters = new ArrayList<>();
        tempMonsters.addAll(monsters);

        monsters.clear();
    }

    static void createNewMonsters() {
        int cnt = 1;
        int newCount = 0;
        for (int j = 0; j < tempMonsters.size(); j++) {
            if (j == tempMonsters.size() - 1) {
                addMonsters(cnt, j);
                break;
            }
            if (tempMonsters.get(j) == tempMonsters.get(j + 1)) {
                cnt++;

                if (j == tempMonsters.size() - 2) {
                    addMonsters(cnt, j);
                    break;
                }
            } else {
                addMonsters(cnt, j);
                cnt = 1;
            }
        }

        if (monsters.size() > totalCount) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(monsters);
            monsters.clear();
            for (int j = 0; j < totalCount; j++) {
                monsters.add(temp.get(j));
            }
        }
    }

    private static void addMonsters(int cnt, int j) {
        monsters.add(cnt);
        monsters.add(tempMonsters.get(j));
    }
}