import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -트리의 지름-
 * 1. 임의의 위치에서 가장 먼 vertex찾기
 * 2. 해당 vertex에서 가장 먼 길이 찾기
 * 
 * 
 * 메모리 : 94932KB
 * 시간 : 920ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/1167
public class Main_B_G3_1167_트리의지름 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static boolean[] isVisited;
	
	static List<ArrayList<Edge>> edgeList;
	
	public static void main(String[] args) throws Exception {
		init();
		
		Vertex farVertex = findFarVertex(1);
		
		Arrays.fill(isVisited, false);
		System.out.println(findFarVertex(farVertex.idx).w);
	}
	
	static Vertex findFarVertex(int s) {
		Queue<Vertex> que = new LinkedList<>();
		que.offer(new Vertex(s, 0));
		isVisited[s] = true;
		
		Vertex farVertex = new Vertex(0,0);
		Vertex vertex;
		while(!que.isEmpty()) {
			vertex = que.poll();
			
			if(farVertex.w < vertex.w) {
				farVertex.build(vertex);
			}
			
			for(Edge edge : edgeList.get(vertex.idx)) {
				if(isVisited[edge.e]) continue;
				que.offer(new Vertex(edge.e, vertex.w + edge.w));
				isVisited[edge.e] = true;
			}
		}
		
		return farVertex;
	}
	
	static class Vertex{
		int idx, w;
		
		Vertex(int idx, int w){
			this.idx = idx;
			this.w = w;
		}
		
		void build(Vertex v){
			this.idx = v.idx;
			this.w = v.w;
		}
	}

	static class Edge {
		int s, e, w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static void init() throws Exception {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		isVisited = new boolean[N+1];
		edgeList = new ArrayList<ArrayList<Edge>>();
		
		for(int i=0; i<=N; ++i) {
			edgeList.add(new ArrayList<Edge>());
		}
		
		ArrayList<Edge> list;
		
		int s, e, w;
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			
			list = edgeList.get(s);
			
			while(st.hasMoreTokens()) {
				e = Integer.parseInt(st.nextToken());
				if(e == -1) break;
				
				w = Integer.parseInt(st.nextToken());
				
				list.add(new Edge(s,e,w));
			}
		}
	}
	
}
