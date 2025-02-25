import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[][] sumlist;
    static int[] numlist;

    public static void main(String[] args) throws Exception {
        int answer = 0;
        ArrayList<Integer> anslist = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int len;
        int sum;

        for (int testcase = 0; testcase < T; testcase++) {
            len = Integer.parseInt(bf.readLine());
            sumlist = new int[len - 1][len - 1];
            numlist = listConvert(bf.readLine().split(" "));
            sum = 0;

            for (int num : numlist) {
                sum += num;
            }

            answer = findAnswer(0, numlist.length - 1, sum);
            anslist.add(answer);
        }

        for (int ans : anslist) {
            System.out.println(ans);
        }
    }

    static int findAnswer(int start_idx, int end_idx, int sum_s_to_e) {
        if (end_idx - start_idx == 0) {
            return 0;
        }

        if (sumlist[end_idx - start_idx - 1][start_idx] != 0) {
            return sumlist[end_idx - start_idx - 1][start_idx];
        }

        int cost = 0;
        int min_cost = Integer.MAX_VALUE;
        int sum_s_to_i = 0;

        for (int idx = start_idx; idx < end_idx; idx++) {
            sum_s_to_i += numlist[idx];
            cost = findAnswer(start_idx, idx, sum_s_to_i) + sum_s_to_e
                    + findAnswer(idx + 1, end_idx, sum_s_to_e - sum_s_to_i);
            if (min_cost > cost) {
                min_cost = cost;
            }
        }
        sumlist[end_idx - start_idx - 1][start_idx] = min_cost;
        return min_cost;
    }

    static int[] listConvert(String[] strlist) {
        int[] intlist = new int[strlist.length];

        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(strlist[i]);
        }
        return intlist;
    }
}