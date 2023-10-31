package 시뮬레이션_구현;

public class 과일_가져가기 {
    // 해당 학생의 과일의 최소갯수를 계산하는 함수.
    public int getMinValue(int[] fruit) {
        int minVal = Integer.MAX_VALUE;
        for (int val : fruit) {
            minVal = Math.min(minVal, val);
        }
        return minVal;
    }

    // 과일을 서로 교환할때 한 학생의 과일의 최소갯수가 유일하지 않으면 다른 학생과 과일을 교환해도 이득이 없기 때문에
    // 해당 학생의 과일의 최소갯수가 유일한지 계산해야한다.
    // 해당 학생의 과일의 최소 갯수가 유일한지 계산하는 함수.
    public boolean isMinValueUnique(int[] fruit) {
        int minVal = getMinValue(fruit);
        int cnt = 0;
        for (int val : fruit) {
            if (val == minVal) {
                cnt++;
            }
        }
        return cnt == 1;
    }

    // 해당 학생의 최소 갯수 과일의 종류(인덱스)를 계산하는 함수.
    public int getMinValueIdx(int[] fruit) {
        int minVal = getMinValue(fruit);
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i] == minVal) {
                return i;
            }
        }
        return 0;
    }

    public int solution(int[][] fruit) {
        int answer = 0;
        int n = fruit.length;
        // 교환은 한번만 진행할 수 있으므로 교환 여부를 체크하기위한 배열 초기화.
        int[] check = new int[n];

        // 과일 가져가기 시뮬레이션 시작
        // 과일을 교환하여 이득을 볼수 있는 i 번째 학생 선정하기.
        for (int i = 0; i < n; i++) {
            // i번째 학생이 이미 과일을 교환했다면 건너뛰기.
            if (check[i] == 1) continue;
            // 해당 학생의 최소 과일의 갯수가 유일하지 않다면 건너뛰기.
            if (!isMinValueUnique(fruit[i])) continue;

            // i 번째 학생과 과일을 교환하여 이득을 볼수 있는 j번째 학생 선정하기.
            for (int j = i + 1; j < n; j++) {
                // j번째 학생이 이미 과일을 교환했다면 건너뛰기.
                if (check[j] == 1) continue;
                // 해당 학생의 최소 과일의 갯수가 유일하지 않다면 건너뛰기.
                if (!isMinValueUnique(fruit[j])) continue;
                // i 번째 학생과 j 번재 학생으 최소갯수 과일의 종류 계산하기.
                int aFruitIdx = getMinValueIdx(fruit[i]);
                int bFruitIdx = getMinValueIdx(fruit[j]);

                // 최소 갯수의 과일의 종류가 서로 다르고 과일을 교환할때 상대방에게 1개씩 줘야하는 과일의 갯수가 0보다 크다면
                if (aFruitIdx != bFruitIdx && fruit[i][bFruitIdx] > 0 && fruit[j][aFruitIdx] > 0) {

                    // 과일을 서로 교환 했을때 최소 갯수의 과일의 종류가 변함이 없거나 상대방에게 준 과일의 종류과 최소 갯수가 중복이더라고 교환을 했을때 이득이므로 서로 교환을 진행.
                    if (fruit[i][aFruitIdx] + 1 <= fruit[i][bFruitIdx] - 1 && fruit[j][bFruitIdx] + 1 <= fruit[j][aFruitIdx] - 1) {
                        fruit[i][aFruitIdx]++; // j 번째 학생에게 a 과일을 1개 받음.
                        fruit[i][bFruitIdx]--; // j 번째 학생에게 b 과일을 1개 줌.
                        fruit[j][bFruitIdx]++; // i 번째 학생에게 b 과일을 한개 받음.
                        fruit[j][aFruitIdx]--; // i 번째 학생에게 a 과일을 1개 줌.
                        check[i] = 1; // i 번째 학생은 교환을 진행했으니 체크
                        check[j] = 1; // j 번째 학생은 교환을 진행했으니 체크
                        break; // 교환을 진행했으니 2번째 for문 빠져나가기.
                    }
                }
            }
        }

        // 최소갯수 과일을 교환하는 시뮬레이션을 진행후 각 학생들이 가져가는 최소 과일의 갯수를 계산하기.
        for (int[] value : fruit) {
            answer += getMinValue(value);
        }

        return answer;
    }
    public static void main(String[] args) {
        과일_가져가기 T = new 과일_가져가기();
        System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
        System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
        System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
        System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
    }
}
