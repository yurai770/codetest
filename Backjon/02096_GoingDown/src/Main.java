import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        int[] before_max = new int[3];
        int[] before_min = new int[3];
        int[] maxvalue = new int[3];
        int[] minvalue = new int[3];
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            int num_1 = Integer.parseInt(st.nextToken());
            int num_2 = Integer.parseInt(st.nextToken());
            int num_3 = Integer.parseInt(st.nextToken());

            maxvalue[0] = Integer.max(before_max[0], before_max[1]) + num_1;
            maxvalue[1] = Integer.max(before_max[0], Integer.max(before_max[1], before_max[2])) + num_2;
            maxvalue[2] = Integer.max(before_max[1], before_max[2]) + num_3;

            minvalue[0] = Integer.min(before_min[0], before_min[1]) + num_1;
            minvalue[1] = Integer.min(before_min[0], Integer.min(before_min[1], before_min[2])) + num_2;
            minvalue[2] = Integer.min(before_min[1], before_min[2]) + num_3;

            for (int j = 0; j < 3; j++) {
                before_max[j] = maxvalue[j];
                before_min[j] = minvalue[j];
            }
        }

        int maxi = Integer.max(before_max[0], Integer.max(before_max[1], before_max[2]));
        int mini = Integer.min(before_min[0], Integer.min(before_min[1], before_min[2]));

        System.out.println(String.format("%d %d", maxi, mini));
    }
}
