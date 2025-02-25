import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for (int tc = 0; tc < T; tc++) {
            int len = Integer.parseInt(bf.readLine());
            int[] start_position = listConvert(bf.readLine().split(" "));
            int[] end_position = listConvert(bf.readLine().split(" "));
            int[][] board = new int[len][len];

            int[][] move_range = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
                    { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };
            Deque<int[]> idx_queue = new ArrayDeque<>();
            idx_queue.add(start_position);
            while (idx_queue.size() > 0) {
                int[] target = idx_queue.pop();
                if (target[0] == end_position[0] && target[1] == end_position[1]) {
                    break;
                }

                for (int[] move_vector : move_range) {
                    int move_x = target[0] + move_vector[0];
                    int move_y = target[1] + move_vector[1];

                    if (!(0 <= move_x && move_x < len && 0 <= move_y && move_y < len)) {
                        continue;
                    }
                    if (board[move_x][move_y] == 0) {
                        board[move_x][move_y] = board[target[0]][target[1]] + 1;
                        idx_queue.add(new int[] { move_x, move_y });
                    }
                }
            }
            // sb.append(board[end_position[0]][end_position[1]] + "\n");
            System.out.println(board[end_position[0]][end_position[1]]);
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
