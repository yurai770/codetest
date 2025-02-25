import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[] numlist = listConvert(bf.readLine().split(" "));

        int min_sum = 0;
        int max_sum = 0;
        int sum = 0;
        int max_value = 0;
        int max_minus_num = Integer.MIN_VALUE;
        boolean only_minus = true;

        for (int idx = 0; idx < numlist.length; idx++) {
            sum += numlist[idx];
            if (min_sum > sum) {
                if (max_value < max_sum - min_sum) {
                    max_value = max_sum - min_sum;
                }
                min_sum = sum;
                max_sum = sum;
            }
            if (max_sum < sum) {
                max_sum = sum;
            }

            if (numlist[idx] >= 0) {
                only_minus = false;
            }

            if (only_minus & numlist[idx] > max_minus_num) {
                max_minus_num = numlist[idx];
            }

        }

        if (max_value < max_sum - min_sum) {
            max_value = max_sum - min_sum;
        }
        if (only_minus) {
            max_value = max_minus_num;
        }

        System.out.println(max_value);
    }

    static int[] listConvert(String[] strlist) {
        int[] intlist = new int[strlist.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(strlist[i]);
        }
        return intlist;
    }
}
