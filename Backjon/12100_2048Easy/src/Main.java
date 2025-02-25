import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int len = Integer.parseInt(st.nextToken());
        int[][] board = new int[len][len];
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < len; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = boardMax(0, board);
        System.out.println(answer);
    }

    private static int boardMax(int depth, int[][] board) {
        int max_value = 0;
        if (depth >= 5) {
            for (int[] line : board) {
                for (int num : line) {
                    max_value = Integer.max(max_value, num);
                }
            }
        } else {
            int[][] dxdy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            for (int[] moving_vector : dxdy) {
                int[][] copyboard = new int[board.length][board.length];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        copyboard[i][j] = board[i][j];
                    }
                }
                swift(moving_vector, copyboard);
                max_value = Integer.max(max_value, boardMax(depth + 1, copyboard));
            }
        }
        return max_value;
    }

    private static void swift(int[] given_vector, int[][] board) {
        int[] horizen_vector;
        int start_value;
        int[] moving_vector = { given_vector[0] * -1, given_vector[1] * -1 };
        if (given_vector[0] == 0) {
            horizen_vector = new int[] { 1, 0 };
        } else {
            horizen_vector = new int[] { 0, 1 };
        }
        if (given_vector[0] + given_vector[1] > 0) {
            start_value = board.length - 1;
        } else {
            start_value = 0;
        }

        for (int first = 0; first < board.length; first++) {
            int start_x_pos = (Math.abs(moving_vector[0]) * start_value) + (horizen_vector[0] * first);
            int start_y_pos = (Math.abs(moving_vector[1]) * start_value) + (horizen_vector[1] * first);
            int[] tmplist = new int[board.length];
            int tmpidx = start_value;
            int buffer = 0;
            int x_pos;
            int y_pos;

            for (int second = 0; second < board.length; second++) {
                x_pos = start_x_pos + (moving_vector[0] * second);
                y_pos = start_y_pos + (moving_vector[1] * second);
                if (board[x_pos][y_pos] == 0) {
                    continue;
                }
                if (buffer == 0) {
                    buffer = board[x_pos][y_pos];
                    continue;
                }

                if (buffer == board[x_pos][y_pos]) {
                    tmplist[tmpidx] = buffer * 2;
                    buffer = 0;
                } else {
                    tmplist[tmpidx] = buffer;
                    buffer = board[x_pos][y_pos];
                }
                tmpidx += moving_vector[0] + moving_vector[1];
            }

            if (buffer != 0) {
                tmplist[tmpidx] = buffer;
            }

            tmpidx = start_value;
            for (int second = 0; second < board.length; second++) {
                x_pos = start_x_pos + (moving_vector[0] * second);
                y_pos = start_y_pos + (moving_vector[1] * second);
                board[x_pos][y_pos] = tmplist[tmpidx];
                tmpidx += moving_vector[0] + moving_vector[1];
            }
        }
    }
}
