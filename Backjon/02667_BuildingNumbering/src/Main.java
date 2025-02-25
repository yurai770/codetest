import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        String[][] board = new String[len][];
        for (int i = 0; i < len; i++) {
            board[i] = bf.readLine().split("");
        }

        int[][] dxdyes = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        List<Integer> answers = new ArrayList<>();
        int buildings = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int x = 0; x < len; x++) {
            for (int y = 0; y < len; y++) {
                if (board[x][y].equals("0")) {
                    continue;
                }

                buildings = 0;
                queue.add(new int[] { x, y });
                while (queue.size() > 0) {
                    int[] target = queue.pop();
                    if (board[target[0]][target[1]].equals("0")) {
                        continue;
                    }

                    board[target[0]][target[1]] = "0";
                    buildings += 1;
                    for (int[] dxdy : dxdyes) {
                        int move_x = target[0] + dxdy[0];
                        int move_y = target[1] + dxdy[1];

                        if (0 <= move_x && move_x < len && 0 <= move_y && move_y < len) {
                            queue.add(new int[] { move_x, move_y });
                        }
                    }
                }
                answers.add(buildings);
            }
        }

        answers.sort((o1, o2) -> o1 - o2);
        StringBuilder sb = new StringBuilder();
        sb.append(answers.size() + "\n");
        for (int ans : answers) {
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

}
