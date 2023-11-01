package 시뮬레이션_구현;

import java.util.Arrays;

public class 회의실_만남 {
    public int[] solution(int[] enter, int[] exit) {
        int n = enter.length;
        // 0 ~ N-1 까지의 사람들이 몇번째로 회의실에 들어왔는지를 저장하기 위한 배열 초기화.
        int[] enterIdx = new int[n];
        // 0 ~ N-1 까지의 사람들이 언제 회의실에 들어왔는지를 저장하기 위한 배열 초기화.
        int[] enterTime = new int[n];
        // 0 ~ N-1 까지의 사람들이 언제 회의실에서 나갔는지를 저장하기 위한 배열 초기화.
        int[] exitTime = new int[n];
        // 입장한 사람들의 번호를 1 ~ N이 아닌 0 ~ N-1 로 변경함.
        for (int i = 0; i < n; i++) {
            enter[i]--;
            exit[i]--;
        }

        for (int i = 0; i < n; i++) {
            // enter[i]의 값은 사람의 번호이므로 enterIdx[enter[i]] = i 의 값은 해당 번호의 사람이 i번 째로 회의실에 입장했다는 의미다.
            enterIdx[enter[i]] = i;
        }
        // 회의실 입장시간과 퇴장시간을 기록하기위한 변수 초기화.
        int time = 0;

        // 회의실에서 만난 사람의 수를 계산하기위한 시뮬레이션 시작
        // 사람들이 퇴장한 순서를 기준으로 회의실에 입장시간과 회의실에서 퇴장한 시간을 기록해준다.
        for (int i = 0, j = 0; i < n; i++) { // i는 회의실에서 퇴장한 순서를 계산하기위한 변수, j는 회의실에 입장한 사람을 계산하기 위한 변수
            // j가 회의실에 입장하는 전체 사람들의 범위를 벗어나지 않고 exit[i]번째 사람이 회의실에서 퇴장하기 전에 먼저 회의실에 입장한 사람이 있다면 exit[i]번째 사람을 포함하여
            // exit[i]번째 사람이 회의실에 입장할때까지 이전에 입장한 사람들을 enter[j] 값을 참고하여 입장 시간을 기록.
            // 만약 enterIdx[exit[i]]의 값이 1이라면, exit[i]번째로 퇴장한 사람이 회의실에 1번째로 입장햇다는 의미이므로, 자신을 포함해 자신의 이전에 회의실에 입장한
            // 0번째 사람은 먼저 회의실에 입장했다는 의미이므로 자신을 포함한 0번째, 1번째 사람을 회의실에 입장했다 가정하고 시간을 순서대로 기록한다.
            while (j < n && j <= enterIdx[exit[i]]) {
                enterTime[enter[j]] = time++; // exit[i[번째 사람을 포함한 exit[i]번째 사람 이전에 회의실에 입장한 사람의 회의실 입장 시간을 기록
                j++;
            }
            // 위의 while 문을 돌면서 i 번째로 회의실에서 퇴장한 사람의 퇴장시간을 기록해준다.
            exitTime[exit[i]] = time++;
        }
        int[] answer = new int[n];
        // 각 사람들이 회의실에서 몇명의 사람들과 마주쳣는지 계산.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 만약 i 번 사람이 회의실에서 퇴장한 시간이 j번 사람이 회의실에서 입장한 시간이 작은 경우, i번 사람과 j번 사람이 회의실에서 마주치지 않았다는 의미.
                // 그리고 j 번 사람이 회의실에서 퇴장한 시간이 i 번 사람이 회의실에 입장한 시간보다 작은 경우에도 j번 사람과 i번 사람은 회의실에서 마주치치 않았다는 의미다.
                // 이 두가지 조건을 묶어서 부정연산자를 이용한다면 아래의 조건문은 i번 사람과 2번 사람이 만나는 경우다.
                if (!(exitTime[i] < enterTime[j] || exitTime[j] < enterTime[i])) {
                    // i번 사람과 j번 사람이 만난다면 만난 사람의 횟수를 각각 1씩 증가시킨다.
                    answer[i]++;
                    answer[j]++;
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        회의실_만남 T = new 회의실_만남();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
