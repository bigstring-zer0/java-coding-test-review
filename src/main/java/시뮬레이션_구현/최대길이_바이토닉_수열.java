package 시뮬레이션_구현;

import java.util.ArrayList;
import java.util.List;

public class 최대길이_바이토닉_수열 {
    public int solution(int[] nums) {
        int answer = Integer.MIN_VALUE;
        int n = nums.length;
        // 해당 위치에 인접해있는 바로 이전값, 이후값이 해당 기준의 위치값보다 작을경우
        // 해당 위치를 기점으로 바이토닉 수열이 될수 있으므로 리스트를 선언하고 해당 위치를 리스트에 저장.
        List<Integer> peekIdx = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peekIdx.add(i);
            }
        }
        // 최대길이 바이토닉 수열을 찾기위한 시뮬레이션 시작.
        for (Integer idx : peekIdx) {
            // idx에 저장되어있는 위치에 인접한 이전값, 이후값이 바이토닉 수열의 조건을 만족하는지 알기위한 인덱스를 저장히기위한 변수 초기화.
            int downIdx = idx;
            int upIdx = idx;
            // idx에 저장되어있는 값은 바이토닉수열의 원소라고 가정하고 시뮬레이션을 진행하기 때문에 1로 초기화.
            int cnt = 1;
            // 기준값의 왼쪽방향으로 바이토닉 수열의 조건을 만족하는 원소를 찾기.
            while (downIdx > 0 && nums[downIdx] > nums[downIdx - 1]) {
                // 해당 while문이 한번 실행되었다는 의미는 기준값의 왼쪽에
                // 바이토닉 수열의 조건을 만족하는 원소가 한개 존재한다는 의미므로 cnt 1증가, 해당 인덱스 1 감소.
                cnt++;
                downIdx--;
            }
            // 기준값의 오른쪽 방향으로 바이토닉 수열의 조건을 만족하는 원소를 찾기.
            while (upIdx < n - 1 && nums[upIdx] > nums[upIdx + 1]) {
                // 해당 while문이 한번 실행되었다는 의미는 기준값의 오른쪽에
                // 바이토닉 수열의 조건을 만족하는 원소가 한개 존재한다는 의미므로 cnt 1증가, 해당 인덱스 1 증가.
                cnt++;
                upIdx++;
            }
            // 해당 인덱스에대한 바이토닉 수열 길이값을 구하기 위한 시뮬레이션을 진행하고나서
            // 이전에 저장되어있던 길이값과 현재 구한 길이값과 비교하여 더 큰값을 저장.
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
    public static void main(String[] args) {
        최대길이_바이토닉_수열 T = new 최대길이_바이토닉_수열();
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}
