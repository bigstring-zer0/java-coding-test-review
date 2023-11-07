package 자료구조_활용;

import java.util.Stack;

public class 겹쳐진_압축_해제 {
    public String solution(String s) {
        String answer = "";
        Stack<String> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            if(c == ')'){
                String tmp = "";
                while(!stack.empty()){
                    String str = stack.pop();
                    if (str.equals("(")) {
                        String num = "";
                        while (!stack.empty() && Character.isDigit(stack.peek().charAt(0))) {
                            num = stack.pop() + num;
                        }
                        String res = "";
                        int cnt = 0;
                        if (num.equals("")) {
                            cnt = 1;
                        } else {
                            cnt = Integer.parseInt(num);
                        }
                        for (int i = 0; i < cnt; i++) {
                            res += tmp;
                        }
                        stack.push(res);
                        break;
                    } else {
                        tmp = str + tmp;

                    }
                }
            }
            else{
                stack.push(String.valueOf(c));
            }

        }
        for(String x : stack) answer += x;
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
