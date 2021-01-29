/*
 * 349532KB
 * 1480ms
 * 2H 30m
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S2_18352_특정거리의도시찾기_정세린 {
	
	static final int INF = 1000000;
	static int[] dist;
	static int N, M, X, K;	// N개의 도시, M개의 도로, X는 출발지, 거리가 K인 도시들 구하기
	static ArrayList<LinkedList<Integer>> road; 
	static class DistInfo{
		int dist;
		int idx;
		public DistInfo(int dist, int idx) {
			this.dist = dist;
			this.idx = idx;
		}
	}
	static Queue<DistInfo> pq = new PriorityQueue<DistInfo>(new Comparator<DistInfo>() {
		@Override
		public int compare(DistInfo o1, DistInfo o2) {
			return o1.dist - o2.dist;
		}
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시의개수
		M = Integer.parseInt(st.nextToken());	// 도로의 수
		K = Integer.parseInt(st.nextToken());	// 거리의 정보
		X = Integer.parseInt(st.nextToken());	// 시작점
		dist = new int[N + 1];	// 출발지~i까지의 최단거리 저장
		road = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i <= N; i++) {
			road.add(new LinkedList<Integer>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			road.get(start).add(end);
		}	// end of input
		
		dijkstra(X);
		
		boolean exist = false;
		for (int i = 1; i <= N; i++) {
			if (dist[i] == K) {
				System.out.println(i);
				exist = true;
			}
		}
		
		if (!exist) System.out.println(-1);
		
	}
	
	static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		for (int i = 0; i < road.get(start).size(); i++) {
			int end = road.get(start).get(i);
			dist[end] = 1;
			pq.offer(new DistInfo(1, end));
		}
		
		for (int i = 1; i <= N - 2; i++) {	
			if (pq.isEmpty()) break;
			int cur = pq.poll().idx;
			
			for (int j = 0; j < road.get(cur).size(); j++) {
				int end = road.get(cur).get(j);
				if (dist[cur] + 1 < dist[end]) {
					dist[end] = dist[cur] + 1;
					pq.offer(new DistInfo(dist[end], end));
				}
			}
		}
	}
	
}
