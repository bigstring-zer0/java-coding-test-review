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
        Queue<Integer> waiting = new LinkedList<>();

        for (String s : enter) {
            int time = transferTime(s.split(" ")[0]);
            int laserNum = Integer.parseInt(s.split(" ")[1]);
            inList.add(new int[]{time, laserNum});
        }
        int endTime = inList.get(0)[0];
        waiting.offer(inList.get(0)[1]);
        int pos = 1;
        for (int time = endTime; time <= 1200; time++ ) {
            if (pos < n && time == inList.get(pos)[0]) {
                if (waiting.isEmpty() && endTime < inList.get(pos)[0]) {
                    endTime = inList.get(pos)[0];
                }
                waiting.offer(inList.get(pos)[1]);
                pos++;
            }
            if (!waiting.isEmpty() && time == endTime) {
                int idx = waiting.poll();
                endTime += laser[idx];
            }
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
