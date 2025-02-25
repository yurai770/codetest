import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static int alreadyFindBit;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        alreadyFindBit = 0;

        for (int r = 0; r < R; r++) {
            String tmp = bf.readLine();
            for (int c = 0; c < C; c++) {
                board[r][c] = tmp.charAt(c) - 65;
            }
        }

        alreadyFindBit = alreadyFindBit | 1 << board[0][0];
        int answer = dfs(0, 0, 1);

        System.out.println(answer);
    }

    private static int dfs(int x, int y, int depth) {
        boolean notfind = true;
        int[][] dxdy = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int max_value = 0;

        for (int[] vector : dxdy) {
            int move_x = x + vector[0];
            int move_y = y + vector[1];
            if (!(0 <= move_x && move_x < board.length && 0 <= move_y && move_y < board[0].length)) {
                continue;
            }

            if ((alreadyFindBit & 1 << board[move_x][move_y]) > 0) {
                continue;
            }

            alreadyFindBit = alreadyFindBit | 1 << board[move_x][move_y];
            max_value = Integer.max(max_value, dfs(move_x, move_y, depth + 1));
            notfind = false;
            alreadyFindBit = alreadyFindBit ^ 1 << board[move_x][move_y];
        }

        if (notfind) {
            return depth;
        } else {
            return max_value;
        }

    }
}
