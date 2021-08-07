import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -케빈 베이컨의 6단계 법칙-
 * 
 * 메모리 : 14492KB
 * 시간 : 132ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/1389
public class Main_B_S1_1389_케빈베이컨의6단계법칙 {
	static int N, M;
	static final int MAX = 100000;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getDist();
		
		System.out.println(solve()+1);
	}
	
	static void getDist() {
		for(int k=0; k<N; ++k) {
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(i==j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
	
	static int solve() {
		int minPerson = 0;
		int min=Integer.MAX_VALUE, sum;
		
		for (int row = 0; row < N; ++row) {
			sum = 0;
			for (int col = 0; col < N; ++col) {
				sum += arr[row][col];
			}
			
			if(min > sum) {
				min = sum;
				minPerson = row;
			}
		}
		
		return minPerson;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		for(int i=0; i<N; ++i) {
			Arrays.fill(arr[i], MAX);
			arr[i][i]=0;
		}
		
		int a,b;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
	}
}
