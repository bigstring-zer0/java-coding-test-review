package 시뮬레이션_구현;

import java.util.Arrays;

public class 회의실_만남 {
    public int[] solution(int[] enter, int[] exit) {
        int n = enter.length;
        int[] enterIdx = new int[n];
        int[] enterTime = new int[n];
        int[] exitTime = new int[n];
        for (int i = 0; i < n; i++) {
            enter[i]--;
            exit[i]--;
        }

        for (int i = 0; i < n; i++) {
            enterIdx[enter[i]] = i;
        }

        int cnt = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && j <= enterIdx[exit[i]]) {
                enterTime[enter[j]] = cnt++;
                j++;
            }
            exitTime[exit[i]] = cnt++;
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!(exitTime[i] < enterTime[j] || exitTime[j] < enterTime[i])) {
                    answer[i]++;
                    answer[j]++;
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        회의실_만남 T = new 회의실_만남();
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
        System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
