package 자료구조_활용;

import java.util.Stack;

public class 겹쳐진_압축_해제 {
    public String solution(String s) {
        String answer = "";
        Stack<String> stack = new Stack<>();
        // 입력받은 s를 순차 탐색 하면서 ")"을 탐색하면 stack을 이용하여 값을 하나씩 꺼내면서 탐색한다.
        // 만약 "("를 탐색하게되면 "("의 앞의 값은 반복을 의미하는 숫자값이 위치한다는것을 알고 탐색한 숫자값 만큼
        // 이전에 stack에서 꺼내어 저장해두었던 값을 숫자값 만큼 반복하여 다시 stack에 저장한다.
        for(Character c : s.toCharArray()){
            // 입력받은 s를 순차탐색한 값이 만약 ")"라면 "("와 ")" 사이에 존재하는 값을 "(" 앞에 위치하는 숫자값만큼 반복해서 저장하기 위하여
            // stack에 저장해두었던 값을 하나씩 꺼내어 탐색한다.
            if(c == ')'){
                String tmp = "";
                // stack에 값이 존재한다면 while문을 실행
                while(!stack.empty()){
                    String str = stack.pop();
                    // stack에서 꺼낸 값이 "("라면 "("이전에 위치하는것은 숫자값이다.
                    if (str.equals("(")) {
                        String num = "";
                        // 만약 stack에 값이 조재하고 stack의 최상단 값이 숫자라면
                        while (!stack.empty() && Character.isDigit(stack.peek().charAt(0))) {
                            // stack의 최상단 값을 꺼낸후 이전에 저장되어있던 숫자값 num의 앞에다가 더한후 다시 num에 저장.
                            num = stack.pop() + num;
                        }
                        String res = "";
                        int cnt = 0;
                        // 숫자값을 계산하는 while문 실행을 완료했는데 값이 ""인 경우는 1이 생략된 경우다.
                        if (num.equals("")) {
                            cnt = 1;
                        } else { // 그게 아니라면 해당 값을 Integer로 변환
                            cnt = Integer.parseInt(num);
                        }
                        // 해당 숫자값은 "("와 ")" 사이에 존재하는 문자열의 반복 횟수를 의미하므로 해당 횟수만큼 문자열을 반복하여 저장한다.
                        for (int i = 0; i < cnt; i++) {
                            res += tmp;
                        }
                        // 해당 횟수만큼 반복한 문자열을 다시 스택의 최상단에 저장한다.
                        stack.push(res);
                        // "("와 "(" 사이에 존재하는 문자열을 숫자값 만큼 문자를 반복하였으면 while문을 빠져나온 후에 입력받은 s의 값을 이어서 순차 탐색 한다.
                        break;

                    } else { // 만약 "("값이 아니라면 stack에서 꺼낸 값은 "("와 ")" 사이에 압축된 문자열 값이라는 의미이므로
                        // 가장 최근에 꺼낸 값이 앞에 위치하도록 tmp에 저장.
                        tmp = str + tmp;

                    }
                }
            }
            else{ // 입력받은 s의 값에서 ")" 값을 탐색하지 못했다면 stack에 저장.
                stack.push(String.valueOf(c));
            }

        }
        // stack에 저장되어있는 값을 for-each문을 사용하여 값을 탐색하면 가장 최상단의 값부터 순차적으로 가져오는것이 아니라
        // 가장 최하단의 값부터 순차적으로 가져온다.
        for(String x : stack) {
            // for-each문을 사용하여 값을 가져오기 때문에 answer = x + answer 가 아닌 answer += x로 값을 answer에 저장한다.
            answer += x;
        }
        return answer;
    }
    public static void main(String[] args) {
        겹쳐진_압축_해제 T = new 겹쳐진_압축_해제();
        System.out.println(T.solution("3(a2(b))ef"));
        System.out.println(T.solution("2(ab)k3(bc)"));
        System.out.println(T.solution("2(ab3((cd)))"));
        System.out.println(T.solution("2(2(ab)3(2(ac)))"));
        System.out.println(T.solution("3(ab2(sg))"));
    }
}
