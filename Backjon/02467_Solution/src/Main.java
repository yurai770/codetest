import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bf.readLine());

        List<Integer> solutions = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        while (st.hasMoreTokens()) {
            solutions.add(Integer.parseInt(st.nextToken()));
        }

        solutions.sort((o1, o2) -> Math.abs(o1) - Math.abs(o2));

        int min_gap = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        for (int idx = 0; idx < solutions.size() - 1; idx++) {
            if (Math.abs(solutions.get(idx) + solutions.get(idx + 1)) < min_gap) {
                min_gap = Math.abs(solutions.get(idx) + solutions.get(idx + 1));
                ans1 = solutions.get(idx);
                ans2 = solutions.get(idx + 1);
            }
        }

        System.out.println(String.format("%d %d", Integer.min(ans1, ans2), Integer.max(ans1, ans2)));

    }
}
