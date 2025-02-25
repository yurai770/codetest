import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MainFail1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] left_team = new int[N + 1];
        int[] right_team = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            left_team[i + 1] = Integer.parseInt(st.nextToken());
            right_team[i + 1] = Integer.parseInt(st.nextToken());
        }

        int[][] left_dp = new int[N + 1][X * K + 1];
        int[][] right_dp = new int[N + 1][X * K + 1];
        int[][] before_left_dp = new int[N + 1][(X * K) + 1];
        int[][] before_right_dp = new int[N + 1][(X * K) + 1];
        int[][] tmp;

        int depth = 0;
        while (depth < K) {
            for (int n = 1; n <= N; n++) {
                for (int idx = 1; idx < left_dp[0].length; idx++) {
                    if (n < depth) {
                        left_dp[n][idx] = before_left_dp[n][idx];
                        right_dp[n][idx] = before_right_dp[n][idx];
                        continue;
                    }
                    if (idx < left_team[n]) {
                        left_dp[n][idx] = left_dp[n - 1][idx];
                    } else {
                        left_dp[n][idx] = Integer.max(left_dp[n - 1][idx],
                                before_left_dp[n - 1][idx - left_team[n]] + left_team[n]);
                    }

                    if (idx < right_team[n]) {
                        right_dp[n][idx] = right_dp[n - 1][idx];
                    } else {
                        right_dp[n][idx] = Integer.max(right_dp[n - 1][idx],
                                before_right_dp[n - 1][idx - right_team[n]] + right_team[n]);
                    }
                }
            }
            tmp = before_left_dp;
            before_left_dp = left_dp;
            left_dp = tmp;

            tmp = before_right_dp;
            before_right_dp = right_dp;
            right_dp = tmp;

            depth++;
        }

        int target_value = X * K;

        int answer = 0;
        for (int n = 1; n <= N; n++) {
            int v = before_left_dp[n][target_value];
            answer = Integer.max(answer, (target_value - v) * v);
        }

        printBoard(before_left_dp);
        printBoard(before_right_dp);

        System.out.println(answer);
    }

    static void printBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] line : board) {
            for (int num : line) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
