import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -팀원 모집-
 * 
 * 메모리 : 14664KB
 * 시간 : 132ms
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/11578
public class Main_B_G5_11578_팀원모집 {
	static int[] canSolve;
	static int minRes = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N, M;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		canSolve = new int[M];
		
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			canSolve[i] |= 1;
			for(int j=0; j<cnt; ++j) {
				canSolve[i] |= (1<<Integer.parseInt(st.nextToken()));
			}
		}
		
		dfs(0,0,M,N+1,0);
		
		System.out.println(minRes == Integer.MAX_VALUE ? -1 : minRes);
	}
	
	static void dfs(int idx, int cnt, int M, int N, int solve) {
		if(idx==M) {
			if(solve == (1<<N)-1) {
				minRes = Math.min(minRes, cnt);
			}
			return;
		}
		
		dfs(idx+1, cnt+1, M, N, solve | canSolve[idx]);
		dfs(idx+1, cnt, M, N, solve);
	}
}
