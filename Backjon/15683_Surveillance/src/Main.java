import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = listConvert(bf.readLine().split(" "));
        int[][] board = new int[NM[0]][];
        for (int i = 0; i < NM[0]; i++) {
            board[i] = listConvert(bf.readLine().split(" "));
        }

        List<int[]> camera_list = new ArrayList<>();
        int[][] dxdy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int answer = 100;

        for (int x = 0; x < NM[0]; x++) {
            for (int y = 0; y < NM[1]; y++) {
                if (board[x][y] == 0 || board[x][y] == 6 || board[x][y] == 9) {
                    continue;
                }
                if (board[x][y] == 5) {
                    for (int[] move_vector : dxdy) {
                        int move_x = x;
                        int move_y = y;
                        while (true) {
                            move_x += move_vector[0];
                            move_y += move_vector[1];
                            if (!(0 <= move_x && move_x < board.length && 0 <= move_y && move_y < board[0].length)) {
                                break;
                            }
                            if (board[move_x][move_y] == 6) {
                                break;
                            }
                            if (board[move_x][move_y] == 0) {
                                board[move_x][move_y] = 9;
                            }
                        }
                    }
                    continue;
                }
                camera_list.add(new int[] { x, y });
            }
        }

        int clone_answer = 0;
        for (int x = 0; x < NM[0]; x++) {
            for (int y = 0; y < NM[1]; y++) {
                if (board[x][y] == 0) {
                    clone_answer += 1;
                }
            }
        }
        if (clone_answer < answer) {
            answer = clone_answer;
        }

        int[][] board_clone;
        int[] directions_bits = new int[camera_list.size()];
        while (camera_list.size() > 0) {
            board_clone = new int[board.length][board[0].length];
            for (int j = 0; j < board.length; j++) {
                System.arraycopy(board[j], 0, board_clone[j], 0, board_clone[j].length);
            }
            for (int camera_num = 0; camera_num < camera_list.size(); camera_num++) {
                int target_camera_x = camera_list.get(camera_num)[0];
                int target_camera_y = camera_list.get(camera_num)[1];

                if (board_clone[target_camera_x][target_camera_y] == 4) {
                    for (int i = 0; i < 4; i++) {
                        if (directions_bits[camera_num] == i) {
                            continue;
                        }
                        int[] move_vector = dxdy[i];
                        int move_x = target_camera_x;
                        int move_y = target_camera_y;
                        while (true) {
                            move_x += move_vector[0];
                            move_y += move_vector[1];
                            if (!(0 <= move_x && move_x < board_clone.length && 0 <= move_y
                                    && move_y < board_clone[0].length)) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 6) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 0) {
                                board_clone[move_x][move_y] = 9;
                            }
                        }
                    }
                }
                if (board_clone[target_camera_x][target_camera_y] == 3) {
                    int[] move_vector = dxdy[directions_bits[camera_num]];
                    int[][] selected_vector = { { move_vector[0], move_vector[1] },
                            { move_vector[1], move_vector[0] * -1 } };
                    for (int[] vector : selected_vector) {
                        int move_x = target_camera_x;
                        int move_y = target_camera_y;
                        while (true) {
                            move_x += vector[0];
                            move_y += vector[1];
                            if (!(0 <= move_x && move_x < board_clone.length && 0 <= move_y
                                    && move_y < board_clone[0].length)) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 6) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 0) {
                                board_clone[move_x][move_y] = 9;
                            }
                        }
                    }
                }
                if (board_clone[target_camera_x][target_camera_y] == 2) {
                    int[] move_vector = dxdy[directions_bits[camera_num]];
                    int[][] selected_vector = { { move_vector[0], move_vector[1] },
                            { move_vector[0] * -1, move_vector[1] * -1 } };
                    for (int[] vector : selected_vector) {
                        int move_x = target_camera_x;
                        int move_y = target_camera_y;
                        while (true) {
                            move_x += vector[0];
                            move_y += vector[1];
                            if (!(0 <= move_x && move_x < board_clone.length && 0 <= move_y
                                    && move_y < board_clone[0].length)) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 6) {
                                break;
                            }
                            if (board_clone[move_x][move_y] == 0) {
                                board_clone[move_x][move_y] = 9;
                            }
                        }
                    }
                }
                if (board_clone[target_camera_x][target_camera_y] == 1) {
                    int[] move_vector = dxdy[directions_bits[camera_num]];
                    int move_x = target_camera_x;
                    int move_y = target_camera_y;
                    while (true) {
                        move_x += move_vector[0];
                        move_y += move_vector[1];
                        if (!(0 <= move_x && move_x < board_clone.length && 0 <= move_y
                                && move_y < board_clone[0].length)) {
                            break;
                        }
                        if (board_clone[move_x][move_y] == 6) {
                            break;
                        }
                        if (board_clone[move_x][move_y] == 0) {
                            board_clone[move_x][move_y] = 9;
                        }
                    }
                }
            }

            clone_answer = 0;
            for (int x = 0; x < NM[0]; x++) {
                for (int y = 0; y < NM[1]; y++) {
                    if (board_clone[x][y] == 0) {
                        clone_answer += 1;
                    }
                }
            }
            if (clone_answer < answer) {
                answer = clone_answer;
            }

            int idx = directions_bits.length - 1;
            directions_bits[idx] += 1;
            while (true) {
                if (directions_bits[idx] < 4) {
                    break;
                }
                directions_bits[idx--] = 0;
                if (idx < 0) {
                    break;
                } else {
                    directions_bits[idx] += 1;
                }
            }
            if (idx < 0) {
                break;
            }
        }

        System.out.println(answer);

    }

    private static int[] listConvert(String[] split) {
        int[] intlist = new int[split.length];
        for (int i = 0; i < intlist.length; i++) {
            intlist[i] = Integer.parseInt(split[i]);
        }
        return intlist;
    }
}
