package 해싱_시간파싱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class 경고_메일 {
    // 입력받은 값의 시간을 정수형으로 변환하기 위한 함수.
    public int transferTime(String time) {
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }
    public String[] solution(String[] reports, int time) {
        // 각 사람들의 입장 시간을 기록하기 위한 enterHashMap 초기화.
        HashMap<String, Integer> enterHashMap = new HashMap<>();
        // 각 사람들 보안실 이용시간을 기록하기 위한 useTimeHashMap 초기화.
        HashMap<String, Integer> useTimeHashMap = new HashMap<>();
        for (String report : reports) {
            // 입력받은 reports를 name, min, status로 분리하여 초기화.
            String name = report.split(" ")[0];
            int min = transferTime(report.split(" ")[1]);
            String status = report.split(" ")[2];
            // 만약 status가 "in"이라면
            if (status.equals("in")) {
                // enterHashMap에 해당 사람의 이름과 보안실 출입 시간을 저장.
                enterHashMap.put(name, min);

                // 만약 status가 "out"이라면
            } else {
                // useTimeHashMap에 해당 사람이 보안실에서 나가는 시각에서 보안실에 입장한 시각을 빼준 결과를 저장한다.
                useTimeHashMap.put(name, useTimeHashMap.getOrDefault(name, 0) + (min - enterHashMap.get(name)));

                // useTimeHashMap에 보안실 이용 시간이 기록된 사람은 enterHashMap에서 데이터를 삭제한다.
                enterHashMap.remove(name);
            }
        }

        List<String> lst = new ArrayList<>();
        // 입력받은 time 보다 보안실 이용 시간을 초과한 사람 찾기.
        for (String name : useTimeHashMap.keySet()) {
            // 만약 해당 사람의 보안실 이용 시간이 입력받은 time 보다 크다면 보안실 이용 시간을 초과했다는 의미다.
            if (useTimeHashMap.get(name) > time) {
                // 해당 사람을 lst에 저장.
                lst.add(name);
            }
        }

        // lst에 저장된 사람의 이름을 사전순으로 빠른 순서대로 정렬하기.
        lst.sort(String::compareTo);

        String[] answer = new String[lst.size()];
        // lst에 저장되어있는 값을 answer에 저장하기.
        for (int i = 0; i < lst.size(); i++) {
            answer[i] = lst.get(i);
        }

        return answer;
    }
    public static void main(String[] args) {
        경고_메일 T = new 경고_메일();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 09:30 in", "daniel 10:05 in", "john 10:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 12:35 out", "daniel 15:05 out"}, 60)));
        System.out.println(Arrays.toString(T.solution(new String[]{"bill 09:30 in", "daniel 10:00 in", "bill 11:15 out", "luis 11:57 in", "john 12:03 in", "john 12:20 out", "luis 14:35 out", "daniel 14:55 out"}, 120)));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 09:14 in", "bill 09:25 in", "luis 09:40 in", "bill 10:30 out", "cody 10:35 out", "luis 10:35 out", "bill 11:15 in", "bill 11:22 out", "luis 15:30 in", "luis 15:33 out"}, 70)));
        System.out.println(Arrays.toString(T.solution(new String[]{"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"}, 60)));
    }
}
