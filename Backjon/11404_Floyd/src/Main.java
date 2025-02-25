import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_VALUE = 50000000;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        int[][] bus = new int[n][n];
        StringTokenizer st;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (x != y) {
                    bus[x][y] = MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if (bus[a][b] != 0 && bus[a][b] < c) {
                continue;
            }
            bus[a][b] = c;
        }

        for (int t = 0; t < n; t++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (x == y) {
                        bus[x][y] = 0;
                    }
                    bus[x][y] = Integer.min(bus[x][y], bus[x][t] + bus[t][y]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] line : bus) {
            for (int num : line) {
                if (num == MAX_VALUE) {
                    sb.append(0).append(" ");

                } else {
                    sb.append(num).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
