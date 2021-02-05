package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 메모리 11664kb
 * 실행시간 80ms
 * 풀이시간 3H
 *
 */
public class Main_B_S1_1068_트리_2트{
	static int N;
	static int[] parent; // 연결된 부모노드 
	static boolean[] isDeleted; //삭제 노드 판별

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		parent = new int[N];
		isDeleted = new boolean[N];
		ArrayList<Integer> parents = new ArrayList<>(); //부모노드 정보 저장		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for(int i=0; i<N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			isDeleted[i] = false;
		}

		//제거할 노드부터 제거 시작
		int delete = Integer.parseInt(in.readLine());
		bfs(delete);
		
		
		for(int i=0; i<N; i++) {
			//i번째 노드가 삭제되지 않았고, 부모노드가 있다면(0이상)
			if(!isDeleted[i] && parent[i] >= 0) {
				parents.add(parent[i]); 
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			//삭제되지 않았고, 자식이 있는 노드 제외
			if(!isDeleted[i] && !parents.contains(i)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		isDeleted[start] = true;		
		q.add(start);
		
		while(!q.isEmpty()) {
			int next = q.poll();
			for(int i=0; i<N; i++) {
				//next가 i의 부모이고, i가 삭제되지 않았다면
				if(next == parent[i] && !isDeleted[i]) {
					isDeleted[i] = true; // i를 삭제하고 큐에 추가.
					q.add(i);
				}
			}
		}
	}
	
}
