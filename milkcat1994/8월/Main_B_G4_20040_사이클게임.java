import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -사이클 게임-
 * 1. 처음 생성 되는 사이클을 판단하는 문제로 union-find 이용하여 해결
 * 
 * 메모리 : 149824 KB
 * 시간 : 568ms
 * 풀이 시간 : 10m
 */

//출처 : https://www.acmicpc.net/problem/20040
public class Main_B_G4_20040_사이클게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[] parents;
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		int a,b;
		int answer=0, time=0;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			time++;
			
			if(!union(a,b)) {
				answer = time;
				break;
			}
		}
		System.out.println(answer);
	}
	
	static void init(int N) {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		return true;
	}
}
