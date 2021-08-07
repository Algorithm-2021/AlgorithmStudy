import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -디저트-
 * 1. 이전일자의 합에서 현재 일자의 만족도를 더해준다. (Line 38, 40)
 * 2. 이때 이전 일자와 동일한 디저트일 경우 절반으로 계산한다. (Line 37 ~ 40)
 * 3. 해당 일자에 현재 디저트를 먹었을 시 최대값을 memo해둔다. (Line 42)
 * 
 * 
 * 메모리 : 90796KB
 * 시간 : 628ms
 * 풀이 시간 : 25M
 */

//출처 : https://www.acmicpc.net/problem/17953
public class Main_B_G5_17953_디저트 {
	static int N,M;
	
	static int[][] satisfaction;
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		System.out.print(getMax());
	}
	
	static void solve() {
		int max;
		for(int col=1; col<N; ++col) {
			for(int row=0; row<M; ++row) {
				max = 0;
				for(int exRow=0; exRow<M; ++exRow) {
					if(row == exRow)
						max = Math.max(max, memo[exRow][col-1] + satisfaction[row][col]/2);
					else
						max = Math.max(max, memo[exRow][col-1] + satisfaction[row][col]);
				}
				memo[row][col] = max;
			}
		}
	}
	
	static int getMax() {
		int max=0, end=N-1;
		for(int row=0; row<M; ++row)
			max = Math.max(max, memo[row][end]);
		return max;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		satisfaction = new int[M][N];
		memo = new int[M][N];
		for(int row=0; row<M; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
				for(int col=0; col<N; ++col) {
				satisfaction[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int row=0; row<M; ++row) {
			memo[row][0] = satisfaction[row][0];
		}
	}
}
