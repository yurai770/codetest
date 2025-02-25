import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        char[][] board = new char[len][len];
        int[][][] dp = new int[3][len][len];

        StringTokenizer st;
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = 0;
            while (st.hasMoreTokens()) {
                board[i][x++] = st.nextToken().charAt(0);
            }
        }

        // 0 = ㅡ , 1 = \ , 2 = |
        dp[0][0][1] = 1;
        for (int x = 0; x < len; x++) {
            for (int y = 0; y < len; y++) {
                if (board[x][y] == '1') {
                    continue;
                }
                if (inBoard(x, y - 1, len)) {
                    dp[0][x][y] += dp[0][x][y - 1] + dp[1][x][y - 1];
                }
                if (inBoard(x - 1, y - 1, len)) {
                    if (!(board[x - 1][y] == '1' || board[x][y - 1] == '1')) {
                        dp[1][x][y] += dp[0][x - 1][y - 1] + dp[1][x - 1][y - 1] + dp[2][x - 1][y - 1];
                    }
                }
                if (inBoard(x - 1, y, len)) {
                    dp[2][x][y] += dp[1][x - 1][y] + dp[2][x - 1][y];
                }
            }
        }

        System.out.println(dp[0][len - 1][len - 1] + dp[1][len - 1][len - 1] + dp[2][len - 1][len - 1]);

    }

    static boolean inBoard(int x, int y, int len) {
        if (0 <= x && x < len && 0 <= y && y < len) {
            return true;
        }
        return false;
    }
}
