package 자료구조_활용;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 피부과 {
    public int transferTime(String s) {
        return Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
    }
    public int solution(int[] laser, String[] enter) {
        int n = enter.length;
        int answer = 0;
        List<int[]> inList = new ArrayList<>();
        // 피부과 대기실을 나타내기 위한 waiting을 초기화. 해당 큐에는 피부과에 방문하는 고객이 시술받을 레이저의 종류를 저장한다.
        Queue<Integer> waiting = new LinkedList<>();
        // 입력 enter를 문자열 값에서 정수형으로 변환하여 HH * 60 + MM로 변환한다.
        // 그리고 리스트에 저장한다.
        for (String s : enter) {
            int time = transferTime(s.split(" ")[0]);
            int laserNum = Integer.parseInt(s.split(" ")[1]);
            inList.add(new int[]{time, laserNum});
        }
        // 피부과 시술의 끝나는 시간을 계산하기 위한 endTime을 초기화.
        // 가장 처음 피부과에 방문하는 사람의 예약 시각을 초기값으로 초기화한다.
        int endTime = inList.get(0)[0];
        // 대기실에 가장 처음 피부과에 방문한 사람의 레이저 종류를 저장한다.
        waiting.offer(inList.get(0)[1]);
        // 가장 처음으로 방문한 0번 고객은 이미 큐에 저장했으므로 그 다음의 고객의 예약 정보를 탐색하기 위해 1로 초기화.
        int pos = 1;
        // 시뮬레이션 시작.
        // 시간의 흐름의 초기값을 가장 처음 피부과에 방문한 사람의 시각으로 초기화 하고 피부과가 오후 8시에 문을 닫기 때문에 time이 1200이 될때까지 for문을 실행한다.
        for (int time = endTime; time <= 1200; time++ ) {
            // 만약 아직 고객의 예약 정보를 전부 탐색하지 않았고 현재 시각이 해당 고객의 방문 시각과 같다면
            if (pos < n && time == inList.get(pos)[0]) {
                // 만약 대기실이 비어있고 이전에 고객이 받고있는 시술 완료 시각이 현재 탐색한 고객의 피부과 방문시각보다 작다면
                // (대기실이 비어있고 이전 고객의 시술이 완료 될때까지 피부과에 아무도 방문하지 않을 예정이라면)
                if (waiting.isEmpty() && endTime < inList.get(pos)[0]) {
                    // 이전에 시술을 받고있는 고객의 시술을 완료했다하고 피부과 시술 완료 시간을 현재 탐색한 고객의 방문시각으로 초기화 하여 불필요한 시뮬레이션 시간을 건너뛴다.
                    // 그러니까 가장 처음 고객이 피부과에 방문했을때와 같은 과정으로 초기화 한다.
                    // 그러면 방금 방문한 고객이 대기실에서 기다릴 필요없이 바로 피부 시술을 받는다.
                    endTime = inList.get(pos)[0];
                }
                // 현재 시각이 해당 고객의 방문시간과 같으면 해당 고객이 시술받을 레이저의 번호를 큐에 저장한다.
                waiting.offer(inList.get(pos)[1]);
                // 다음 고객의 예약정보를 탐색하기위해 pos값 1증가.
                pos++;
            }
            // 만약에 대기실에 고객이 대기하고 있고 현재 시각이 이전 고객의 피부과 시술 완료시간이라면 대기실에서 대기하고 있는 다음 고객의 피부 시술을 시작한다.
            if (!waiting.isEmpty() && time == endTime) {
                // 대기실에서 기다리고 있는 고객이 시술받을 레이저 번호값을 큐에서 가져온다.
                int idx = waiting.poll();
                // 각 레이저의 시술 시간이 저장되어있는 입력값 laser에서 이전에 가져온 레이저 번호값으로 인덱싱 하여 해당 레이저의 시술 완료 시각을 업데이트 한다.
                endTime += laser[idx];
            }
            // 전체 시간이 1분씩 지날때 마다 대기실에서 대기하는 고객 수의 최댓값을 계산한다.
            answer = Math.max(answer, waiting.size());
        }

        return answer;
    }
    public static void main(String[] args) {
        피부과 T = new 피부과();
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
        System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
    }
}
