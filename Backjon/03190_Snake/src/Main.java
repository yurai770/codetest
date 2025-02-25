import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[][] board = new int[len + 2][len + 2];
        String[][] spin_list;

        int apple_num = Integer.parseInt(bf.readLine());
        for (int i = 0; i < apple_num; i++) {
            int[] colrow = listConvert(bf.readLine().split(" "));
            board[colrow[0]][colrow[1]] = 1;
        }

        int spin_num = Integer.parseInt(bf.readLine());
        spin_list = new String[spin_num][];
        for (int i = 0; i < spin_num; i++) {
            spin_list[i] = bf.readLine().split(" ");
        }

        for (int i = 0; i < board.length; i++) {
            if (i == 0 || i == board.length - 1) {
                for (int idx = 0; idx < board.length; idx++) {
                    board[i][idx] = -1;
                }
                continue;
            }
            board[i][0] = -1;
            board[i][board.length - 1] = -1;
        }

        int move_count = 0;
        int spin_idx = 0;
        int[] move_vector = { 0, 1 };
        int[] head = { 1, 1 };
        List<int[]> snake = new LinkedList<>();
        snake.add(head);
        while (true) {
            move_count++;

            head[0] += move_vector[0];
            head[1] += move_vector[1];

            if (board[head[0]][head[1]] == -1) {
                break;
            }
            if (board[head[0]][head[1]] == 0) {
                int[] tail = snake.remove(0);
                board[tail[0]][tail[1]] = 0;
            }

            board[head[0]][head[1]] = -1;
            snake.add(new int[] { head[0], head[1] });

            if (spin_idx >= spin_list.length) {
                continue;
            }

            if (Integer.parseInt(spin_list[spin_idx][0]) == move_count) {
                if (spin_list[spin_idx][1].equals("L")) {
                    int tmp = move_vector[0];
                    move_vector[0] = move_vector[1] * -1;
                    move_vector[1] = tmp;
                } else {
                    int tmp = move_vector[1];
                    move_vector[1] = move_vector[0] * -1;
                    move_vector[0] = tmp;
                }
                spin_idx++;
            }
        }

        System.out.println(move_count);
    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }

}
