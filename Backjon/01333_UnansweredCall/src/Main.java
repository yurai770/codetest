import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int song_num = Integer.parseInt(st.nextToken());
        int song_len = Integer.parseInt(st.nextToken());
        int bells_ring = Integer.parseInt(st.nextToken());

        int term_min = song_len;
        int term_max = song_len + 5;
        int bell_timing = bells_ring;
        int song_counter = 0;

        while (song_counter < song_num) {
            if (term_min <= bell_timing && bell_timing < term_max) {
                break;
            }

            if (term_min > bell_timing) {
                bell_timing += bells_ring;
            }

            if (term_max <= bell_timing) {
                term_min = term_max + song_len;
                term_max = term_min + 5;
                song_counter++;
            }

        }

        System.out.println(bell_timing);
    }
}
