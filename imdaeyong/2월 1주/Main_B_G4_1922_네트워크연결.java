package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G4_1922_네트워크연결 {
	
	static int N;
	static int M;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		parent = new int[N+1];
		for(int i = 1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, cost));
		}
		
		int res = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			if(cnt == N-1) break;
			Edge e = pq.poll();
			if(find(e.from)!= find(e.to)) {
				res += e.cost;
				cnt++;
				union(e.from, e.to);
			}
		}
		
		System.out.println(res);
		
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[y] = x;
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
}

class Edge implements Comparable<Edge> {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.cost- o.cost;
	}
}
