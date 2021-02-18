package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S2_DFS와BFS {
static int N, M, V;
static boolean map[][];
static boolean isvisit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][N+1];
		isvisit = new boolean[N+1];
		int to = 0;
		int from =0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			to = Integer.parseInt(st.nextToken());
			from = Integer.parseInt(st.nextToken());
			map[to][from]=true;
			map[from][to]=true; //true == 연결되있다.
		}
		DFS(V);
		System.out.println();
		Arrays.fill(isvisit, false);
		BFS();
		
	}
	static void DFS(int v) {
		System.out.print(v);
		isvisit[v] = true;//방문처리
		for (int i = 1; i < N+1; i++) {
			if(map[v][i]) {
				if(!isvisit[i]) {
					System.out.print(" ");
					isvisit[i] = true;
					DFS(i);
				}
			}
		}
	}
	static void BFS() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(V);
		isvisit[V] =true;
		System.out.print(V);
		int temp = 0;
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int i = 1; i < N+1; i++) {
				if(map[temp][i]) {
					if(!isvisit[i]) {
						q.offer(i);
						System.out.print(" "+i);//프린트
						isvisit[i]=true;//방문처리
					}
				}
			}
		}
		
	}
}
