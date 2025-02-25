import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] board;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());
        int[] board = new int[len];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < len; i++){
            board[i] = Integer.parseInt(st.nextToken())-1;
        }

        int cmd = Integer.parseInt(bf.readLine());
        int[][] cmdline = new int[cmd][2]; 
        for(int i =0; i < cmd; i++){
            st = new StringTokenizer(bf.readLine());
            cmdline[i][0] = Integer.parseInt(st.nextToken()) -1;
            cmdline[i][1] = Integer.parseInt(st.nextToken()) -1;
        }

        dp = new int[len][len];
        for(int i = 0; i< len; i++){
            for(int j = i; j<len; j++){
                if(dp[i][j] != 0){
                    continue;
                }
                dp[i][j] = findPalindrome(i , j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] cm: cmdline){
            if(dp[cm[0]][cm[1]] == 1){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int findPalindrome(int i, int j) {
        if (dp[i][j] != 0){
            return dp[i][j];
        }
        
        if (j - i == 0){
            return 1;
        }

        if (board[i] != board[j]){
            return -1;
        }
        if(j - 1 == 1){
            return 1;
        }

        return findPalindrome(i+1, j-1);
    }
}
