import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 0. 입력 받아서 도우 배열[][] 만들기, 배열 크기는 [n][n]이면 충분할듯
 * 1. 밀가루 양이 가장 작은 위치에 1만큼 더 넣어주기 (중복일 경우 모두 넣기)
 * 2. *핵심* 도우 말기 우선 현재 자르는 도우의 크기 변수 설정 - 자르는 만큼이 그 다음 도우의 높이를 결정한다.
 * 2-1. 잘라지는 크기가 남아있는 도우의 크기보다 크거나 같으면 break한다.
 * 2-2. 우선적으로 옮기는 요소들 옮기고 나서 마지막에 배열 당기자.
 * 3. 도우를 꾹 눌러주기. 배열 순회하면서 '동시'에 더하고 빼기. 이후 1차원 배열로 바꾸기
 * 4. 도우를 두 번 반으로 접어주기.
 * 5. 3의 과정(도우 꾹 눌러주기) 한번 더 반복
 * 6. 전체 배열 순회하면서 최댓값과 최솟값 차이 찾고, k 이하이면 return, 아니면 continue
 *
 * [Remind]
 * - n은 4의 배수
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static final int[] dy = {1, 0, -1, 0};

    static int n, k;
    static int[][] dough;
    static int[][] diff;
    static int[][] temp;
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dough = new int[n][n];
        diff = new int[n][n];
        temp = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dough[0][i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            // 1. 밀가루 양이 가장 작은 위치에 1만큼 더 넣어주기 (중복일 경우 모두 넣기)
            addPowder();

            // 2. *핵심* 도우 말기 우선 현재 자르는 도우의 크기 변수 설정 - 자르는 만큼이 그 다음 도우의 높이를 결정한다.
            rollDough();

            // 3. 도우를 꾹 눌러주기. 배열 순회하면서 '동시'에 더하고 빼기. 이후 1차원 배열로 바꾸기
            press();
            reArrange();

            // 4. 도우를 두 번 반으로 접어주기.
            foldTwice();

            // 5. 3의 과정(도우 꾹 눌러주기) 한번 더 반복
            press();
            reArrange();

            // 6. 전체 배열 순회하면서 최댓값과 최솟값 차이 찾고, k 이하이면 return, 아니면 continue
            boolean isDone = getDiff();
            if (isDone) break;

            answer++;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void addPowder() {
        int min = getPowderMin();
        for (int i = 0; i < n; i++) {
            if (dough[0][i] == min) {
                dough[0][i]++;
            }
        }
    }

    static int getPowderMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (min > dough[0][i]) {
                min = dough[0][i];
            }
        }

        return min;
    }

    static void rollDough() {
        int iter = 1;
        int cuttingVal = 1;
        int currentHeight = 1;
        while (true) {
            // 2-1. 잘라지는 크기가 남아있는 도우의 크기보다 크거나 같으면 break한다.
            int floorSize = getFloorSize();
            if (currentHeight > floorSize - cuttingVal) break;

            // 2-2. 우선적으로 옮기는 요소들 옮기고 나서 마지막에 배열 당기자.
            int[][] rotated = roll(cuttingVal, currentHeight);

            currentHeight = cuttingVal + 1;
            cuttingVal = pullAndFill(iter, cuttingVal, currentHeight, rotated);

            iter++;
        }
    }

    static int[][] roll(int cuttingVal, int currentHeight) {
        int[][] arr = new int[currentHeight][cuttingVal];
        for (int i = 0; i < currentHeight; i++) {
            for (int j = 0; j < cuttingVal; j++) {
                arr[i][j] = dough[i][j];
            }
        }
        int[][] rotated = rotate270(arr);

        for (int i = 1; i <= rotated.length; i++) {
            for (int j = cuttingVal; j < cuttingVal + currentHeight; j++) {
                dough[i][j] = rotated[i - 1][j - cuttingVal];
            }
        }
        return rotated;
    }

    static int[][] rotate270(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotate[i][j] = arr[j][m - 1 - i];
            }
        }

        return rotate;
    }

    static int pullAndFill(int iter, int cuttingVal, int currentHeight, int[][] rotated) {
        for (int i = 0; i < currentHeight; i++) {
            for (int j = cuttingVal; j < n; j++) {
                dough[i][j - cuttingVal] = dough[i][j];
            }
        }

        for (int i = 0; i < currentHeight; i++) {
            for (int j = 0; j < cuttingVal; j++) {
                dough[i][n - 1 - j] = 0;
            }
        }

        cuttingVal = rotated[0].length;
        return cuttingVal;
    }

    static int getFloorSize() {
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (dough[0][i] == 0) break;
            size++;
        }
        return size;
    }

    static void press() {
        int[][] diff = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dough[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if (inRange(nextX, nextY) && dough[nextX][nextY] != 0) {
                            int d = Math.abs(dough[i][j] - dough[nextX][nextY]) / 5;;
                            if (dough[i][j] > dough[nextX][nextY]) {
                                diff[i][j] -= d;
                            } else if (dough[i][j] < dough[nextX][nextY]) {
                                diff[i][j] += d;
                            }
                        }
                    }
                }
            }
        }

        dough = addArr(dough, diff);
        initArr(diff);
    }

    static void reArrange() {
        int idx = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (dough[i][j] != 0) {
                    temp[0][idx] = dough[i][j];
                    idx++;
                }
            }
        }

        initArr(dough);
        for (int i = 0; i < n; i++) {
            dough[0][i] = temp[0][i];
        }

        initArr(temp);
    }

    static void foldTwice() {
        int index = once();
        twice(index);

        initArr(temp);
    }

    static int once() {
        int index = n / 2;

        int[][] fold1 = new int[1][index];

        for (int i = 0; i < index; i++) {
            fold1[0][i] = dough[0][i];
        }

        int[][] rotated1 = rotate180(fold1);
        for (int i = 0; i < index; i++) {
            dough[1][i] = rotated1[0][i];
        }

        for (int i = index; i < n; i++) {
            dough[0][i - index] = dough[0][i];
            dough[0][i] = 0;
        }
        return index;
    }

    static void twice(int index) {
        index /= 2;
        int[][] fold2 = new int[2][index];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < index; j++) {
                fold2[i][j] = dough[i][j];
            }
        }

        int[][] rotated2 = rotate180(fold2);
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < index; j++) {
                dough[i][j] = rotated2[i - 2][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = index; j < 2 * index; j++) {
                dough[i][j - index] = dough[i][j];
                dough[i][j] = 0;
            }
        }
    }

    static int[][] rotate180(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotate[i][j] = arr[n - i - 1][m - j - 1];
            }
        }

        return rotate;
    }

    static boolean getDiff() {
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dough[0][i]);
            max = Math.max(max, dough[0][i]);
        }

        return (max - min) <= k;
    }

    static int[][] addArr(int[][] a, int[][] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] += b[i][j];
            }
        }

        return a;
    }

    static void initArr(int[][] arr) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = 0;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static void print2DArr(int[][] arr, String name) {
        System.out.println("===== " + name + " =====");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}