import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -숨바꼭질3-
 * 1. 2배로 뛰는 것 먼저 진행
 * 2. 그 뒤 앞 뒤로 이동
 * 3. K가 N보다 작거나 같다면 그 차이 값이 걸리는 시간이다.
 * 
 * 메모리 : 19168KB
 * 시간 : 172ms
 * 풀이 시간 : 50M
 */

//출처 : https://www.acmicpc.net/problem/13549
public class Main_B_G5_13549_숨바꼭질3 {
	static int N,K;
	static final int MAX = 100001;
	
	static boolean[] isVisited;
	static Queue<Integer> que;
	public static void main(String[] args) throws Exception {
		init();
		
		if(K<=N) {
			System.out.println(N-K);
			return;
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		que.offer(N);
		int pos, size, time=0;
		while(!que.isEmpty()) {
			
			size = que.size();
			while(--size>=0) {
				pos = que.poll();
				que.offer(pos);
				if(pos==0) continue;
				while(pos < MAX) {
					if(jump(pos))
						return time;
					pos*=2;
				}
			}
			
			size = que.size();
			while(--size>=0) {
				pos = que.poll();
				
				if(move(pos))
					return time+1;
			}
			time++;
		}
		return time;
	}
	
	static boolean move(int pos) {
		if(down(pos)) return true;
		
		if(up(pos)) return true;
		
		return false;
	}
	
	static boolean down(int pos) {
		pos-=1;
		if(pos>=0 && !isVisited[pos]) {
			if(pos == K) return true;
			que.offer(pos);
			isVisited[pos] = true;
		}
		return false;
	}
	
	static boolean up(int pos) {
		pos+=1;
		if(pos<=K && !isVisited[pos]) {
			if(pos == K) return true;
			que.offer(pos);
			isVisited[pos] = true;
		}
		return false;
	}
	
	static boolean jump(int pos) {
		pos*=2;
		if(pos < MAX && !isVisited[pos]) {
			if(pos == K) return true;
			que.offer(pos);
			isVisited[pos] = true;
		}
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[MAX];
		que = new LinkedList<>();
	}
}
