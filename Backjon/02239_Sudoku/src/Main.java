import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean isClear = false;
    static List<Cell> questions;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        questions = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String a = bf.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = a.charAt(j) - 48;
                if (board[i][j] == 0) {
                    questions.add(new Cell(i, j));
                }
            }
        }

        bfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int idx) {
        if (questions.size() <= idx) {
            isClear = true;
            return;
        }

        Cell target = questions.get(idx);

        // 9개의 숫자 넣기
        for (int num = 1; num <= 9; num++) {
            boolean isFail = false;
            board[target.x][target.y] = num;

            // 가로 체크
            int horizen = 0;
            for (int i = 0; i < 9; i++) {
                if (board[target.x][i] == 0) {
                    continue;
                }
                if ((horizen & (1 << board[target.x][i])) > 0) {
                    isFail = true;
                    break;
                }
                horizen = horizen | (1 << board[target.x][i]);
            }

            if (isFail) {
                continue;
            }

            // 세로 체크
            int vertical = 0;
            for (int i = 0; i < 9; i++) {
                if (board[i][target.y] == 0) {
                    continue;
                }
                if ((vertical & (1 << board[i][target.y])) > 0) {
                    isFail = true;
                    break;
                }
                vertical = vertical | (1 << board[i][target.y]);
            }

            if (isFail) {
                continue;
            }

            // 네모 체크
            int square = 0;
            for (int x : target.xline) {
                for (int y : target.yline) {
                    if (board[x][y] == 0) {
                        continue;
                    }
                    if ((square & (1 << board[x][y])) > 0) {
                        isFail = true;
                        break;
                    }
                    square = square | (1 << board[x][y]);
                }
                if (isFail) {
                    break;
                }
            }

            if (isFail) {
                continue;
            }

            bfs(idx + 1);

            if (isClear) {
                return;
            }

        }
        board[target.x][target.y] = 0;

    }
}

class Cell {
    int x;
    int y;

    int[] xline = new int[3];
    int[] yline = new int[3];

    Cell(int x, int y) {
        this.x = x;
        this.y = y;

        int xt = (x / 3) * 3;
        int yt = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            xline[i] = xt + i;
            yline[i] = yt + i;
        }
    }
}