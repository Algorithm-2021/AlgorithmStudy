import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -우주신과의 교감-
 * 1. 크루스칼 이용 (Line 30)
 * 2. 연결되지 않은 간선을 우선순위 큐에 넣을시 이미 같은 집합인 간선은 넣지 않아도 된다. (Line 71)
 * 3. M개로 입력되는 사전 연결된 정보의 경우 같은 집합인 경우도 섞여 있다. (Line 65)
 * 
 * 메모리 : 37992KB
 * 시간 : 256ms
 * 풀이 시간 : 2H
 */

//출처 : https://www.acmicpc.net/problem/1774
public class Main_B_G4_1774_우주신과의교감 {
	static int N, M, edgeNum;
	static int[] parents;
	
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.printf("%.2f", solve());
	}
	
	static double solve() {
		int target = N-1;
		double dist=0;
		
		Edge te;
		while(edgeNum < target) {
			te = pq.poll();
			
			if(!union(te.s, te.e)) continue;
			edgeNum++;
			dist += te.w;
		}
		
		return dist;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Point[] points = new Point[N+1];
		for(int i=1; i<=N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		initParents(N+1);
		
		int s,e;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			if(union(s, e)) edgeNum++;
		}
		
		pq = new PriorityQueue<Edge>();
		for(int i=1; i<N; ++i) {
			for(int j=i+1; j<=N; ++j) {
				if(getParent(i) == getParent(j)) continue;
				pq.offer(new Edge(i, j, getDist(points[i], points[j])));
			}
		}
	}
	
	static class Point {
		int r,c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int s,e;
		double w;
		
		Edge(int s, int e, double w){
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}

	static double getDist(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.r - p2.r, 2) + Math.pow(p1.c - p2.c, 2));
	}
	
	static void initParents(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
}
