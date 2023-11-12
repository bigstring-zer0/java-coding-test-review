package 자료구조_활용;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class CPU_스케쥴링 {
    public int[] solution(int[][] tasks) {
        int n = tasks.length;
        int[] answer = new int[n];
        // 입력값 task에서 각 스케쥴당 인덱스를 저장하기위한 programs 초기화.
        LinkedList<int[]> programs = new LinkedList<>();
        // 입력값 task에 인덱스를 추가한 정수형 배열 programs에 저장.
        for (int i = 0; i < n; i++) {
            programs.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        // 시작시간을 기준으로 정렬.
        programs.sort((a, b) -> a[0] - b[0]);

        // 문제에서 실햄시간이 작은 순서로 프로그램을 실행하는데 실행시간이 같다면 프로그램 번호가 작은 프로그램을
        // 먼저 실행한다고 했기때문에 우선순위를 문제의 요구사항에 맞게 설정하고 초기화.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // 각 프로그램을 실행하고나서 실행 완료 시간을 저장하기 위한 endTime 초기화.
        // answer에 값을 저장하기 위한 idx 초기화.
        int endTime = 0, idx = 0;

        // 시뮬레이션 시작.
        // 실행해야할 프로그램이 아직 남아있거나 우선순위 큐에 프로그램 실행을 위해 대기중인 경우에 while문을 실행
        while (!programs.isEmpty() || !queue.isEmpty()) {
            // 만약 실행 대기중인 프로그램이 없다면 실행해야할 프로그램이 있다는 의미다.
            if (queue.isEmpty()) {
                // 실행해야할 프로그램의 시간과 이전의 프로그램 실행이 끝나는 시각과 비교하여 더 큰 값을 endTime에 저장.
                // 실행 대기중인 프로그램이 없는데 실행 해야할 프로그램이 남아있는경우 기다릴 필요가 없기 때문에 endTime값을 변경한다.
                endTime = Math.max(endTime, programs.peek()[0]);
            }

            // 만약 실행해야할 프로그램이 남아있고 가장 먼저 실행해야할 프로그램의 실행 시작 시각이 이전 프로그램의 실행 완료 시각보다 작거나 같다면
            // 큐에 저장해서 실행되기까지 대기해야한다.
            while (!programs.isEmpty() && programs.peek()[0] <= endTime) {
                // 가장 먼저 실행해야할 프로그램을 꺼낸다.
                int[] tmp = programs.pollFirst();
                // 해당 프로그램의 실행 시간과 프로그램 번호를 큐에다 저장하고 실행되기를 기다린다.
                queue.add(new int[]{tmp[1], tmp[2]});
            }

            // endTime에 따라 프로그램을 큐에다가 저장한다음에 큐에서 프로그램을 하나씩 꺼내어 해당 프로그램을 실행.
            // 가장 먼저 실행될 프로그램을 하나 꺼낸다.
            int[] execute = queue.poll();
            // 해당 프로그램의 실행 시간을 이전 프로그램 실행 완료 시각에 더하여 endTime값을 초기화.
            endTime += execute[0];
            // 해당 프로그램을 실행 시켰기 때문에 해당 프로그램의 번호를 answer에 저장.
            answer[idx++] = execute[1];
        }

        return answer;
    }
    public static void main(String[] args) {
        CPU_스케쥴링 T = new CPU_스케쥴링();
        System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
        System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
    }
}
