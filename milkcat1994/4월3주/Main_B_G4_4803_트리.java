import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -트리-
 * 1. union-find 이용
 * 2. 사이클이 생기는 경우 0번 idx에 연결
 * 
 * 메모리 : 58084KB
 * 시간 : 456ms
 * 풀이 시간 : 50M
 */

//출처 : https://www.acmicpc.net/problem/4803
public class Main_B_G4_4803_트리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC=0;
		while(++TC>0) {
			st = new StringTokenizer(br.readLine(), " ");
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0)
				break;
			
			int[][] arr = new int[m][2];
			for(int i=0; i<m; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int treeCnt = solve(n, arr);
			sb.append("Case "+TC+": ");
			if(treeCnt == 0) {
				sb.append("No trees.\n");
			}
			else if(treeCnt == 1) {
				sb.append("There is one tree.\n");
			}
			else {
				sb.append("A forest of "+treeCnt+" trees.\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	static int[] parents;
	public static int solve(int n, int[][] arr) {
		initParent(n);
		
		int a,b;
		for(int i=0; i<arr.length; ++i) {
			a = arr[i][0];
			b = arr[i][1];
			
			union(a,b);
		}
		
		int cnt=0;
		for(int i=1; i<=n; ++i) {
			if(parents[i] == -1) cnt++;
		}
		return cnt;
	}
	
	public static void initParent(int n) {
		parents = new int[n+1];
		Arrays.fill(parents, -1);
	}
	
	// 0에 연결된경우 0을 반환
	public static int getParent(int x) {
		if(parents[x] == -1 || x==0) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	// 0에 연결된 경우 해당 a,b의 부모값 0으로 교체후 실패 반환
	public static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa==pb || pa==0 || pb==0) {
			parents[pa] = 0;
			parents[pb] = 0;
			return false;
		}
		
		parents[pa] = pb;
		
		return true;
	}
}
