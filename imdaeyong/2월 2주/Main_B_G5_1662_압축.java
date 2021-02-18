package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 메모리 11548kb
 * 시간 76ms
 * 푸는시간 2H
 *
 */
public class Main_B_G5_1662_압축 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ')') {
                int q = 0;
                while (!stack.peek().equals("(")) { //괄호를 만날떄까지 개수 카운팅
                    String token = stack.pop();
                    if (token.contains("n")) //압축을 푼 단어(n표시)를 만날시 그 개수만큼 + 
                        q += Integer.parseInt(token.substring(1));
                    else
                        q++;
                }
                stack.pop(); // 괄호 하나  만나서 괄호 빼줌 

                int K = Integer.parseInt(stack.pop()); //괄호 바로 이전 숫자와 현재까지 단어갯수 곱해서 다시 넣기
                stack.push("n" + (K * q));
            }else {
            	stack.push(String.valueOf(ch)); //닫힌괄호 만날떄까지 스택에 계속 넣어줌.

            }
           
        }

        int total = 0;
        while (!stack.isEmpty()) {
            String token = stack.pop();
            if (token.contains("n"))
                total += Integer.parseInt(token.substring(1));
            else
                total++;
        }

        System.out.println(total);
    }
}