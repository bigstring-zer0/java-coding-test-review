package 시뮬레이션_구현;

import java.util.Arrays;

public class 청소 {
    public int[] solution(int[][] board, int k) {
        int[] answer = new int[2];
        int n = board.length;
        // 상, 우, 하, 좌 순버대로 맵을 탐색하기위한 dx, dy
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 현재 로봇의 시작위치를 저장하기 위한 변수 (로봇의 시작위치는 0, 0)
        int x = 0, y = 0;
        // 새롭게 탐색한 좌표를 저장하기위한 nx, ny와 로봇의 현재 방향을 저장해주기 위한 d (처음 로봇이 바라보기 있는 방향은 오른쪽 이므로 1로 초기화)
        int nx = 0, ny = 0, d = 1;
        // 입력받은 k 시간만큼 시뮬레이션 진행
        for (int i = 0; i < k; i++) {
            // 현재 로봇이 바라보고 있는 방향으로 맵을 탐색한다.
            nx = x + dx[d];
            ny = y + dy[d];
            // 탐색한 좌표가 board의 범위내면서 탐색한곳이 장애물이 없다면
            if (0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == 0) {
                // 새롭게 탐색을 성공한 방향의 nx, ny 좌표를 로봇의 현재 좌표가 저장되어 있는 x, y에 초기화
                x = nx;
                y = ny;
                continue; // 로봇이 한칸 전진하면 1초가 소요되니 다음 반복문을 바로 진행.
            }
            // 만약 바라보고 있는 방향으로 로봇이 탐색을 했는데 장애물이 있거나 보드의 범위를 벗어나는 경우라면
            else{
                // 로봇의 방향을 시계방향으로 회전
                d = (d + 1) % 4;
                continue; // 로봇이 회전하는데도 1초가 소요되니 다음 반복문을 바로 진행.
            }
        }
        // 입력받은 k만큼 반복문이 진행되었다면 k초 동안 시뮬레이션을 진행한 것이므로 반복문이 끝나고 로봇의 현재 위치가 저장되어 있는 x, y 값을 answer[0], answer[1]에 저장.
        answer[0] = x;
        answer[1] = y;

        return answer;
    }
    public static void main(String[] args) {
        청소 T = new 청소();
        int[][] arr1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr1, 10)));
        int[][] arr2 = {{0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr2, 20)));
        int[][] arr3 = {{0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr3, 25)));
    }
}
