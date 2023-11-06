package 자료구조_활용;

import java.util.HashSet;
import java.util.Set;

public class 최대_길이_연속수열 {
    public int  solution(int[] nums) {
        Set<Integer> integerSet = new HashSet<>();

        for (int num : nums) {
            integerSet.add(num);
        }

        int answer = Integer.MIN_VALUE;
        for (Integer integer : integerSet) {
            if (integerSet.contains(integer - 1)) {
                continue;
            }
            int cnt = 1;
            while (integerSet.contains(integer + 1)) {
                cnt++;
                integer++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
    public static void main(String[] args) {
        최대_길이_연속수열 T = new 최대_길이_연속수열();
        System.out.println(T.solution(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
        System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
        System.out.println(T.solution(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
        System.out.println(T.solution(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
        System.out.println(T.solution(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
    }
}
