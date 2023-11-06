package 자료구조_활용;

import java.util.HashSet;
import java.util.Set;

public class 최대_길이_연속수열 {
    public int  solution(int[] nums) {
        // 입력받은 nums의 각 요소들의 중복값을 제거하기 위한 Set 초기화.
        Set<Integer> integerSet = new HashSet<>();

        // 입력받은 nums를 Set에 저장.
        for (int num : nums) {
            integerSet.add(num);
        }

        int answer = Integer.MIN_VALUE;
        // 최대 길이 연속수열 찾기 시작
        // 만약에 각 요소들을 전부 탐색하며 최대 길이 연속수열을 찾는다면 최대 길이 연속수열의 시작값이 아닌 값들은 모든 요소들을 탐색할때마다 중복 탐색 될수도 있기 때문에 비효율적이다.
        // 그래서 해당 값보다 1 작은 숫자가 Set에 존재한다면 해당 숫자의 탐색은 건너뛰는 방식으로 문제를 해결할 것이다.
        for (Integer integer : integerSet) {
            // 만약 해당 숫자보다 1 작은 수가 Set에 존재 한다면 해당 숫자는 최대 길이 연속수열의 시작값이 되지 못하고 중복으로 카운트되는 값이다.
            if (integerSet.contains(integer - 1)) {
                // 탐색시간을 줄이기 위해 해당 값에서의 탐색은 건너뛴다.
                continue;
            }
            int cnt = 1;
            // 해당 값보다 1만큼 큰 숫자가 Set에 존재한다면 while을 실행한다.
            while (integerSet.contains(integer + 1)) {
                cnt++; // 연속수열 길이 1증가.
                integer++; // 해당 값 1증가
            }
            // 이전에 탐색한 연속수열의 길이 값과 비교하여 입력받은 nums 값에서 연속수열의 최대 길이 값을 계산한다.
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
