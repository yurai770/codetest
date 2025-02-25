import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int[][] magnets = new int[4][8];
        for(int i = 0; i < 4; i++){
            String str = bf.readLine();
            for(int j = 0; j < 8; j++){
                magnets[i][j] = str.charAt(j) - '0';
            }
        }
        
            int K = Integer.parseInt(bf.readLine());
        StringTokenizer st;
            int[] arrows = new int[4];
            int next_magnet;
            for(int trial = 0; trial < K; trial++){
                st = new StringTokenizer(bf.readLine());
                int start_magnet = Integer.parseInt(st.nextToken()) -1;
                int spin_dir = Integer.parseInt(st.nextToken()) * -1;

                int[] spinner = new int[4];
                spinner[start_magnet] = spin_dir;
              
                //leftright
                for(int vector : new int[]{1, -1}){
                    next_magnet = start_magnet;
                    while(true){
                        next_magnet += vector;
                        if (next_magnet < 0 || next_magnet >= 4){
                            break;
                        }
                        if(magnets[next_magnet][inMagnets(arrows[next_magnet] - (2 * vector))] != 
                            magnets[next_magnet - vector][inMagnets(arrows[next_magnet- vector] + (2 * vector))]){
                            spinner[next_magnet] = spinner[next_magnet - vector] * -1;
                        }else{
                            break;
                        }
                    }
                }

                for(int i = 0; i < 4; i++){
                    arrows[i] = inMagnets(arrows[i] + spinner[i]);
                } 
            }
            
            for(int i = 0; i < 4; i++){
                answer += (int)Math.pow(2, i) * magnets[i][arrows[i]];
            }
        
        System.out.print(answer);
    }

    static int inMagnets(int num){
        if (num < 0){
            return num + 8;
        }

        return num % 8;
    }
}
