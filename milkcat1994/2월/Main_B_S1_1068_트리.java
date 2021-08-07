import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -트리-
 * 1. HashMap이용한 Tree의 Node 번호에 따른 Child기억
 * 2. 삭제 idx는 HashMap의 Key에서 삭제하여 search되지 못하도록 수정
 * 
 * ++ 직접 Node라는 class배열을 생성, 내부적으로 child List를 가지는 식으로 하면 좋을듯.
 * 
 * 메모리 : 14816KB
 * 시간 : 128ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/1068
public class Main_B_S1_1068_트리 {
	// Node개수, 삭제 NodeIdx, 최상위부모 NodeIdx
	static int N, R, S;
	
	static HashMap<Integer, List<Integer>> hm;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static int solve() {
		hm.remove(R);
		int res=0, nodeIdx, childSize;
		
		Queue<Integer> que = new LinkedList<>();
		List<Integer> list;
		que.offer(S);
		while(!que.isEmpty()) {
			nodeIdx = que.poll();
			// 지워진 Node라면 continue
			if(!hm.containsKey(nodeIdx)) continue;
			
			// childList배열 가져와 지워진 Node가 있는지 확인, leaf Node라면 res++
			list = hm.get(nodeIdx);
			childSize = 0;
			for(Integer ti : list) {
				if(ti != R) {
					childSize++;
				}
			}
			
			if(childSize == 0) res++;
			que.addAll(list);
		}
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		hm = new HashMap<Integer, List<Integer>>();
		for(int i=0; i<N; ++i)
			hm.put(i, new ArrayList<>());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int ti;
		for(int i=0; i<N; ++i) {
			ti = Integer.parseInt(st.nextToken());
			
			if(ti == -1)
				S = i;
			else
				hm.get(ti).add(i);
		}
		
		R = Integer.parseInt(br.readLine());
	}
}