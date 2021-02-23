import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -디저트-
 * 
 * 메모리 : 90872KB
 * 시간 : 608ms
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
		int sum, max;
		for(int col=1; col<N; ++col) {
			for(int row=0; row<M; ++row) {
				max =0;
				for(int ex=0; ex<M; ++ex) {
					sum = memo[ex][col-1];
					if(row==ex)
						sum += Math.ceil(satisfaction[row][col]/2);
					else
						sum += satisfaction[row][col];
					max = Math.max(max, sum);
				}
				memo[row][col] = max;
			}
		}
	}
	
	static int getMax() {
		int max=0, end=N-1;
		for(int col=0; col<M; ++col)
			max = Math.max(max, memo[col][end]);
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
