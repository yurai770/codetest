import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());

        int[] board = new int[100001];
        for (int i = 0; i < board.length; i++) {
            board[i] = -1;
        }
        Deque<Integer> tp_queue = new ArrayDeque<>();
        Deque<Integer> walk_queue = new ArrayDeque<>();
        tp_queue.add(subin);
        walk_queue.add(subin);
        int step = 0;
        board[subin] = step;

        while (board[sister] == -1) {
            while (tp_queue.size() > 0) {
                int tp_position = tp_queue.pop();
                walk_queue.add(tp_position);
                while (board[sister] == -1) {
                    if (tp_position == 0) {
                        break;
                    }

                    if (tp_position > sister) {
                        break;
                    }

                    tp_position *= 2;
                    if (tp_position > 100000) {
                        break;
                    }

                    if (board[tp_position] == -1) {
                        board[tp_position] = step;
                        walk_queue.add(tp_position);
                    }
                }
            }

            // walk
            step++;
            while (walk_queue.size() > 0) {
                int walk_position = walk_queue.pop();
                if (walk_position > 0 && board[walk_position - 1] == -1) {
                    board[walk_position - 1] = step;
                    tp_queue.add(walk_position - 1);
                }

                if (walk_position < sister && walk_position < board.length - 1 && board[walk_position + 1] == -1) {
                    board[walk_position + 1] = step;
                    tp_queue.add(walk_position + 1);
                }
            }
        }
        System.out.println(board[sister]);
    }
}
