
/*
 * 메모리 : 13436 KB
 * 시간 : 236 ms
 * 풀이 시간 : 50M
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_17836_공주님을구해라 {
static int N,M,T,gogram,goprincess,gramN,gramM;
static int map[][];
static int dn[] = {-1,0,1,0}, dm[] = {0,-1,0,1};
static boolean isvisited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isvisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		gogram =0;
		boolean findG = findGram();
		for (int i = 0; i < N; i++) {
			Arrays.fill(isvisited[i], false);
		}
		boolean findP = findPrincess();
		if(findG&&findP) {
			int minG = gogram+N-1-gramN+M-1-gramM;
			if(minG<goprincess) {
				if(minG<=T) System.out.println(minG);
				else System.out.println("Fail");
			}else {
				if(goprincess<=T) System.out.println(goprincess);
				else System.out.println("Fail");
			}
		}else {
			if(findG) {
				int minG = gogram+N-1-gramN+M-1-gramM;
				if(minG<=T) System.out.println(minG);
				else System.out.println("Fail");
			}
			else if(findP) {
				if(goprincess<=T) System.out.println(goprincess);
				else System.out.println("Fail");
			}else {
				System.out.println("Fail");
			}
		}
	}
	static class node{
		int n, m;
		public node(int n, int m) {
			this.n = n;
			this.m = m;
		}
	}
	static boolean findGram() {
		Queue<node> q = new LinkedList<node>();
		q.offer(new node(0,0));
		boolean state = false;
		A :while(!q.isEmpty()) {
			int size = q.size();
			while(size>0){
				node temp = q.poll();
				isvisited[temp.n][temp.m] =true;
				int tempN, tempM;
				for (int i = 0; i < 4; i++) {
					tempN = temp.n+dn[i];
					tempM = temp.m+dm[i];
					if(0<=tempN&&tempN<N&&0<=tempM&&tempM<M) {
						if(map[tempN][tempM]==2) { //그람을 찾았다면
							gramN = tempN;
							gramM = tempM;
							gogram++;
							state = true;
							break A;
						}
						if(!isvisited[tempN][tempM]&&map[tempN][tempM]==0) {
							isvisited[tempN][tempM] = true;
							q.offer(new node(tempN,tempM));
						}
					}
				}
				size--;
			}
			gogram++;
		}
		return state;
	}
	
	static boolean findPrincess() {
		Queue<node> q = new LinkedList<node>();
		q.offer(new node(0,0));
		boolean state = false;
		A :while(!q.isEmpty()) {
			int size = q.size();
			while(size>0){
				node temp = q.poll();
				isvisited[temp.n][temp.m] =true;
				int tempN, tempM;
				for (int i = 0; i < 4; i++) {
					tempN = temp.n+dn[i];
					tempM = temp.m+dm[i];
					if(0<=tempN&&tempN<N&&0<=tempM&&tempM<M) {
						if(tempN==N-1&&tempM==M-1) { //공주를 찾았다면
							goprincess++;
							state = true;
							break A;
						}
						if(!isvisited[tempN][tempM]&&!(map[tempN][tempM]==1)) {
							isvisited[tempN][tempM] = true;
							q.offer(new node(tempN,tempM));
						}
					}
				}
				size--;
			}
			goprincess++;
		}
		return state;
	}
}
