import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = listConvert(bf.readLine().split(" "));
        int[][] board = new int[NM[1]][NM[0]];
        for (int i = 0; i < NM[1]; i++) {
            board[i] = listConvert(bf.readLine().split(" "));
        }

        int ans = 1;
        int cnt = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        for (int x = 0; x < NM[1]; x++) {
            for (int y = 0; y < NM[0]; y++) {
                if (board[x][y] == 1) {
                    cnt++;
                    queue.add(new int[] { x, y });
                }
                if (board[x][y] == -1) {
                    cnt++;
                }
            }
        }

        int[][] dxdy = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        while (queue.size() > 0) {
            int[] now = queue.pop();
            for (int[] move_vector : dxdy) {
                int move_x = now[0] + move_vector[0];
                int move_y = now[1] + move_vector[1];
                if (!(0 <= move_x && move_x < NM[1] && 0 <= move_y && move_y < NM[0])) {
                    continue;
                }
                if (board[move_x][move_y] == 0) {
                    cnt++;
                    board[move_x][move_y] = board[now[0]][now[1]] + 1;
                    if (board[move_x][move_y] > ans) {
                        ans = board[move_x][move_y];
                    }
                    queue.add(new int[] { move_x, move_y });
                }
            }
        }

        if (cnt != (NM[0] * NM[1])) {
            System.out.println(-1);
        } else {
            System.out.println(ans - 1);
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
