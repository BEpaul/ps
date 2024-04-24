import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. 입력 받아 각각 절반을 2개의 데크에 넣는다.
 * 2. 무빙워크의 움직임을 각각의 데크의 넣고 빼는 것으로 구현한다.
 * 3. 무빙워크 칸 각각은 번호와 안정성을 갖고 있다. (class 분리)
 * 4. 사람이 올라간 칸은 별도의 배열로 표시한다. <- 해보면서 조절
 *
 *
 * [Remind]
 * - 안정성이 0인 칸이 발생할 때마다 카운트를 해주고, 카운트가 k가 될 때 프로그램 종료
 * - 1~3 과정 중 n번 칸의 위치에 사람이 위치하면 그 즉시 내리게 된다.
 * - 배열의 끝 지점에서 시작지점으로 이어주는 것 생각하자.
 */

public class Main {
    static int n, k;
    static Deque<Integer> onRails = new ArrayDeque<>();
    static Deque<Integer> offRails = new ArrayDeque<>();
    static int answer;
    static boolean[] userInfo;
    static int[] safeInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        userInfo = new boolean[2 * n + 1];
        safeInfo = new int[2 * n + 1];

        st = new StringTokenizer(br.readLine());


        for (int i = 1; i <= 2 * n; i++) {
            int safeness = Integer.parseInt(st.nextToken());
            if (i <= n) {
                onRails.add(i);
            } else {
                offRails.addFirst(i);
            }

            safeInfo[i] = safeness;
        }

        int safeCount = 0;
        while (true) {
            // 1. 무빙워크가 한 칸 회전한다.
            Integer rail1 = onRails.pollLast();
            offRails.addLast(rail1);
            Integer rail2 = offRails.pollFirst();
            onRails.addFirst(rail2);
            userInfo[onRails.peekLast()] = false;

            // 2. 가장 먼저 무빙워크에 올라간 사람부터 무빙워크가 회전하는 방향으로 한칸 이동할 수 있으면 이동한다.
            // 만약 앞선 칸에 사람이 있거나 안정성이 0이면 이동하지 않는다.
            int index = onRails.peekLast();

            for (int i = 0; i < n; i++) {
                int prev;
                if (index == 1) {
                    prev = 2 * n;
                } else {
                    prev = index - 1;
                }

                if (!userInfo[index] && safeInfo[index] != 0) {
                    if (userInfo[prev]) {
                        userInfo[prev] = false;
                        userInfo[index] = true;
                        safeInfo[index]--;

                        if (safeInfo[index] == 0) safeCount++;
                    }
                }

                index--;
                if (index == 0) {
                    index = 2 * n;
                }
            }
            userInfo[onRails.peekLast()] = false;

            // 3. 1번 칸에 사람이 없고 안정성이 0이 아니라면 사람을 한 명 더 올린다.
            int first = onRails.peekFirst();
            if (!userInfo[first] && safeInfo[first] != 0) {
                userInfo[first] = true;
                safeInfo[first]--;

                if (safeInfo[first] == 0) safeCount++;
            }

            // 4. 안정성이 0인 칸이 k개 이상이라면 과정을 종료한다. 그렇지 않다면 과정을 반복한다.
            answer++;

            if (safeCount >= k) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}