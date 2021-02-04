/*
 * memory : 51164 KB
 * time : 488 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 한 정점에서 거리가 가장 짧은 선을 우선 판단하여 연결되지 않은 정점일 경우 잇고 해당 정점에서 나오는 선을 추가
 * 
 * 
 * 
 * 
 */

package algo_2월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G4_1922_네트워크연결 {
	static int N, M;
	static boolean visit[];
	static ArrayList<Edge> edge[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visit = new boolean[N];
		edge = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			if (a == b) {
				continue;
			}
			Edge in1 = new Edge(b, c);
			Edge in2 = new Edge(a, c);
			edge[a].add(in1);
			edge[b].add(in2);
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int cnt = 0;
		visit[0] = true;
		for (Edge edge : edge[0]) {
			pq.offer(edge);
		}
		int res = 0;
		while (cnt != N && !pq.isEmpty()) {
			Edge tmp = pq.poll();
			if (!visit[tmp.b]) {
				visit[tmp.b] = true;
				cnt++;
				res += tmp.c;
				for (Edge edge : edge[tmp.b]) {
					pq.offer(edge);
				}
			}
		}
		System.out.println(res);
	}

	public static class Edge implements Comparable<Edge> {
		int b;
		int c;

		public Edge(int b, int c) {
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return c - o.c;
		}
	}
}
