package 해싱_시간파싱;

import java.util.Arrays;
import java.util.HashMap;

public class 같은_빈도수_만들기 {
    public int[] solution(String s) {
        int[] answer = new int[5];
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] alpha = {"a", "b", "c", "d", "e"};
        String[] split = s.split("");
        // 입력받은 s 를 분리한 split의 각 값을 순회하면서 각 문자열의 빈도수를 계산한다.
        for (String str : split) {
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }
        int maxVal = Integer.MIN_VALUE;
        // alpha 각 값을 활용하여 빈도수 중에서 최대 빈도수의 값을 찾는다.
        for (String str : alpha) {
            maxVal = Math.max(maxVal, hashMap.getOrDefault(str, 0));
        }
        // 입력받은 문자열 s의 최대 빈도수 값에서 문자열 s의 각 문자열 빈도수 값을 빼서 입력받은 문자열 s를 기준으로
        // 각 문자열이 동일 빈도수가 되는 값을 계산하여 저장한다.
        for (int i = 0; i < 5; i++) {
            answer[i] = maxVal - hashMap.getOrDefault(alpha[i], 0);
        }
        return answer;

    }
    public static void main(String[] args) {
        같은_빈도수_만들기 T = new 같은_빈도수_만들기();
        System.out.println(Arrays.toString(T.solution("aaabc")));
        System.out.println(Arrays.toString(T.solution("aabb")));
        System.out.println(Arrays.toString(T.solution("abcde")));
        System.out.println(Arrays.toString(T.solution("abcdeabc")));
        System.out.println(Arrays.toString(T.solution("abbccddee")));
    }
}