package s_20210129;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -연산자 끼워넣기-
 * 
 * 메모리 : 15192KB
 * 시간 : 140ms
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/14888
public class Main_B_S1_14888_연산자끼워넣기 {
	static int N, MAX,MIN;
	static int[] numbers, operations;
	
	public static void main(String[] args) throws Exception {
		init();
		
		permu(numbers[0], 1);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	static void permu(int curVal, int idx) {
		if(idx == N) {
			MAX = Math.max(MAX, curVal);
			MIN = Math.min(MIN, curVal);
			return;
		}
		
		for(int i=0; i<4; ++i) {
			if(operations[i] == 0) continue;
			
			operations[i]--;
			permu(cal(i, curVal, idx), idx+1);
			operations[i]++;
		}
	}
	
	static int cal(int oi, int cur, int ni) {
		switch (oi) {
		case 0:
			return cur+numbers[ni];
		case 1:
			return cur-numbers[ni];
		case 2:
			return cur*numbers[ni];
		case 3:
			return cur/numbers[ni];
		}
		
		return 0;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		operations = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; ++i)
			operations[i] = Integer.parseInt(st.nextToken());
		
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
	}
}
