import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -호석이 두 마리 치킨-
 * 1. 플로이드-워셜 알고리즘 이용
 * 
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/21278
public class Main_B_G5_21278_호석이두마리치킨 {
	static final int INF = 100000;
	static int N,M;
	
	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for(int i=0; i<N; ++i)
			Arrays.fill(board[i], INF);
		
		int s,e;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken()) -1;
			e = Integer.parseInt(st.nextToken()) -1;
			
			board[s][e]=1;
			board[e][s]=1;
		}
		
		for(int j=0; j<N; ++j) {
			for(int start=0; start<N; ++start) {
				for(int end=0; end<N; ++end) {
					if(start==end) {
						board[start][end] = 0;
					}
					
					board[start][end] = Math.min(board[start][end], board[start][j]+board[j][end]);
				}
			}
		}
		
		int minRes=INF, tempMin;
		int start=0, end=0;
		for(int one=0; one<N; ++one) {
			for(int two=one+1; two<N; ++two) {
				tempMin = 0;
				for(int idx=0; idx<N; ++idx) {
					tempMin += Math.min(board[one][idx], board[two][idx]);
				}
				if(tempMin < minRes) {
					minRes = tempMin;
					start = one;
					end = two;
				}
			}
		}
		start+=1;
		end+=1;
		minRes*=2;
		System.out.println(start + " " + end + " " + minRes);
	}
}
