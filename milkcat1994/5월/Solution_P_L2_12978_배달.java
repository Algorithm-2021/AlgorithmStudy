import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * -배달-
 * 우선순위큐 사용하는 다익스트라 알고리즘 이용
 * O(E*log(V))
 * 
 * 우선순위큐 사용 하지 않고 매번 방문 체크 및 가장 작은 값 가져온다면
 * O(V^2+E)
 * 
 * 테스트 26 〉	통과 (4.92ms, 53.7MB)
 * 테스트 27 〉	통과 (4.02ms, 53.2MB)
 * 테스트 28 〉	통과 (11.14ms, 54.3MB)
 * 테스트 29 〉	통과 (6.16ms, 57.3MB)
 * 테스트 30 〉	통과 (7.82ms, 54.7MB)
 * 테스트 31 〉	통과 (1.45ms, 53.1MB)
 * 테스트 32 〉	통과 (1.43ms, 52.4MB)
 * 
 * 풀이시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12978
public class Solution_P_L2_12978_배달 {
	
	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		List<ArrayList<Edge>> edges = new ArrayList<>();
		for(int i=0; i<=N; ++i)
			edges.add(new ArrayList<>());
		
		for(int i=0; i<road.length; ++i) {
			int s = road[i][0];
			int e = road[i][1];
			int w = road[i][2];
			
			edges.get(s).add(new Edge(e, w));
			edges.get(e).add(new Edge(s, w));
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Vertex(1, 0));
		dist[1] = 0;
		
		Vertex curv;
		while(!pq.isEmpty()) {
			curv = pq.poll();
			
			// 이미 최소 값으로 갱신 되어있다면 해당 정점 확인 할 필요 없음
			if(dist[curv.idx] < curv.w) continue;
			
			// 해당 정점에서 뻗어나가며 연결된 정점 최소값 갱신 및 pq에 추가
			for(Edge te : edges.get(curv.idx)) {
				if(dist[te.e] <= te.w + curv.w) continue;
				dist[te.e] = te.w + curv.w;
				pq.offer(new Vertex(te.e, dist[te.e]));
			}
		}
		
		for(int i=1; i<=N; ++i)
			if(dist[i] <= K) answer++;
		
		return answer;
	}
	
	class Vertex {
		int idx, w;
		Vertex(int idx, int w){
			this.idx = idx;
			this.w = w;
		}
	}
	
	class Edge {
		int e, w;
		Edge(int e, int w){
			this.e = e;
			this.w = w;
		}
	}

	public static void main(String[] args) {
		Solution_P_L2_12978_배달 sol = new Solution_P_L2_12978_배달();
		int N = 5;
		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K = 3;
		int answer = sol.solution(N, road, K);
		System.out.println(answer);
	}
}