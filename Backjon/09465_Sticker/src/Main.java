import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            int len = Integer.parseInt(bf.readLine());
            int[][] sticker = new int[2][];
            for (int i = 0; i < 2; i++) {
                sticker[i] = listConvert(bf.readLine().split(" "));
            }
            int[][] dp = new int[2][len];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for (int y = 1; y < len; y++) {
                for (int x = 0; x < 2; x++) {
                    dp[x][y] = Integer.max(dp[x][y - 1], sticker[x][y] + dp[(x + 1) % 2][y - 1]);
                }
            }

            System.out.println(Integer.max(dp[0][len - 1], dp[1][len - 1]));
        }
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}
