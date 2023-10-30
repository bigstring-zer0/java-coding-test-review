package 시뮬레이션_구현;

public class 잃어버린_강아지 {
    public int solution(int[][] board) {
        int n = board.length;
        // 상, 우, 하, 좌 순버대로 맵을 탐색하기위한 dx, dy
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        // 사람의 좌표를 저장하기 위한 변수 초기화.
        int ix = 0, iy = 0;
        // 강아지의 좌표를 저장하기 위한 변수 초기화.
        int jx = 0, jy = 0;
        // 사람이 바라보는 방향, 강아지가 바라보는 방향을 저장하기 위한 변수 초기화.
        int id = 0, jd = 0;

        // 이중 for문으로 입력받은 board를 순회하면서 사람의 시작좌표 강아지으 시작좌표를 저장.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 2) {
                    ix = i;
                    iy = j;
                }
                if (board[i][j] == 3) {
                    jx = i;
                    jy = j;
                }
            }
        }
        // 사람이 새롭게 탐색하는 좌표를 저장하기위한 변수 초기화.
        int inx = 0, iny = 0;
        // 강아지가 새롭게 탐색하는 좌표를 저장하기위한 변수 초기화.
        int jnx = 0, jny = 0;
        int answer = 0;
        // 시뮬레이션 시작
        while (answer < 10000) {
            // 시뮬레이션을 시작하자마자 1초 증가
            answer++;
            // 사람이 바라보는 방향으로 board를 탐색
            inx = ix + dx[id];
            iny = iy + dy[id];
            // 만약 사람이 이동하려는 위치가 입력받은 board의 범위를 벗어나거나 이동하려는 위치가 나무인 경우
            if (inx < 0 || inx >= 10 || iny < 0 || iny >= 10 || board[inx][iny] == 1) {
                // 시계방향으로 사람을 회전시킨다.
                id = (id + 1) % 4;
            } else { // 사람이 탐색한 위치가 board의 범위내이면서 이동하려는 위치가 나무가 아닌 경우
                // 사람을 해당 방향으로 한칸 이동한다.
                ix = inx;
                iy = iny;
            }
            // 강아지가 바라보는 방향으로 board를 탐색
            jnx = jx + dx[jd];
            jny = jy + dy[jd];
            // 만약 강아지가 이동하려는 위치가 입력받은 board의 범위를 벗어나거나 이동하려는 위치가 나무인 경우
            if (jnx < 0 || jnx >= 10 || jny < 0 || jny >= 10 || board[jnx][jny] == 1) {
                // 시계방향으로 강아지를 회전시킨다.
                jd = (jd + 1) % 4;
            } else { // 강아지가 이동하려는 위치가 board의 범위내이면서 이동하려는 위치가 나무가 아닌 경우
                // 강아지를 해당 방향으로 한칸 이동한다.
                jx = jnx;
                jy = jny;
            }
            // 사람과 강아지 둘다 한번씩 이동시키거나 회전시켰을때 사람과 강아지의 x좌표, y좌표가 같은경우 시뮬레이션 종료.
            if (ix == jx && iy == jy) {
                break;
            }
        }
        // 10000분이 지났는데도 사람과 강아지가 만나지 못했다면 0을 리턴.
        if (answer >= 10000) return 0;
        // 10000분 이내에 사람과 강아지가 서로 만났다면 만나기까지 걸린 시간을 리턴.
        return answer;
    }
    public static void main(String[] args) {
        잃어버린_강아지 T = new 잃어버린_강아지();
        int[][] arr1 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution(arr1));
        int[][] arr2 = {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution(arr2));
    }
}
