package 시뮬레이션_구현;

import java.util.Arrays;

public class 비밀번호 {
    public int solution(int[] keypad, String password) {
        int answer = 0;
        int[][] pad = new int[3][3];
        // 입력받은 password 값의 순서대로 탐색하지 않고 각 값마다 다른 값들이 얼마나 인접해있는지를 구하여 문제를 해결하기 위한 2차원 배열 초기화.
        int[][] distance = new int[10][10];
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        String[] psw = password.split("");

        // 입력받은 keypad를 8방향으로 탐색하기 위하여 3*3의 2차원 배열로 변환
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pad[i][j] = keypad[i * 3 + j];
            }
        }
        // 새롭게 변환한 pad에서 각 값마다 8방향으로 인접한 값들만 탐색하여 1로 초기화 할것이므로 이전에 전부 2로 초기화.
        for (int i = 0; i < 10; i++) {
            Arrays.fill(distance[i], 2);
        }
        // (1 -> 1), (2 -> 2), (3 -> 3)... 과 같이 자신의 번호에서 자신의 번호로 이동하는 거리는 0이므로 해당하는 인덱스의 값들은 0으로 초기화.
        for (int i = 0; i < 10; i++) {
            distance[i][i] = 0;
        }

        // pad의 각 값마다 8방향으로 인접해있는 번호마다 distance 배열의 값을 1로 초기화.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 해당 값에 인접한 값들을 찾기위한 기준값 초기화.
                int from = pad[i][j];
                // 해당 기준값에서 8방향으로 탐색하기 위한 좌표 변수 초기화.
                int nx = 0, ny = 0;
                // 기준값과 인접해있는 값들을 찾기위해 8방향으로 탐색
                for (int k = 0; k < 8; k++) {
                    nx = i+dx[k];
                    ny = j+dy[k];
                    // 만약 pad의 범위를 벗어나면 건너뛰기.
                    if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;
                    // pad 범위내의 인접한 값이라면
                    // 기준값과 인접한 값 초기화.
                    int to = pad[nx][ny];
                    // 기준값에서 인접한 값의 거리는 인접해있으므로 1로 초기화.
                    distance[from][to] = 1;
                }
            }
        }

        // psw의 값을 순회하며 distance 값들을 바탕으로 비밀번호가 모두 입력되는데 걸리는 시간을 계산.
        for (int i = 0; i < psw.length - 1; i++) {
            // 기준값
            int from = Integer.parseInt(psw[i]);
            // 기준값 다음으로 입력해야하는 값
            int to = Integer.parseInt(psw[i + 1]);
            answer += distance[from][to];
        }

        return answer;
    }
    public static void main(String[] args) {
        비밀번호 T = new 비밀번호();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));
        System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
        System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
        System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
    }
}
