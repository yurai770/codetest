import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MainCopy {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        PriorityQueue<Player> pq = new PriorityQueue<>((o1, o2) -> o1.status - o2.status);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int classs = 0;

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(bf.readLine());
            while(st.hasMoreTokens()){
                pq.add(new Player(classs, Integer.parseInt(st.nextToken())));
            }
            classs++;
        }

        List<Player> li = new ArrayList<>();
        while(pq.size() > 0){
            li.add(pq.poll());
        }

        int head_idx = 0, tail_idx = 0;
        int[] counters = new int[N];

        int cnt = 0;
        int answer = Integer.MAX_VALUE;
        while(head_idx < N * M){
            if(counters[li.get(head_idx).classs] == 0){
                cnt++;
            }
            counters[li.get(head_idx).classs] += 1;
            
            if(cnt < N){
                head_idx++;
                continue;
            }

            while(counters[li.get(tail_idx).classs] > 1){
                counters[li.get(tail_idx).classs] -= 1;
                tail_idx++;
            }
            
            answer = Integer.min(answer, li.get(head_idx).status - li.get(tail_idx).status);
            head_idx++;
        }
        
        System.out.println(answer);
    }
}

class Player {
    int classs;
    int status;

    Player(int classs, int status){
        this.classs = classs;
        this.status = status;
    }
}