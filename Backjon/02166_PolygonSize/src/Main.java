import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        int[][] xy = new int[len][2];

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            xy[i][0] = Integer.parseInt(st.nextToken());
            xy[i][1] = Integer.parseInt(st.nextToken());
        }

        long x1 = xy[0][0];
        long y1 = xy[0][1];
        long x2;
        long y2;
        long x3;
        long y3;
        long sum = 0;
        for (int i = 1; i < len - 1; i++) {
            x2 = xy[i][0];
            y2 = xy[i][1];
            x3 = xy[i + 1][0];
            y3 = xy[i + 1][1];

            sum += (x1 * y2 + x2 * y3 + x3 * y1) - (x1 * y3 + x2 * y1 + x3 * y2);
        }

        System.out.println(String.format("%.1f", (double) Math.abs(sum) / 2));

    }
}
