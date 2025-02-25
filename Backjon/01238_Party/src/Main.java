import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        final int MAX_INT = 50000000;

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = MAX_INT;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            board[a][b] = c;
        }

        for (int m = 0; m < N; m++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    board[x][y] = Integer.min(board[x][y], board[x][m] + board[m][y]);
                }
            }
        }

        int answer = 0;
        for (int person = 0; person < N; person++) {
            if (person == X) {
                continue;
            }
            answer = Integer.max(answer, board[person][X] + board[X][person]);
        }
        System.out.println(answer);
    }
}
