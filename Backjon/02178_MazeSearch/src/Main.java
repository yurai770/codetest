import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = listConvert(bf.readLine().split(" "));

        int[][] board = new int[NM[0]][];
        for (int i = 0; i < NM[0]; i++) {
            board[i] = listConvert(bf.readLine().split(""));
        }

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });
        int[][] move_vertors = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

        while (queue.size() > 0) {
            int[] target = queue.pop();

            for (int[] dxdy : move_vertors) {
                int move_x = target[0] + dxdy[0];
                int move_y = target[1] + dxdy[1];

                if (!(0 <= move_x && move_x < NM[0] && 0 <= move_y && move_y < NM[1])) {
                    continue;
                }
                if (!(board[move_x][move_y] == 1)) {
                    continue;
                }

                board[move_x][move_y] = board[target[0]][target[1]] + 1;
                queue.add(new int[] { move_x, move_y });
            }
        }

        System.out.println(board[NM[0] - 1][NM[1] - 1]);
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}
