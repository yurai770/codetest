import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] sums = new int[N+1];
        st = new StringTokenizer(bf.readLine());
        sums[1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i <= N; i++){
            sums[i] = sums[i-1] + Integer.parseInt(st.nextToken());
        }

        if(sums[N] < S){
            System.out.println(0);
            return;
        }

        int head = 1;
        int tail = 0;
        int minus_sum;
        int answer = 10000000;
        while(head < N){
            minus_sum = sums[head] - sums[tail];
            if(minus_sum < S){
                head++;
                continue;
            }

            answer = Integer.min(head - tail, answer);
            tail++;
        }
        while(tail < N){
            minus_sum = sums[head] - sums[tail];
            if(minus_sum < S){
                break;
            }
            answer = Integer.min(head - tail, answer);
            tail++;
        }

        System.out.println(answer);
        
    }
}
