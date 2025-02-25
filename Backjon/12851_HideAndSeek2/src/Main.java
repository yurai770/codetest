import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int now_pos = Integer.parseInt(st.nextToken());
        int sister_pos = Integer.parseInt(st.nextToken());

        int[] min_times = new int[100001];
        for(int i = 0; i < min_times.length; i++){
            min_times[i]= Integer.MAX_VALUE;
        }

        int answer = 0;

        Deque<Chaser> queue = new ArrayDeque<>();
        queue.add(new Chaser(now_pos, 0));
        
        while(queue.size() > 0){
            Chaser chaser = queue.pop();
            // System.out.println(chaser);

            if(chaser.time > min_times[chaser.pos]){
                continue;
            }
            
            min_times[chaser.pos] = chaser.time;

            if (chaser.pos == sister_pos){
                answer++;
                continue;
                // System.out.println("answer+");
            }


            queue.add(new Chaser(chaser.pos - 1, chaser.time + 1));

            if(chaser.pos > sister_pos){
                continue;
            }

            queue.add(new Chaser(chaser.pos +1, chaser.time + 1));
            queue.add(new Chaser(chaser.pos * 2, chaser.time + 1));
        }
        
        System.out.println(min_times[sister_pos]);
        System.out.println(answer);
    }
}

class Chaser{
    int pos;
    int time;
    public Chaser(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }
    @Override
    public String toString() {
        return "Chaser [pos=" + pos + ", time=" + time + "]";
    }

}