import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        int[] MNH = new int[3];

        int i = 0;
        while (st.hasMoreTokens()) {
            MNH[i++] = Integer.parseInt(st.nextToken());
        }
        Deque<int[]> queue = new ArrayDeque<>();
        int[][][] board = new int[MNH[2]][MNH[1]][MNH[0]];
        int checker = 0;
        for (int h = 0; h < MNH[2]; h++) {
            for (int n = 0; n < MNH[1]; n++) {
                st = new StringTokenizer(bf.readLine());
                i = 0;
                while (st.hasMoreTokens()) {
                    board[h][n][i] = Integer.parseInt(st.nextToken());
                    if (board[h][n][i] == 1) {
                        queue.add(new int[] { h, n, i });
                        checker++;
                    }
                    if (board[h][n][i] == -1) {
                        checker++;
                    }
                    i++;
                }
            }
        }

        if (checker == (MNH[0] * MNH[1] * MNH[2])) {
            System.out.println(0);
            return;
        }

        List<int[]> vectors = new ArrayList<>();
        int[] tmp;
        for (int idx = 0; idx < 3; idx++) {
            tmp = new int[3];
            tmp[idx] = 1;
            vectors.add(tmp);
            tmp = new int[3];
            tmp[idx] = -1;
            vectors.add(tmp);
        }

        int[] target = new int[3];
        int answer = 0;
        while (queue.size() > 0) {
            int[] popped = queue.pop();
            for (int[] vector : vectors) {
                for (int idx = 0; idx < 3; idx++) {
                    target[idx] = popped[idx] + vector[idx];
                }
                if (notInBoard(target, MNH)) {
                    continue;
                }

                if (board[target[0]][target[1]][target[2]] == 0) {
                    board[target[0]][target[1]][target[2]] = board[popped[0]][popped[1]][popped[2]] + 1;
                    answer = Integer.max(answer, board[target[0]][target[1]][target[2]]);
                    queue.add(target.clone());
                    checker++;
                }
            }
        }

        if (checker != (MNH[0] * MNH[1] * MNH[2])) {
            System.out.println(-1);
        } else {
            System.out.println(answer - 1);
        }
    }

    private static boolean notInBoard(int[] target, int[] mnh) {
        if (!(0 <= target[0] && target[0] < mnh[2] && 0 <= target[1] && target[1] < mnh[1] && 0 <= target[2]
                && target[2] < mnh[0])) {
            return true;
        }
        return false;
    }

}
