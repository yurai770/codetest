import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[][] board = new int[len][len];
        int[][] clear_moving_board = new int[len][len];
        int[] shark_position = null;
        for (int i = 0; i < len; i++) {
            String[] tmp = bf.readLine().split(" ");
            for (int j = 0; j < len; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
                if (board[i][j] == 9) {
                    shark_position = new int[] { i, j };
                }
            }
        }

        int[][] dir_vectors = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(shark_position);
        int body_size = 2;
        int eat = 0;
        int time = 0;

        int[][] time_board = new int[len][len];
        for (int i = 0; i < len; i++) {
            System.arraycopy(clear_moving_board[i], 0, time_board[i], 0, len);
        }

        time_board[shark_position[0]][shark_position[1]] = 1;
        int[] target_position;
        int before_depth = 0;
        List<int[]> foods = new ArrayList<>();

        while (queue.size() > 0 || foods.size() > 0) {
            if (queue.size() == 0 || time_board[queue.peek()[0]][queue.peek()[1]] == before_depth) {
                foods.sort((o1, o2) -> {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    } else {
                        return o1[0] - o2[0];
                    }
                });
                int[] food = foods.get(0);

                eat++;
                if (eat >= body_size) {
                    body_size++;
                    eat = 0;
                }
                time += time_board[food[0]][food[1]] - 1;

                board[shark_position[0]][shark_position[1]] = 0;
                board[food[0]][food[1]] = 9;
                queue.clear();
                shark_position = new int[] { food[0], food[1] };
                queue.add(shark_position);
                for (int i = 0; i < len; i++) {
                    System.arraycopy(clear_moving_board[i], 0, time_board[i], 0, len);
                }
                time_board[shark_position[0]][shark_position[1]] = 1;
                foods.clear();

                before_depth = 0;
                continue;
            }

            target_position = queue.pop();
            for (int[] vector : dir_vectors) {
                int move_x = target_position[0] + vector[0];
                int move_y = target_position[1] + vector[1];
                if (!(0 <= move_x && move_x < len && 0 <= move_y && move_y < len)) {
                    continue;
                }

                if (0 < board[move_x][move_y] && board[move_x][move_y] < body_size && board[move_x][move_y] != 9) {
                    foods.add(new int[] { move_x, move_y });
                    time_board[move_x][move_y] = time_board[target_position[0]][target_position[1]] + 1;
                    before_depth = time_board[move_x][move_y];
                    continue;
                }

                if (board[move_x][move_y] <= body_size && time_board[move_x][move_y] == 0) {
                    queue.add(new int[] { move_x, move_y });
                    time_board[move_x][move_y] = time_board[target_position[0]][target_position[1]] + 1;
                }
            }

        }

        System.out.println(time);
    }

}
