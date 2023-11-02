package 해싱_시간파싱;

import java.util.HashMap;

public class 음수가_있는_부분수열 {
    public int solution(int[] nums, int m) {
        int answer = 0;
        // 입력받은 nums의 i번째 까지의 합인 sum, 빈도수인 cnt 순서로 저장할 hashmap 초기화.
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum = 0;

        // 입력받은 nums 의 처음부터 i번째 까지의 합이 0인 경우도 하나 있기 때문에 for 문으로 nums를 순회하기전에 먼저 저장해준다.
        hashMap.put(0, 1);
        for (int num : nums) {
            // i번째 부터 값을 sum에 누적한다.
            sum += num;
            // 만약에 sum값을 누적시키면서 sum-m의 값이 hashmap에 저장되어 있다면 nums의 i번째 까지 값을 누적시켜온 연속된 수열중에 m 값이 있다는 의미다.
            if (hashMap.containsKey(sum - m)) {
                // sum-m 값의 빈도수 만큼 sum 값을 누적시켜온 과정에서 m 값이 존재한다는 의미므로 answer에 sum - m 값의 빈도수를 누적시킨다.
                answer += hashMap.get(sum - m);
            }
            // nums의 i번째 까지 값을 누적시켜온 여러가지 sum 값의 빈도수를 저장한다.
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }

        return answer;

    }
    public static void main(String[] args) {
        음수가_있는_부분수열 T = new 음수가_있는_부분수열();
        System.out.println(T.solution(new int[]{2, 2, 3, -1, -1, -1, 3, 1, 1}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2, 2, -3}, 5));
        System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2}, 3));
        System.out.println(T.solution(new int[]{-1, 0, 1}, 0));
        System.out.println(T.solution(new int[]{-1, -1, -1, 1}, 0));
    }
}
