package 해싱_시간파싱;

import java.util.HashMap;

public class 한번_사용한_최초문자 {
    public int solution(String s) {
        String[] split = s.split("");
        // 입력받은 s 의 각 문자열의 빈도수를 계산하기 위하여 HashMap 초기화.
        HashMap<String, Integer> hashMap = new HashMap<>();
        // 입격받은 값을 순회하면서 각 문자열의 빈도수를 계산.
        for (String str : split) {
            // getOrDefault()를 활용하여 순회한 문자열이 이미 hashMap에 저장되어 있으면 해당 값을 반환하고 1을 더한값을 다시 hashMap에 저장한다.
            // 만약 해당 문자열을 hashMap에다 처음 저장하는 경우라면 0 을 반환하고 1을 더한 값을 hashMap에 저장한다.
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }
        // 입력받은 문자열 s를 분리한 split[i]을 활용하여 hashMap에서 해당 문자열의 빈도수가 1인 문자열의 인덱스를 찾는다.
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(split[i]) == 1) { // 만약 해당 문자열의 빈도수가 1이라면
                // 문제에서 인덱스는 0이 아닌 1부터 시작한다고 했으므로 해당 인덱스 i 에다가 1을 더한값을 리턴.
                return i + 1;
            }
        }
        // 만약 빈도수가 1인 문자열이 존재하지 않는다면 -1을 리턴.
        return -1;
    }
    public static void main(String[] args) {
        한번_사용한_최초문자 T = new 한번_사용한_최초문자();
        System.out.println(T.solution("statitsics"));
        System.out.println(T.solution("aabb"));
        System.out.println(T.solution("stringshowtime"));
        System.out.println(T.solution("abcdeabcdfg"));
    }
}