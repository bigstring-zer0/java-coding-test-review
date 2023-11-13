package 자료구조_활용;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class 가장_많이_사용된_회의실 {
    public int solution(int n, int[][] meetings) {
        int[] res = new int[n];
        int answer = 0;

        // 입력값 meetings를 회의 시작 시각이 빠른 순서로 정렬한다.
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // 문제에서 사용할 수 있는 회의가 여러개일 경우 회의실 번호가 작은 회의실 부터 배정한다고 했으므로 가장 작은 값이 루트에 위치하는 TreeSet을 초기화.
        TreeSet<Integer> rooms = new TreeSet<>();

        // 입력받은 n 만큼 TreeSet에 회의실 번호를 저장.
        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }

        // 문제에서 요구하는것 처럼 우선순위 큐를 이용하여 회의가 가장 빨리 끝나는 회의를 루트에 위치시키고 만약 회의가 가장 빨리 끝나는 회의가 여러개인 경우 회의실 번호가 빠른 순서대로 회의를 끝낸다.
        // a[0] == 회의가 끝나는 시각, a[1] 회의실 번호
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // 시뮬레이션 시작
        for (int[] meeting : meetings) {
            // 만약 진행중인 회의가 있고 가장 빨리 끝나는 회의의 시각이 현재 배정하려고 하는 회의의 시작시간보다 작거나 같다면
            // 가장 빨리 끝나는 회의는 우선순위 큐에서 꺼내면서 끝낸다. 그리고 가장 빨리 끝나는 회의가 사용한 회의실 번호를 rooms에 저장한다.

            while (!queue.isEmpty() && queue.peek()[0] <= meeting[0]) rooms.add(queue.poll()[1]);

            // 만약 회의를 배정할 수 있는 회의실이 존재한다면
            if (!rooms.isEmpty()) {
                // rooms에서 회의실 번호가 가장 빠른 순서대로 하나를 꺼낸다.
                Integer roomNum = rooms.pollFirst();
                // 해당 회의실에서 회의를 진행할 것이기 때문에 해당 회의실을 한번 사용했다고 기록한다.
                res[roomNum]++;
                // 현재 배정하려는 회의의 끝나는 시각과 해당 회의실 번호를 우선순위 큐에다가 저장.
                queue.add(new int[]{meeting[1], roomNum});

            } else { // 만약 회의를 진행할 수 있는 회의실이 없다면
                // 큐에서 가장 빨리 끝나는 회의를 꺼내어 현재 가장 빨리 끝나는 진행중인 회의를 끝낸다.
                int[] meet = queue.poll();
                // 현재 회의를 진행할 수 있는 회의실이 없기 때문에 현재 배정하려는 회의를
                // 위에서 꺼냈던 회의와 동일한 회의실에서 회의를 진행할 수 밖에 없다.
                // 그래서 가장 최근에 회의를 끝낸 meet에와 동일한 회의실을 사용할것이라고 기록한다.
                res[meet[1]]++;
                // 해당 케이스는 회의를 진행할 수 있는 회의실이 없는 경우기 때문에 가장 최근에 회의를 끝낸 시각에
                // 현재 회의를 진행하려는 회의의 끝나는 시각에서 시작하는 시각을 빼준 그러니까 회의 진행 시간을 더해준다.
                // 그리고 이전에 꺼내두었던 가장 최근에 회의를 완료한 회의와 같은 회의실을 사용할 것이기 때문에 동일한 회의실 번호도 우선순위 큐에 저장.
                queue.add(new int[]{meet[0] + (meeting[1] - meeting[0]), meet[1]});
            }
        }

        // 시뮬레이션을 완료하고 가장 많이 사용된 회의실을 찾는다.
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (res[i] > maxVal) {
                maxVal = res[i];
                answer = i;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        가장_많이_사용된_회의실 T = new 가장_많이_사용된_회의실();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
    }
}
