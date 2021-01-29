/*
 * 169840KB
 * 772ms
 * 2H
 * 스택
 * 1. 이전의 탑을 스택에 저장
 * 2. 현재탑과 이전의 탑을 비교
 * 3. 현재 탑보다 이전의 탑(stack.peek)이낮다면 이전의 탑은 수신 할 수가 없음 stack.pop으로 버림 
 * 4. 현재 탑보다 이전의 탑이 높다면 이전탑이 수신한것. 
 */

package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G5_2493_탑_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] tower = new int [N];
		Stack<Integer> stack = new Stack<Integer>();	// 탑의 정보(인덱스)를 담을 스택
		for (int i = 0; i < N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());	// 타워의 높이들을 저장
		}	// end of input
	
		StringBuilder sb = new StringBuilder();
		sb.append("0 ");	// 맨 처음 타워의 신호를 수신해줄 타워가 없음
		
		stack.add(0);
		for (int i = 1; i < N; i++) {
			int cur = tower[i];
			
			while (!stack.isEmpty() && tower[stack.peek()] < cur) {	// 스택 탑이 현재 탑보다 클때까지 pop (수신할수 있는 타워만 남김)
				stack.pop();	// 현재 탑이 더 높다면 이전의 작은 탑들은 어차피 수산하지 못함. pop해서 버림
			}
			
			if (!stack.isEmpty()) sb.append(stack.peek() + 1 + " ");	// 스택이 비어있지 않다면 수신 가능한 탑이 스택의 top에 있음
			else sb.append("0 ");	// 스택이 비어있다면 수신 가능한 탑이 하나도 없음
			
			stack.add(i);	// 현재 탑을 스택에 담음 (햔재 탑이 수신 가능할지 아닐지 모르기 떄문)
		}
		
		System.out.println(sb.toString());
	}
	
}
