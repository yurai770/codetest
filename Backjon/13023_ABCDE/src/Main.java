import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int node_1 = Integer.parseInt(st.nextToken());
            int node_2 = Integer.parseInt(st.nextToken());
            edges[node_1].add(node_2);
            edges[node_2].add(node_1);
        }

        boolean findABCDE = false;
        for (int start_idx = 0; start_idx < N; start_idx++) {
            visited[start_idx] = true;
            if (findFriend(start_idx, 0)) {
                findABCDE = true;
                break;
            }
            visited[start_idx] = false;
        }

        if (findABCDE) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    private static boolean findFriend(int now_person_idx, int depth) {
        if (depth == 4) {
            return true;
        }

        boolean have_friend = false;
        for (int target_idx : edges[now_person_idx]) {
            if (visited[target_idx]) {
                continue;
            }

            visited[target_idx] = true;
            have_friend = findFriend(target_idx, depth + 1);
            if (have_friend) {
                break;
            }
            visited[target_idx] = false;
        }

        return have_friend;
    }
}
