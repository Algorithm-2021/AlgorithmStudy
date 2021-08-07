import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -줄어들지 않아-
 * 1. 현재 숫자에 따라 좌측에 놓을 수 있는 숫자는 현재 숫자보다 같거나 작은 숫자들이다.
 * 2. 이전 길이의 숫자중 끝자리가 현재 숫자보다 같거나 작은 숫자의 경우의 수를 모두 합해주면 된다.
 * 
 * 메모리 : 14452KB
 * 시간 : 124ms
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/2688
public class Main_B_G5_2688_줄어들지않아 {
	static int T, N;
	static long[][] memo;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(--T>=0) {
			init();
			
			solve();
			
			sb.append(print()).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void solve() {
		long sum;
		for(int i=2; i<=N; ++i) {
			sum=0;
			for(int j=0; j<10; ++j) {
				sum+=memo[i-1][j];
				memo[i][j] = sum;
			}
		}
	}
	
	static long print() {
		long sum=0;
		for(int i=0; i<10; ++i)
			sum+=memo[N][i];
		return sum;
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		memo = new long[N+1][10];
		
		for(int i=0; i<10; ++i) {
			memo[1][i] = 1;
		}
	}
}
