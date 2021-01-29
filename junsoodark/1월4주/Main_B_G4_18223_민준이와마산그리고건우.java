/*
 * memory : 28832 KB
 * time : 776 ms
 * 
 * solve time : 2 Hour
 * 
 * 풀이
 * dfs로 경로 탐색을 하며 정점간의 거리가 갱신될때마다 우선순위큐에 넣어서 반복하여 거리를 계산하며 시작부터 끝까지의 거리를 계산하고
 * 건우와 민준이의 거리와 건우와 마산까지의 거리의 합이 민준이와 마산까지의 거리와 같으면 건우를 데리고 간다
 * 
 * 
 * tip
 * 거리가 짧은것을 우선 탐색하여 시간을 줄여보았음
 * 
 * 
 */

package algo_1월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G4_18223_민준이와마산그리고건우 {
	static int V, E, G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		ArrayList<pair> arr[] = new ArrayList[V + 1];
		int dist[] = new int[V + 1];
		for (int i = 0; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i <= V; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			pair p1 = new pair(a, c, 0);
			pair p2 = new pair(b, c, 0);
			arr[b].add(p1);
			arr[a].add(p2);
		}
		dist[1] = 0;
		PriorityQueue<pair> pq = new PriorityQueue<pair>(new Comparator<pair>() {

			@Override
			public int compare(pair o1, pair o2) {
				return o2.weight - o1.weight;
			}
		});
		for (pair i : arr[1]) {
			pq.offer(i);
		}
		while (!pq.isEmpty()) {
			pair tmp = pq.poll();
			if (dist[tmp.num] > (tmp.weight + tmp.sum)) {
				dist[tmp.num] = (tmp.weight + tmp.sum);
				for (pair i : arr[tmp.num]) {
					i.sum = (tmp.weight + tmp.sum);
					pq.offer(i);
				}
			}
		}
		int res = dist[V];
		int gunwoo = dist[G];
		for (int i = 0; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[G] = 0;
		for (pair i : arr[G]) {
			pq.offer(i);
		}
		for (int i = 1; i <= V; i++) {
			for (pair pair : arr[i]) {
				pair.sum = 0;
			}
		}
		while (!pq.isEmpty()) {
			pair tmp = pq.poll();
			if (dist[tmp.num] > (tmp.weight + tmp.sum)) {
				dist[tmp.num] = (tmp.weight + tmp.sum);
//				System.out.println(Arrays.toString(dist));
				for (pair i : arr[tmp.num]) {
					i.sum = (tmp.weight + tmp.sum);
					pq.offer(i);
				}
			}
		}
		if (res == gunwoo + dist[V]) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}

	static class pair {
		int num;
		int weight;
		int sum;

		public pair(int num, int weight, int sum) {
			this.num = num;
			this.weight = weight;
			this.sum = sum;
		}

	}
}
