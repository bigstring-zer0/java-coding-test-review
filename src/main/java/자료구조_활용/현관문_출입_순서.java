package 자료구조_활용;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 현관문_출입_순서 {
    public int[] solution(int[] arrival, int[] state) {
        int n = arrival.length;
        int[] answer = new int[n];
        // 이전사람이 현관문을 통해서 나갔으면 1, 현관문을 통해서 들어왔으면 0으로 초기화한다.
        // 초기값은 문제에서 현관문을 통해서 나가는것을 우선시 하기 때문에 1로 초기화.
        int prev = 1;
        // 현관문을 통해서 들어오려는 사람들이 대기하는것을 저장하기위한 enter 초기화.
        Queue<Integer> enter = new LinkedList<>();
        // 현관문을 통해서 나가려는 사람들이 대기하는것을 저장하기위한 exit 초기화.
        Queue<Integer> exit = new LinkedList<>();
        for (int i = 0, time = 0, cnt = 0; ; time++) {
            // 만약에 enter가 비어있고 exit가 비어있고 아직 모든 사원에 대한 순차 탐색이 완료되지 않았다면
            if (enter.isEmpty() && exit.isEmpty() && i < n) {
                // 그리고 만약 전체 흐름의 시간이 현재 사원이 현관문에 도착한 시간보다 작다면
                // 전체 흐름의 시간과 현재 사원이 현관문에 도착한 시간 사이의 시간은 시뮬레이션할 필요가 없다.
                if (time < arrival[i]) {
                    // 전체 흐름의 시간을 현재 사원이 현관문에 도착한 시각으로 변경한다.
                    time = arrival[i];
                    // enter가 비었고 exit도 비어있기 때문에 아무도 현관문을 이용하지 않았다.
                    // 문제에서 현관문을 이용하여 나가는것이 우선이라고 하였으므로 prev값을 1로 초기화한다.
                    prev = 1;
                }
            }
            // 순차 탐색하는 사원 번호가 전체 사원번호보다 작고 해당 사원이 도착한 시간 전체 흐름의 시간보다 작거나 같다면
            while (i < n && arrival[i] <= time) {
                // 만약 해당 사원의 state값이 0이면 현관문을 통해서 들어온다는 것을 의미한다.
                if (state[i] == 0) {
                    enter.offer(i);
                } else { // 그게아니라면 현관문을 통해서 나가는것을 의미한다.
                    exit.offer(i);
                }
                // 해당 사원에 대한 state값을 바탕으로 queue에 저장했으니 다음 사원을 가리킨다.
                i++;
            }
            // 전체 시간의 흐름에 따른 queue에 저장하는 작업을 완료한후에
            // 만약 현재의 prev 값이 1이라면 이전의 사람이 현관문을 통해서 나갔다는 의미다.
            if (prev == 1) {
                // 만약 현관문을 통해서 나가려는 queue가 비어있지 않다면
                if (!exit.isEmpty()) {
                    // 현재 exit에 가장 최근에 저장된 사원번호 값을 바탕으로 answer에 현관문을 이용한 현재 시각을 기록한다.
                    answer[exit.poll()] = time;

                } else { // 만약 exit가 비어있다면 위의 while문에서 enter에 값이 저장되었다는 의미이므로
                    // 현재 enter에 가장 최근에 저장된 사원번호 값을 바탕으로 answer에 현관문을 이용한 현재 시각을 기록
                    answer[enter.poll()] = time;
                    // 현재 prev 값이 1이지만 exit큐가 비어있고 enter큐에 값이 존재하여 enter에서 값을 꺼내었으므로
                    // 어떤 사원이 가장 최근에 현관문을 통해 들어왔다 라는 것을 기록한다.
                    prev = 0;
                }
            } else { // 만약 prev값이 1이 아니라 0이라면 이전의 사람이 현관문을 통해서 들어왔다는 의미다.
                // 그래서 먼저 enter에 값이 존재하는지 본다.
                // enter에 값이 존재한다면
                if (!enter.isEmpty()) {
                    // 현재 enter에 가장 최근에 저장된 사원번호 값을 바탕으로 answer에 현관문을 이용한 현재 시각을 기록
                    answer[enter.poll()] = time;
                } else { // 만약 enter가 비어있다면 위의 while문에서 exit에 값이 저장되었다는 의미이므로
                    // 현재 exit에 가장 최근에 저장된 사원번호 값을 바탕으로 answer에 현관문을 이용한 현재 시각을 기록한다.
                    answer[exit.poll()] = time;
                    // 현재 prev 값이 0이지만 enter가 비어있고 exit 값이 존재하여 exit에서 값을 꺼내었으므로
                    // 어떤 사원이 가장 최근에 현관문을 통해 나갔다 라는 것을 기록한다.
                    prev = 1;
                }
            }
            // answer에 값을 기록했다는 의미는 한명의 사원이 언제 현관문을 출입했다는 것을 기록했다는 의미다.
            cnt++;
            // 만약 전체 사원수에 대한 현관문 이용 시각을 기록했다면 해당 시뮬레이션을 종료한다.
            if (cnt == n) {
                break;
            }

        }
        return answer;
    }
    public static void main(String[] args) {
        현관문_출입_순서 T = new 현관문_출입_순서();
        System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
        System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
    }
}
