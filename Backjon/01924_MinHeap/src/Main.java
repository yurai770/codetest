import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bf.readLine());
        int cmd;
        MyHeap myheap = new MyHeap();
        List<Integer> anslist = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            cmd = Integer.parseInt(bf.readLine());
            if (cmd == 0) {
                anslist.add(myheap.pop());
                continue;
            }
            myheap.add(cmd);
        }

        for (int ans : anslist) {
            System.out.println(ans);
        }

    }

    /// out of index!!!
    static class MyHeap {
        private static int Max_size = 100000;
        private int[] heap = new int[Max_size];
        private int max_idx = 0;

        public void add(int new_one) {
            int idx = ++max_idx;
            heap[idx] = new_one;

            int parent_idx;
            int tmp;
            while (true) {
                parent_idx = idx / 2;
                if (parent_idx == 0) {
                    break;
                }
                if (heap[parent_idx] < heap[idx]) {
                    break;
                }
                tmp = heap[parent_idx];
                heap[parent_idx] = heap[idx];
                heap[idx] = tmp;
                idx = parent_idx;
            }
        }

        public int pop() {
            if (max_idx == 0) {
                return 0;
            }
            int return_num = heap[1];
            heap[1] = heap[max_idx];
            heap[max_idx] = 0;
            max_idx--;

            int left_child_idx;
            int right_child_idx;
            int idx = 1;
            int tmp;
            int target_idx;
            while (true) {
                left_child_idx = idx * 2;
                right_child_idx = idx * 2 + 1;

                // 자식이 없으면 종료
                if (left_child_idx >= Max_size) {
                    break;
                }
                if (heap[left_child_idx] == 0) {
                    break;
                }
                // 좌측만 있다면 좌측과 바꾸고 종료
                if (right_child_idx >= Max_size) {
                    break;
                }
                if (heap[right_child_idx] == 0) {
                    if (heap[left_child_idx] > heap[idx]) {
                        break;
                    }
                    tmp = heap[left_child_idx];
                    heap[left_child_idx] = heap[idx];
                    heap[idx] = tmp;
                    idx = left_child_idx;
                    break;
                }
                if (heap[left_child_idx] < heap[right_child_idx]) {
                    target_idx = left_child_idx;
                } else {
                    target_idx = right_child_idx;
                }

                if (heap[target_idx] > heap[idx]) {
                    break;
                }
                tmp = heap[target_idx];
                heap[target_idx] = heap[idx];
                heap[idx] = tmp;
                idx = target_idx;

            }

            return return_num;
        }
    }
}
