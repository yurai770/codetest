import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = bf.readLine().toCharArray();
        char[] str2 = bf.readLine().toCharArray();

        int[][] board = new int[str2.length + 1][str1.length + 1];
        int target = 0;

        for(int i = 0; i < str2.length; i++){
            for(int idx = 0; idx < str1.length; idx++){
                if(str2[i] == str1[idx]){
                    board[i+1][idx+1] = Integer.max(board[i][idx] + 1, board[i][idx+1]);
                }else{
                    board[i+1][idx+1] = Integer.max(board[i+1][idx], board[i][idx+1]);
                }
            }
            if (board[i][str1.length] != board[i+1][str1.length]){
                target = i+1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx1 = str1.length-1;
        int idx2 = str2.length-1;
        while(idx1 >= 0 && idx2 >= 0){
            if (str1[idx1] == str2[idx2]){
                sb.append(str1[idx1]);
                idx1--;
                idx2--;
                continue;
            }

            if(board[idx2+1][idx1+1] == board[idx2+1][idx1]){
                idx1--;
            }else{
                idx2--;
            }
        }

        System.out.println(board[str2.length][str1.length]);
        if(board[str2.length][str1.length] != 0){
            System.out.println(sb.reverse());
        }
        
    }
}