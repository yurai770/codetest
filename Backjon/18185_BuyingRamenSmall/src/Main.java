import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[] ramens = listConvert(bf.readLine().split(" "));

        int ans = 0;
        for (int idx = 0; idx < ramens.length; idx++) {
            // 3
            if (idx == len - 1 ) {
                ans += ramens[idx] * 2;
                continue;
            }
            // 5
            if (idx == len - 2 && ramens[idx] < 0) {
                ans += ramens[idx] * 4;
                ramens[idx + 1] += ramens[idx];
                continue;
            }

            // 7
            ans += ramens[idx] * 7;
            ramens[idx + 1] -= ramens[idx];
            ramens[idx + 2] -= ramens[idx];
        }
        System.out.println(ans);
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length + 2];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}
