package DP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리 31168kb
 * 시간 400ms
 * 풀이시간 1.5H
 */
public class Main_B_S1_15558_점프게임 {
	
	static int N,K,arr1[],arr2[];
	static boolean[][] visited;
	static int dx[]= {1,-1,1}; //한칸 앞으로, 한칸 뒤로, 옆줄로!
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr1 = new int[N];
		arr2 = new int[N];
		visited = new boolean[2][N];
		
		String line = in.readLine();
		for(int i=0;i<N;i++) {
			arr1[i] = line.charAt(i)-'0';
		}
		line = in.readLine();
		for(int i=0;i<N;i++) {
			arr2[i] = line.charAt(i)-'0';
		}
			
		System.out.println(bfs(0,0));
		
	}
	
	static int bfs(int x, int y) {
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(x,y,0));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			
			Pair cur = q.poll();
			x = cur.x; // 왼쪽인지 오른쪽인지
			y = cur.y; // 위치
			int count = cur.cnt;
			
			if(count!=0) { // 맨 밑부터 한칸씩 없앰
				arr1[count-1] = 0;
				arr2[count-1] = 0;
			}
			
			//사라진 칸이면 패스
			if(x==0 && arr1[y]==0) continue;
			if(x==1 && arr2[y]==0) continue;
		
			for(int i=0;i<3;i++) {
				int nx = x; 
				int ny = 0;
				if(i==2) {
					if(x==0)	nx = 1;
					else 		nx = 0;
					ny = y + dx[i] * K;
				}
				else {
					ny = y + dx[i];
				}
				
				if(0<=ny && ny<N && nx==0 && arr1[ny]==1 && !visited[0][ny]) {
					visited[0][ny] = true;
					q.offer(new Pair(nx,ny,count+1));
				}else if(0<=ny && ny<N && nx==1 && arr2[ny]==1 && !visited[1][ny]) {
					visited[1][ny] = true;
					q.offer(new Pair(nx,ny,count+1));
				}
				else if(ny>=N) return 1;
			}
		}
		return 0;
	}
	static class Pair{
		int x;
		int y;
		int cnt;
		public Pair(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

}