package 해싱_시간파싱;

import java.util.Arrays;
import java.util.HashMap;

public class 같은_빈도수_만들기 {
    public int[] solution(String s) {
        int[] answer = new int[5];
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] alpha = {"a", "b", "c", "d", "e"};
        String[] split = s.split("");

        for (String str : split) {
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }
        int maxVal = Integer.MIN_VALUE;
        for (String str : alpha) {
            hashMap.put(str, hashMap.getOrDefault(str, 0));
            maxVal = Math.max(maxVal, hashMap.get(str));
        }
        for (int i = 0; i < 5; i++) {
            answer[i] = maxVal - hashMap.get(alpha[i]);
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
