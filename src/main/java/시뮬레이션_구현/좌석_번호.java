package 시뮬레이션_구현;

import java.util.Arrays;

public class 좌석_번호 {
    public int[] solution(int c, int r, int k) {
        // 주어진 보드를 시계방향으로 한번 회전시켜서 문제를 풀기위해 r과 c의 값을 서로 교환한 크기의 board를 생성.
        int[][] board = new int[c][r];
        // 사람들은 (1, 1) 부터 시계방향으로 앉기 때문에 방향을 1로 초기화.
        int d = 1;
        // 상, 우, 하, 좌 순버대로 맵을 탐색하기위한 dx, dy
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        // 만약 강연장의 크기보다 입력받은 k 의 값이 크다면 k번째 온사람은 앉을 좌석이 없는 경우기 때문에 문제에서 요구한것 처럼 (0, 0)을 반환
        if (r * c < k) return new int[]{0, 0};
        // 시작 좌표는 (0, 0)이므로 변수를 초기화.
        int x = 0, y = 0;
        // 사람이 이미 앉아있다면 1, 비어있는 자리라면 0을 의미한다.
        // 시작 좌표는 사람이 먼저 앉았다고 가정하고 초기화.
        board[x][y] = 1;
        // 시작 좌표의 사람은 먼저 앉았다고 가정했기 때문에 1로 초기화.
        int cnt = 1;
        // 해당 방향으로 한칸씩 탐색하기 위한 변수 초기화.
        int nx = 0, ny = 0;
        // 시뮬레이션 시작.
        while (cnt != k) {
            // 해당 방향으롷 한칸씩 탐색한다.
            nx = x + dx[d];
            ny = y + dy[d];
            // 만약 탐색한 위치가 board의 범위를 벗어나고 탐색한 위치가 이미 사람이 앉아있다면
            if (nx < 0 || nx >= c || ny < 0 || ny >= r || board[nx][ny] == 1) {
                // 방향을 시계방향으로 회전한다.
                d = (d + 1) % 4;
                // 시뮬레이션 이어서 처음부터 시작.
                continue;
            }
            // 회전할 필요가 없다면
            // 탐색한 해당 위치로 한칸 전진한다.
            x = nx;
            y = ny;
            // 한칸 전진한 위치에 사람을 앉혔다고 가정하고 1로 초기화.
            board[x][y] = 1;
            // 한명이 자리에 앉았으므로 cnt 값 1 증가.
            cnt++;
        }
        // 문제에서의 시작 위치는 (1, 1)이지만 (0, 0)으로 생각하고 문제를 풀었으므로
        // 시뮬레이션이 종료디면 마지막 앉은 사람의 x좌표, y좌표를 1씩 증가시키고 해당 좌표를 리턴.
        return new int[]{x+1, y+1};
    }
    public static void main(String[] args) {
        좌석_번호 T = new 좌석_번호();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}
