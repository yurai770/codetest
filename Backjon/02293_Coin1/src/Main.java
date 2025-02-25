import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = listConvert(bf.readLine().split(" "));
        int[] coins = new int[nk[0]];
        for (int i = 0; i < nk[0]; i++) {
            coins[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.asList(coins).stream().sorted();

        int[] beforelist;
        int[] nowlist = new int[nk[1] + 1];
        nowlist[0] = 1;

        for (int coin : coins) {
            if (coin > nk[1]) {
                continue;
            }
            beforelist = nowlist;
            nowlist = new int[nk[1] + 1];
            nowlist[0] = 1;
            for (int i = 0; i < nowlist.length; i++) {
                if (i - coin < 0) {
                    nowlist[i] = beforelist[i];
                    continue;
                }
                nowlist[i] = beforelist[i] + nowlist[i - coin];
            }
        }

        System.out.println(nowlist[nowlist.length - 1]);
    }

    private static int[] listConvert(String[] split) {
        int[] returnlist = new int[split.length];
        for (int i = 0; i < returnlist.length; i++) {
            returnlist[i] = Integer.parseInt(split[i]);
        }
        return returnlist;
    }
}
