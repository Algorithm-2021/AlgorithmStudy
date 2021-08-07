import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -사촌-
 * 1. 각 Node는 depth와 부모 노드를 기억한다.
 * 2. 부모의 부모 노드가 같고 부모의 노드가 다른 노드의 개수만 더해준다.
 * 3. 깊이가 같은 부분을 찾아 해당 깊이만 수행하였다.
 * 
 * 메모리 : 208252KB
 * 시간 : 988ms
 * 풀이 시간 : 2H
 */

//출처 : https://www.acmicpc.net/problem/9489
public class Main_B_G4_9489_사촌 {
	static int N, K;
	
	static Node[] nodes;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		while(init()) {
			sb.append(solve()+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve() {
		// 타겟 노드 찾기
		Node target = nodes[0];
		for(int i=0; i<N; ++i) {
			if(nodes[i].cur == K) {
				target = nodes[i];
				break;
			}
		}
		int targetDepth = target.depth;
		
		// 시작 깊이 찾기
		int startDepthIdx = 0;
		for(int i=0; i<N; ++i) {
			if(targetDepth == nodes[i].depth) {
				startDepthIdx = i;
				break;
			}
		}
		
		int targetParent = target.parent == null ? 0 : target.parent.cur;
		int targetDescent = target.parent.parent == null ? nodes[0].cur : target.parent.parent.cur;
		
		// 깊이가 다르다면 중지
		// 조상은 같고, 부모가 다르다면 값++
		int res=0;
		for(int i = startDepthIdx; i<N; ++i) {
			if(targetDepth != nodes[i].depth) {
				break;
			}
			
			if(targetDescent == nodes[i].parent.parent.cur && nodes[i].parent.cur != targetParent)
				res++;
		}
		return res;
	}
	
	static boolean init() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N==0 && K==0)
			return false;
		
		nodes = new Node[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		// parent : 부모 idx
		int parent = -1;
		// 현재 NodeNum, 이전 NodeNum;
		int cur, ex;
		// 깊이
		int depth;
		
		//첫 노드 초기화
		cur = Integer.parseInt(st.nextToken());
		ex = cur;
		depth = 0;
		nodes[0] = new Node();
		nodes[0].set(parent, cur, depth);
		
		for(int i=1; i<N; ++i) {
			cur = Integer.parseInt(st.nextToken());
			// parent 다음 idx로 변경
			if(ex+1 < cur)
				parent++;
			
			nodes[i] = new Node(parent, cur, nodes[parent].depth+1);
			
			if(depth < nodes[i].depth) {
				depth++;
			}
			ex = cur;
		}
		
		return true;
	}
	
	static class Node {
		Node parent;
		int cur, depth;
		
		Node(){
			this.parent = null;
		}
		
		Node(int parent, int cur, int depth){
			this.parent = parent == -1 ? new Node() : nodes[parent];
			this.cur = cur;
			this.depth = depth;
		}
		
		void set(int parent, int cur, int depth) {
			this.parent = parent == -1 ? nodes[0] : nodes[parent];
			this.cur = cur;
			this.depth = depth;
		}
	}
}
