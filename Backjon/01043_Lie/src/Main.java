import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] dont_lie = new boolean[N + 1];
        st = new StringTokenizer(bf.readLine());
        st.nextToken();
        while (st.hasMoreTokens()) {
            dont_lie[Integer.parseInt(st.nextToken())] = true;
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> tmp = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(tmp);
        }

        boolean keep_going_loop = true;
        boolean dont_lie_partypeople;
        boolean[] need_not_check = new boolean[M];
        while (keep_going_loop) {
            keep_going_loop = false;

            for (int party_num = 0; party_num < M; party_num++) {
                if (need_not_check[party_num]) {
                    continue;
                }
                dont_lie_partypeople = false;
                for (int partypeople : parties.get(party_num)) {
                    if (dont_lie[partypeople]) {
                        keep_going_loop = true;
                        dont_lie_partypeople = true;
                        break;
                    }
                }

                if (dont_lie_partypeople) {
                    for (int partypeople : parties.get(party_num)) {
                        dont_lie[partypeople] = true;
                    }
                    need_not_check[party_num] = true;
                }
            }
        }
        int answer = 0;
        for (boolean checker : need_not_check) {
            if (!checker) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
