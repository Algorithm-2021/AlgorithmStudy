package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S1_1389_케빈베이컨의6단계법칙 {
// 11788	84
	static int N,M;
	static boolean map[][],visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int tempn = Integer.parseInt(st.nextToken());
			int tempm = Integer.parseInt(st.nextToken());
			map[tempn][tempm] = true;
			map[tempm][tempn] = true;
		}
		int min = Integer.MAX_VALUE;
		int minnum = 0;
		for (int i = 1; i < N+1; i++) {
			int temp = BFS(i);
			if(temp<min) {
				min = temp;
				minnum=i;
			}
		}
		System.out.println(minnum);
	}
	//전부 다 돌았을 때 가장 작은 애를 출력 하는게 목적
	
	static int BFS(int node) {
		Arrays.fill(visited, false);
	    int sum=0;
		Queue<Integer> q = new <Integer>LinkedList();
	    q.offer(node);
	    visited[node] = true;
	    int count =0;
	    while(!q.isEmpty()) {
	    	int size = q.size();
	    	while(size>0) {
	    		int temp = q.poll();
	    		sum =  sum+count;
	    		for (int i = 1; i < N+1; i++) {
					if(map[temp][i]&&!visited[i]) {
						q.offer(i);
						visited[i] = true;
					}
				}
	    		size--;
	    	}
	    	count++;
	    }
	    return sum;
	}
}
