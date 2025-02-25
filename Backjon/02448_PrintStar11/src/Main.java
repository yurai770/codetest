import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        board = new char[len][(len * 2) - 1];

        for (int x = 0; x < len; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = ' ';
            }
        }

        starWalkin(len, len, len);
        StringBuilder sb = new StringBuilder();
        for (char[] line : board) {
            for (char c : line) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void starWalkin(int len, int x, int y) {
        if (len == 3) {
            board[x - 3][y - 1] = '*';
            board[x - 2][y - 2] = '*';
            board[x - 2][y] = '*';
            for (int i = 0; i < 5; i++) {
                board[x - 1][y - 3 + i] = '*';
            }
            return;
        }

        starWalkin(len / 2, x - (len / 2), y);
        starWalkin(len / 2, x, y - (len / 2));
        starWalkin(len / 2, x, y + (len / 2));
    }

}
