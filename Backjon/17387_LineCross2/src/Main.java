import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        Dot d1 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Dot d2 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(bf.readLine());
        Dot d3 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Dot d4 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int l1_d3 = CCW(d1, d2, d3);
        int l1_d4 = CCW(d1, d2, d4);
        int l2_d1 = CCW(d3, d4, d1);
        int l2_d2 = CCW(d3, d4, d2);

        int l1_l2 = l1_d3 * l1_d4;
        int l2_l1 = l2_d1 * l2_d2;

        if (l1_l2 > 0 || l2_l1 > 0) {
            System.out.println(0);
            return;
        }

        if (l1_l2 + l2_l1 != 0) {
            System.out.println(1);
            return;
        }

        if (Math.min(d1.x, d2.x) <= Math.max(d3.x, d4.x) && Math.min(d3.x, d4.x) <= Math.max(d1.x, d2.x)) {
            if (Math.min(d1.y, d2.y) <= Math.max(d3.y, d4.y) && Math.min(d3.y, d4.y) <= Math.max(d1.y, d2.y)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    static int CCW(Dot d1, Dot d2, Dot d3) {
        Dot vector1 = new Dot(d2.x - d1.x, d2.y - d1.y);
        Dot vector2 = new Dot(d3.x - d2.x, d3.y - d2.y);

        long calcul = Long.valueOf(vector1.x) * Long.valueOf(vector2.y)
                - Long.valueOf(vector1.y) * Long.valueOf(vector2.x);
        if (calcul > 0) {
            return 1;
        }
        if (calcul < 0) {
            return -1;
        }
        return 0;
    }
}

class Dot {
    final int x;
    final int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}