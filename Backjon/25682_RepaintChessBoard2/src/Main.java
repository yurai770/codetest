import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] nmk = listConvert(bf.readLine().split(" "));
        String[][] board = new String[nmk[0]][];
        int[][][] dp = new int[2][nmk[0] + 1][nmk[1] + 1];
        String check_color = "B";
        int sum_black_start;
        int sum_white_start;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < nmk[0]; i++) {
            board[i] = bf.readLine().split("");
        }

        for (int x = 0; x < nmk[0]; x++) {
            if (x % 2 == 0) {
                check_color = "W";
            } else {
                check_color = "B";
            }
            for (int y = 0; y < nmk[1]; y++) {
                if (check_color.equals("B")) {
                    check_color = "W";
                } else {
                    check_color = "B";
                }

                if (board[x][y].equals(check_color)) {
                    dp[1][x + 1][y + 1] = 1;
                } else {
                    dp[0][x + 1][y + 1] = 1;
                }

                for (int z = 0; z < 2; z++) {
                    dp[z][x + 1][y + 1] = dp[z][x + 1][y + 1] + dp[z][x][y + 1] + dp[z][x + 1][y] - dp[z][x][y];
                }
            }
        }

        for (int x = 0; x < nmk[0] - nmk[2] + 1; x++) {
            for (int y = 0; y < nmk[1] - nmk[2] + 1; y++) {
                sum_black_start = dp[0][x + nmk[2]][y + nmk[2]] - dp[0][x][y + nmk[2]] - dp[0][x + nmk[2]][y]
                        + dp[0][x][y];
                sum_white_start = dp[1][x + nmk[2]][y + nmk[2]] - dp[1][x][y + nmk[2]] - dp[1][x + nmk[2]][y]
                        + dp[1][x][y];

                answer = Integer.min(answer, Integer.min(sum_white_start, sum_black_start));
            }
        }

        System.out.println(answer);
    }

    static int[] listConvert(String[] strlist) {
        int[] intlist = new int[strlist.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(strlist[i]);
        }
        return intlist;
    }
}
