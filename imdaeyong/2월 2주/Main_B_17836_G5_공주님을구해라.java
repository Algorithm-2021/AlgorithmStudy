import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 13752kb
 * 시간 136ms
 * 푸는시간 : 3H
 */
 
public class Main {
	static int N,M,T;
	static int[][] run_map;
	static int[][] sword_map;
	static int[] dy= {-1,0,1,0};
	static int[] dx= {0,-1,0,1};
	static int sword_result,run_result;
	static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		run_map = new int[N][M];
		sword_map = new int[N][M];
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(in.readLine());					
			for(int j=0; j<M;j++) {
				int check = Integer.parseInt(st.nextToken());
				run_map[i][j]=check*(-1);
				sword_map[i][j]=check*(-1);
			}
		}
		run();
		sword();		
				
		int result=run_result>sword_result? sword_result:run_result;
		if(result==10001) {
			System.out.println("Fail");
		}else {
			System.out.println(result);
			
		}
	}
	private static void run() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int n = 0; n < 4; n++) {
				int ny = dy[n] + node.y;
				int nx = dx[n] + node.x;
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)	continue;
				if (run_map[ny][nx] == 0) {
					q.add(new Node(ny, nx));
					run_map[ny][nx] = run_map[node.y][node.x]+1;
					if(run_map[ny][nx]>T || run_map[ny][nx]==0) {
						run_result=10001; 
						return;
					}
				
				}
			}
		}
		run_result = run_map[N-1][M-1];
		if(run_result==0) run_result=10001;
	}
	private static void sword() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int n = 0; n < 4; n++) {
				int ny = dy[n] + node.y;
				int nx = dx[n] + node.x;
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)	continue;
				if (sword_map[ny][nx] == 0) {
					q.add(new Node(ny, nx));
					sword_map[ny][nx] = sword_map[node.y][node.x]+1;
				}else if(sword_map[ny][nx]==-2) {
					sword_map[ny][nx] = sword_map[node.y][node.x]+1;
					sword_result=sword_map[ny][nx]+(N-ny)+(M-nx)-2;
					if(sword_result>T ||sword_result==0) {
						sword_result=10001; 
						return;
					}
					return;
				}
			}
			sword_result=10001;
		}
	}
}
