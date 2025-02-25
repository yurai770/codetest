import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Space> board = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            board.add(new Space(i));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int ladder = Integer.parseInt(st.nextToken());
            board.get(idx).setLadder(ladder);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int snake = Integer.parseInt(st.nextToken());
            board.get(idx).setSnake(snake);
        }

        Deque<Integer> idx_queue = new ArrayDeque<>();
        idx_queue.add(1);
        board.get(1).setTime(0);
        Space endspace = board.get(100);

        while (endspace.getTime() == -1) {
            int start_pos = idx_queue.pop();
            for (int i = 1; i <= 6; i++) {
                int target_idx = start_pos + i;
                if (target_idx > 100) {
                    break;
                }

                Space target = board.get(target_idx);

                if (target.getTime() != -1) {
                    continue;
                }

                target.setTime(board.get(start_pos).getTime() + 1);

                if (target.getLadder() != -1) {
                    if (board.get(target.getLadder()).getTime() != -1) {
                        continue;
                    } else {
                        board.get(target.getLadder()).setTime(board.get(start_pos).getTime() + 1);
                        target_idx = target.getLadder();
                    }
                }
                if (target.getSnake() != -1) {
                    if (board.get(target.getSnake()).getTime() != -1) {
                        continue;
                    } else {
                        board.get(target.getSnake()).setTime(board.get(start_pos).getTime() + 1);
                        target_idx = target.getSnake();
                    }
                }

                idx_queue.add(target_idx);
            }
        }

        System.out.println(board.get(100).getTime());
    }
}

class Space {
    private int num;
    private int time;
    private int ladder;
    private int snake;

    Space(int num) {
        this.num = num;
        time = -1;
        ladder = -1;
        snake = -1;
    }

    public int getNum() {
        return num;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLadder() {
        return ladder;
    }

    public void setLadder(int ladder) {
        this.ladder = ladder;
    }

    public int getSnake() {
        return snake;
    }

    public void setSnake(int snake) {
        this.snake = snake;
    }

}