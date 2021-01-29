/*
 * 12856KB
 * 92ms
 * 1. 연산자로 순열만들기
 * 2. 최댓값 최솟값 계산
 * 35m
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S1_14888_연산자끼워넣기_정세린 {
	static int N;
	static int[] A, op;
	static int[] cal;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N]; 
		op = new int[4];
		cal = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i ++) {	// + - * /
			op[i] = Integer.parseInt(st.nextToken());
		}	// end of input
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	static void permutation(int index) {
		if (index == N-1) {
//			System.out.println(Arrays.toString(cal));
			calc();
			return;
		}
		
		for (int i = 0; i < 4; i++) {	// +, -, *, /
			if (op[i] == 0) continue;	// 해당 연산자를 다 씀
			cal[index] = i;	// 연산자 선택
			op[i]--;	// 선택되면 개수 하나 줄어듦
			permutation(index + 1);	
			op[i]++;	// 선택 이전으로 돌아감
		}
	}
	
	static void calc() {
		int res = A[0];
		for (int i = 0; i < N-1; i++) {
			switch (cal[i]) {
			case 0:	// +
				res = res + A[i+1];
				break;
			case 1: // -
				res = res - A[i+1];
				break;
			case 2: // *
				res = res * A[i+1];
				break;
			case 3: // /
				res = res / A[i+1];
				break;
			}
		}
		min = min < res ? min : res;
		max = max > res ? max : res;
	}

}
