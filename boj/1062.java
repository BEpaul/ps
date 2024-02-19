import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static String[] words;
    static boolean[] visited;
    static int length;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        visited = new boolean[26];

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < n; i++) {
            words[i] = (br.readLine()).replaceAll("[antic]", "");
        }

        if (k < 5) {
            answer = 0;
        } else if (k == 26) {
            answer = n;
        } else {
            k -= 5;

            if (k == 0) {
                answer = countWords();
            } else {
                for (int i = 0; i < 26; i++) {
                    if (!visited[i]) {
                        dfs(i);
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void dfs(int index) {
        visited[index] = true;
        length++;

        if (length == k) {
            answer = Math.max(countWords(), answer);
        } else {
            for (int i = index + 1; i < 26; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }

        visited[index] = false;
        length--;
    }

    static int countWords() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            boolean isReadable = true;
            String word = words[i];

            for (int j = 0; j < word.length(); j++) {
                if (!visited[word.charAt(j) - 'a']) {
                    isReadable = false;
                    break;
                }
            }

            if (isReadable) {
                count++;
            }
        }

        return count;
    }
}