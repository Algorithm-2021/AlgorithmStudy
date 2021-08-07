import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -순위-
 * 위상정렬을 가장 먼저 생각하였으나 이 문제는 자신보다 뒤에 실행되는 수도 알아야 하여 다르게 풀었다.
 * 
 * 테스트 1 〉	통과 (0.23ms, 52.4MB)
 * 테스트 2 〉	통과 (0.24ms, 52.1MB)
 * 테스트 3 〉	통과 (0.32ms, 52.4MB)
 * 테스트 4 〉	통과 (0.33ms, 52.7MB)
 * 테스트 5 〉	통과 (2.09ms, 52.9MB)
 * 테스트 6 〉	통과 (3.43ms, 52.9MB)
 * 테스트 7 〉	통과 (7.88ms, 53.2MB)
 * 테스트 8 〉	통과 (14.38ms, 54.4MB)
 * 테스트 9 〉	통과 (19.56ms, 57.4MB)
 * 테스트 10 〉	통과 (24.62ms, 57.9MB)
 * 
 * 풀이 시간 : 1H 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/49191
public class Solution_P_L3_49191_순위 {
	public int solution(int n, int[][] results) {
		int answer = 0;
		
		List<ArrayList<Integer>> edgeList = new ArrayList<>();
		int[][] edgeCnt = new int[n+1][2];
		for(int i=0; i<=n; ++i)
			edgeList.add(new ArrayList<>());
		
		int s,e;
		for(int i=0; i<results.length; ++i) {
			s = results[i][0];
			e = results[i][1];
			
			edgeList.get(s).add(e);
		}
		
		for(int i=1; i<=n; ++i) {
			bfs(i, n, edgeList, edgeCnt);
		}
		
		for(int i=1; i<=n; ++i) {
			if(edgeCnt[i][0]+edgeCnt[i][1] == n-1)
				answer++;
		}
		return answer;
	}
	
	public void bfs(int start, int n, List<ArrayList<Integer>> edgeList, int[][] edgeCnt) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		boolean[] isVisited = new boolean[n+1];
		
		int s;
		while(!que.isEmpty()) {
			s = que.poll();
			for(Integer ti : edgeList.get(s)) {
				if(isVisited[ti]) continue;
				que.offer(ti);
				isVisited[ti] = true;
				edgeCnt[ti][0]++;
				edgeCnt[start][1]++;
			}
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_49191_순위 sol = new Solution_P_L3_49191_순위();
		int n=5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		int answer = sol.solution(n, results);
		System.out.println(answer);
	}
}