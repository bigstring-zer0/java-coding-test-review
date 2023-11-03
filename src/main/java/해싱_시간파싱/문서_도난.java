package 해싱_시간파싱;

import java.util.*;
import java.util.stream.Collectors;

public class 문서_도난 {
    // 입력받은 값의 시간을 정수형으로 변환하기 위한 함수.
    public int transferTime(String time) {
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }
    public String[] solution(String[] reports, String time) {
        // 사람별 보안실에 들어간 시각을 저장하기 위한 hashmap 초기화.
        HashMap<String, Integer> hashMap = new HashMap<>();
        // 입력받은 time을 startTime과 endTime으로 나누어 정수형으로 변환.
        int startTime = transferTime(time.split(" ")[0]);
        int endTime = transferTime(time.split(" ")[1]);
        // hashMap에 사람별 보안실 출입 시각을 저장.
        for (String report : reports) {
            String name = report.split(" ")[0];
            int min = transferTime(report.split(" ")[1]);
            hashMap.put(name, min);
        }
        // hashMap으로 저장된 값들을 값을 기준으로 시간순으로 정렬하기.
        List<Map.Entry<String, Integer>> entries =
                hashMap.entrySet()
                        .stream().sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toList());

        List<String> lst = new ArrayList<>();
        // 각 사람별 보안실 출입 시각이 startTime과 endTime 사이라면 해당 사람의 이름을 list에 저장.
        for (Map.Entry<String, Integer> entry : entries) {
            if (startTime <= entry.getValue() && entry.getValue() <= endTime) {
                lst.add(entry.getKey());
            }
        }

        String[] answer = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            answer[i] = lst.get(i);
        }

        return answer;
    }
    public static void main(String[] args) {
        문서_도난 T = new 문서_도난();
        System.out.println(Arrays.toString(T.solution(new String[]{"john 15:23", "daniel 09:30", "tom 07:23", "park 09:59", "luis 08:57"}, "08:33 09:45")));
        System.out.println(Arrays.toString(T.solution(new String[]{"ami 12:56", "daniel 15:00", "bob 19:59", "luis 08:57", "bill 17:35", "tom 07:23", "john 15:23", "park 09:59"}, "15:01 19:59")));
        System.out.println(Arrays.toString(T.solution(new String[]{"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"}, "14:20 15:20")));
    }
}
