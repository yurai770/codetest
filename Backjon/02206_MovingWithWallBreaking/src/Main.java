import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][m];
        int[][] startboard = new int[n][m];
        int[][] endboard = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
                startboard[i][j] = -1;
                endboard[i][j] = -1;
            }
        }

        int[][] dxdy = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 0, 0 });
        startboard[0][0] = 0;
        while (queue.size() > 0) {
            int[] target = queue.pop();
            for (int[] vector : dxdy) {
                int move_x = target[0] + vector[0];
                int move_y = target[1] + vector[1];
                if (!(0 <= move_x && move_x < n && 0 <= move_y && move_y < m)) {
                    continue;
                }
                if (startboard[move_x][move_y] >= 0) {
                    continue;
                }

                startboard[move_x][move_y] = startboard[target[0]][target[1]] + 1;
                if (board[move_x][move_y] == '0') {
                    queue.add(new int[] { move_x, move_y });
                }

            }
        }

        queue.add(new int[] { n - 1, m - 1 });
        endboard[n - 1][m - 1] = 0;
        while (queue.size() > 0) {
            int[] target = queue.pop();
            for (int[] vector : dxdy) {
                int move_x = target[0] + vector[0];
                int move_y = target[1] + vector[1];
                if (!(0 <= move_x && move_x < n && 0 <= move_y && move_y < m)) {
                    continue;
                }
                if (endboard[move_x][move_y] >= 0) {
                    continue;
                }

                endboard[move_x][move_y] = endboard[target[0]][target[1]] + 1;
                if (board[move_x][move_y] == '0') {
                    queue.add(new int[] { move_x, move_y });
                }
            }
        }

        int shortest_path = Integer.MAX_VALUE;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (startboard[x][y] == -1 || endboard[x][y] == -1) {
                    continue;
                }
                shortest_path = Integer.min(shortest_path, startboard[x][y] + endboard[x][y] + 1);
            }
        }

        if (shortest_path == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(shortest_path);
        }
    }

}