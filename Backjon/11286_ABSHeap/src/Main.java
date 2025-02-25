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
                if (abs(heap[parent_idx]) < abs(heap[idx])) {
                    break;
                }
                if ((abs(heap[parent_idx]) == abs(heap[idx])) & (heap[parent_idx] < heap[idx])) {
                    break;
                }
                swap(idx, parent_idx);
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
                // 좌측만 있다면 좌측과 비교 후 바꾸고 종료
                if (right_child_idx >= Max_size) {
                    break;
                }
                if (heap[right_child_idx] == 0) {
                    if (abs(heap[left_child_idx]) > abs(heap[idx])) {
                        break;
                    }
                    if ((abs(heap[left_child_idx]) == abs(heap[idx])) & (heap[left_child_idx] > heap[idx])) {
                        break;
                    }
                    swap(idx, left_child_idx);
                    idx = left_child_idx;
                    break;
                }

                // 자식 둘다 있으면 작은쪽이랑 비교 후 바꿈
                if (abs(heap[left_child_idx]) < abs(heap[right_child_idx])) {
                    target_idx = left_child_idx;
                } else if (abs(heap[left_child_idx]) > abs(heap[right_child_idx])) {
                    target_idx = right_child_idx;
                } else if (heap[left_child_idx] < heap[right_child_idx]) {
                    target_idx = left_child_idx;
                } else {
                    target_idx = right_child_idx;
                }

                if (abs(heap[target_idx]) > abs(heap[idx])) {
                    break;
                }
                if ((abs(heap[target_idx]) == abs(heap[idx])) & (heap[target_idx] > heap[idx])) {
                    break;
                }
                swap(idx, target_idx);
                idx = target_idx;

            }

            return return_num;
        }

        private void swap(int idx1, int idx2) {
            int tmp;
            tmp = heap[idx1];
            heap[idx1] = heap[idx2];
            heap[idx2] = tmp;
        }

        private int abs(int num) {
            if (num < 0) {
                num *= -1;
            }
            return num;
        }
    }
}
