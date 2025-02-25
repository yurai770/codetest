import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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
        int max_value = 0;

        int left_value;
        int right_value;

        for (int n = K; n <= N; n++) {
            if (before_left_dp[n][target_value] == 0 && before_left_dp[n][(target_value+1)/2] == 0) {
                left_value = 0;
            } else if (before_left_dp[n][(target_value+1)/2] == 0) {
                left_value = Integer.MAX_VALUE;
            } else {
                left_value = before_left_dp[n][(target_value+1)/2];
            }
            if (before_right_dp[n][target_value] == 0 && before_right_dp[n][(target_value+1)/2] == 0) {
                right_value = 0;
            } else if (before_right_dp[n][(target_value+1)/2] == 0) {
                right_value = Integer.MAX_VALUE;
            } else {
                right_value = before_right_dp[n][(target_value+1)/2];
            }

            max_value = Integer.max(max_value, Integer.min(left_value, right_value));
        }
        int answer = (target_value - max_value) * max_value;


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
