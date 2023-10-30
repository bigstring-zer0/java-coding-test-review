package 시뮬레이션_구현;

import java.util.Arrays;

public class 사다리_타기 {
    public char[] solution(int n, int[][] ladder) {
        // 입력받은 n의 갯수만큼 답을 저장할 배열을 생성
        char[] answer = new char[n];
        // 입력받은 n의 갯수만큼 A, B, C, D, E... 순서대로 문자를 정답 배열에 저장
        for (int i = 0; i < n; i++) {
            answer[i] = (char) (i + 65);
        }
        // 사다리 타기의 시작 번호가 0이아닌 1부터인것을 생각하고 문제를 풀어야한다.
        // 이중 for문을 돌면서 입력받은 ladder의 값을 순회한다.
        // 입력받은 n과 n-1의 위치의 값을 입력받은 ladder 값이 끝날때까지 계속해서 위치를 바꿔준다.
        for (int[] line : ladder) {
            for (int val : line) {
                char tmp = answer[val-1];
                answer[val - 1] = answer[val];
                answer[val] = tmp;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        사다리_타기 T = new 사다리_타기();
        System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}
