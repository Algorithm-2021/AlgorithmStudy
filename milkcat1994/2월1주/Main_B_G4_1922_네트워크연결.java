import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -네트워크 연결-
 * 1. union-find 이용한 트리 구성
 * 2. edge의 weight가 최소 인 것부터 확인 하여 MST구성
 * 
 * 메모리 : 47324KB
 * 시간 : 428ms
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/1922
public class Main_B_G4_1922_네트워크연결 {
	static int N, M;
	static PriorityQueue<Edge> pq;
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static int solve() {
		Edge te;
		int edge=0, res=0;
		while(edge<N-1) {
			te = pq.poll();
			if(!union(te.s, te.e)) continue;
			edge++;
			res+=te.w;
		}
		
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		int s,e,w;
		StringTokenizer st;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(s,e,w));
		}
		
		initParent(N);
	}
	
	static class Edge implements Comparable<Edge>{
		int s,e,w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	static void initParent(int N) {
		parents = new int[N+1];
		Arrays.fill(parents, -1);
	}
	
	static int getParents(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = getParents(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParents(a);
		int pb = getParents(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
}
