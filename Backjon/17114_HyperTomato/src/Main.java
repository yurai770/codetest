import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        final int DIMENSION = 11;

        // input mnopqrstuvw
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int O = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // input tomato
        Deque<int[]> queue = new ArrayDeque<>();
        int[][][][][][][][][][][] board = new int[W][V][U][T][S][R][Q][P][O][N][M];
        int[] board_len = { W, V, U, T, S, R, Q, P, O, N, M };
        int checker = 0;
        int space = M * N * O * P * Q * R * S * T * U * V * W;
        for (int w = 0; w < W; w++) {
            for (int v = 0; v < V; v++) {
                for (int u = 0; u < U; u++) {
                    for (int t = 0; t < T; t++) {
                        for (int s = 0; s < S; s++) {
                            for (int r = 0; r < R; r++) {
                                for (int q = 0; q < Q; q++) {
                                    for (int p = 0; p < P; p++) {
                                        for (int o = 0; o < O; o++) {
                                            for (int n = 0; n < N; n++) {
                                                st = new StringTokenizer(bf.readLine());
                                                for (int m = 0; m < M; m++) {
                                                    board[w][v][u][t][s][r][q][p][o][n][m] = Integer
                                                            .parseInt(st.nextToken());
                                                    if (board[w][v][u][t][s][r][q][p][o][n][m] == 1) {
                                                        queue.add(new int[] { w, v, u, t, s, r, q, p, o, n, m });
                                                        checker++;
                                                    }
                                                    if (board[w][v][u][t][s][r][q][p][o][n][m] == -1) {
                                                        checker++;
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (checker == space) {
            System.out.println(0);
            return;
        }

        List<int[]> vectors = new ArrayList<>();
        int[] tmp;
        for (int idx = 0; idx < DIMENSION; idx++) {
            tmp = new int[DIMENSION];
            tmp[idx] = 1;
            vectors.add(tmp);
            tmp = new int[DIMENSION];
            tmp[idx] = -1;
            vectors.add(tmp);
        }

        int[] tg = new int[DIMENSION];
        int answer = 0;
        while (queue.size() > 0) {
            int[] ppp = queue.pop();
            for (int[] vector : vectors) {
                for (int idx = 0; idx < DIMENSION; idx++) {
                    tg[idx] = ppp[idx] + vector[idx];
                }
                if (notInBoard(tg, board_len)) {
                    continue;
                }

                if (board[tg[0]][tg[1]][tg[2]][tg[3]][tg[4]][tg[5]][tg[6]][tg[7]][tg[8]][tg[9]][tg[10]] == 0) {
                    board[tg[0]][tg[1]][tg[2]][tg[3]][tg[4]][tg[5]][tg[6]][tg[7]][tg[8]][tg[9]][tg[10]] = board[ppp[0]][ppp[1]][ppp[2]][ppp[3]][ppp[4]][ppp[5]][ppp[6]][ppp[7]][ppp[8]][ppp[9]][ppp[10]]
                            + 1;
                    answer = Integer.max(answer,
                            board[tg[0]][tg[1]][tg[2]][tg[3]][tg[4]][tg[5]][tg[6]][tg[7]][tg[8]][tg[9]][tg[10]]);
                    queue.add(tg.clone());
                    checker++;
                }
            }
        }

        if (checker != space) {
            System.out.println(-1);
        } else {
            System.out.println(answer - 1);
        }
    }

    private static boolean notInBoard(int[] target, int[] lens) {
        for (int idx = 0; idx < target.length; idx++) {
            if (!(0 <= target[idx] && target[idx] < lens[idx])) {
                return true;
            }
        }
        return false;
    }

}
