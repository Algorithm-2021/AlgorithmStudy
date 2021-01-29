/*
 * 11640KB
 * 80ms
 * 30m
 * 1. 전체 트리에서 리프노드 체크 (부모인 노드들을 false)
 *    leaf[node[i]] = false
 *    루트노드는 -1이므로 따로 처리
 * 2. 삭제하는 서브트리 노드 leaf false처리 및 node정보 -1로 변환
 * 3. 삭제하는 노드의 부모가 리프노드가 되었는지 확인.
 * 4. 전체 리프노드 수 세기
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S1_1068_트리_정세린 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] node = new int[N];
		int root = 0;	// 루트 노드 저장
		boolean[] leaf = new boolean[N];
		Arrays.fill(leaf, true); 	// 리프 노드가 아닌 것을 false로 변경
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			node[i] = Integer.parseInt(st.nextToken());
			if (node[i] == -1) {
				leaf[i] = false; // 루트노드는 리프가 아님
				root = i;
			}else {
				leaf[node[i]] = false;	// node[i]는 부모임
			}
		}
		int subRoot = Integer.parseInt(br.readLine());	// 지울 서브트리의 root
		// end of input
		int subParents = node[subRoot];	// 지우는 노드의 부모가 leaf노드가 되었는지 확인하기 위함

		if (subRoot == root) {
			System.out.println(0);
			return;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(subRoot);
		// 지워지는 서브트리의 리프 false
		while(!q.isEmpty()) {
			int cur = q.poll();	// 지우는 노드
			leaf[cur] = false;	// 지워지기 떄문에 leaf가 반드시 아님
			node[cur] = -1;	// 지워진 노드 표시
			
			for (int i = 0; i < N; i++) {
				if (node[i] == cur) {
					q.offer(i);
				}
			}
		}
        
		int leafNum = 0;
		boolean isLeaf = true;	// 삭제학 노드의 부모는 리프노드인가?
		for (int i = 0; i < N; i++) {
			if (node[i] == subParents) isLeaf = false;	// 삭제한 노드의 부모의 다른 자식노드가 존재함
			if (leaf[i]) leafNum++;
		}
		if (isLeaf) leafNum++;
		
		System.out.println(leafNum);
	}	// end of main

}
