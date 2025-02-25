import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = listConvert(bf.readLine().split(" "));
        int N = tmp[0];
        int M = tmp[1];
        List<Coordinate> houses = new ArrayList<>();
        List<Coordinate> chickens = new ArrayList<>();

        for (int x = 0; x < N; x++) {
            tmp = listConvert(bf.readLine().split(" "));
            for (int y = 0; y < N; y++) {
                if (tmp[y] == 1) {
                    houses.add(new Coordinate(x, y));
                }
                if (tmp[y] == 2) {
                    chickens.add(new Coordinate(x, y));
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        Coordinate[] leaved_chichens = new Coordinate[M];
        for (int i = 1; i < Math.pow(2, chickens.size()); i++) {
            if (Integer.bitCount(i) != M) {
                continue;
            }

            int idx = 0;
            for (int j = 0; j < chickens.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    leaved_chichens[idx] = chickens.get(j);
                    idx++;
                }
            }

            int town_chicken_distance = 0;
            for (Coordinate house : houses) {
                int chicken_distance = Integer.MAX_VALUE;

                for (Coordinate chicken : leaved_chichens) {
                    int new_distance = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                    chicken_distance = Integer.min(new_distance, chicken_distance);
                }
                town_chicken_distance += chicken_distance;
            }
            answer = Integer.min(answer, town_chicken_distance);
        }

        System.out.println(answer);
    }

    private static int[] listConvert(String[] split) {
        int[] list = new int[split.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = Integer.parseInt(split[i]);
        }
        return list;
    }
}

class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}