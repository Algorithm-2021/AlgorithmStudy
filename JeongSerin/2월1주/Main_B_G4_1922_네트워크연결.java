/*
 * 47004KB
 * 524ms
 * 30m
 * Kruskal, union find
 * 1. 가중치 오름차순으로 간선 정렬
 * 2. 사이클이 생기지 않도록 가중치 작은 간선부터 선택
 * 사이클 여부? union find
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_B_G4_1922_네트워크연결 {

	static int[] parents;
	static int N, M;	// 컴퓨터 수와 간선 수
	static Edge[] graph;	// 간선 정보 저장
	
	static class Edge{
		int a, b;
		int weight;
		
		public Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		makeSet(N);
		graph = new Edge[M];
		
		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[i] = new Edge(a, b, weight);
		}	// end of input
		
		// 1. 오름차순 정렬
		Arrays.sort(graph, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
		});
		
		// 2. union
		int sum = 0;
		int selectedEdge = 0;
		for (int i = 0; i < graph.length; i++) {
			boolean union = unionSet(graph[i].a, graph[i].b);
			if (union) {
				sum += graph[i].weight;
				selectedEdge++;
			}
			if (selectedEdge >= N - 1) break;	// 모든 간선 선택
		}
		System.out.println(sum);
	}
	
	static void makeSet(int n) {
		parents = new int[n + 1]; // 컴퓨터 번호 1번부터
		Arrays.fill(parents, -1);
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;	// 부모 노드라면 해당 노드의 번호리턴
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;	// 합칠 수 없음
		parents[bRoot] = aRoot;
		return true;
	}
	
}
