package 해싱_시간파싱;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class 서로_다른_빈도수_만들기 {
    public int solution(String s) {
        int answer = 0;
        String[] split = s.split("");
        // 입력받은 s의 각 문자열의 빈도수를 계산하기 위한 hashmap 초기화.
        HashMap<String, Integer> hashMap = new HashMap<>();

        // 각 문자열의 빈도수가 중복이 되는지 체크하기위한 set 초기화.
        Set<Integer> check = new HashSet<>();

        // 각 문자열의 빈도수 계산
        for (String str : split) {
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }
        // hashmap을 순회하면서 중복되는 빈도수가 있는지 체크, 만약 중복되는 빈두소가 있다면 빈도수가 중복되지 않을때까지 1씩 감소시킨다.
        for (Integer value : hashMap.values()) {
            // 만약 해당 빈도수가 이미 set에 저장되어 있다면 빈도수가 중복되는 것이므로 while 문을 실행.
            while (check.contains(value)) {
                value--; // 해당 빈도수를 1씩 감소.
                answer++; // 빈도수를 1 감소 시켰으니 answer 값을 1 증가시킨다.
            }

            // 만약에 빈도수를 감소시킨 값이 0일 경우에 set에 저장하지 않고 건너뛴다.
            // 만약 빈도수가 0인경우를 저장한다면 다음에 또 빈도수가 0일때 이미 set에는 0이 저장되어 있기 때문에 1을 감소시킨 -1 을 set에 저장하게된다.
            // 그리고 answer 값을 올바르지 못하게 1을 증가시키게 된다. set은 빈도수를 저장하는데 빈도수가 0이라는 것은 해당 문자열은 없다는 의미이므로 0, -1을 set에다가 저장해서는 안된다.
            if (value == 0) continue;
            // 빈도수가 0보다 큰 경우 set에 해당 빈도수를 저장한다.
            check.add(value);
        }
        return answer;
    }

    public static void main(String[] args) {
        서로_다른_빈도수_만들기 T = new 서로_다른_빈도수_만들기();
        System.out.println(T.solution("aaabbbcc"));
        System.out.println(T.solution("aaabbc"));
        System.out.println(T.solution("aebbbbc"));
        System.out.println(T.solution("aaabbbcccde"));
        System.out.println(T.solution("aaabbbcccdddeeeeeff"));
    }
}