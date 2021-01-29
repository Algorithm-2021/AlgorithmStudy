package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 	사용 메모리 : 165680kb
 *  코드 동작 시간 : 716ms
 *  풀이에 걸린 시간 : 4H	
 */
public class Main_B_G5_2493_탑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		StringTokenizer st = new StringTokenizer(in.readLine());

		int height = 0;
		
		for (int i = 1; i <= N; i++) {
			height = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				if(height < stack.peek()[1]) {
					break;
				} else {
					stack.pop();
				}
			}
			if(stack.isEmpty()) {
				sb.append(0+ " ");
			} else {
				sb.append(stack.peek()[0]+" ");
			}
			stack.push(new int[] {i,height});
		}
		
		System.out.println(sb);
	}

}