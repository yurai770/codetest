import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = listConvert(bf.readLine().split(" "));
        int[] memories = listConvert(bf.readLine().split(" "));
        int[] costs = listConvert(bf.readLine().split(" "));
        int cost_sum = 0;
        for (int cost : costs) {
            cost_sum += cost;
        }

        int[] before_list;
        int[] now_list = new int[cost_sum + 1];
        int idx = now_list.length;

        for (int i = 0; i < NM[0]; i++) {
            before_list = now_list;
            now_list = new int[idx];

            for (idx = 0; idx < now_list.length; idx++) {
                if (idx - costs[i] < 0) {
                    now_list[idx] = before_list[idx];
                    continue;
                }
                now_list[idx] = Integer.max(before_list[idx], memories[i] + before_list[idx - costs[i]]);

                if (now_list[idx] >= NM[1]) {
                    break;
                }
            }

        }

        System.out.println(idx);

    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}
