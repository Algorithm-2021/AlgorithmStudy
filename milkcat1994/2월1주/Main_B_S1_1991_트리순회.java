import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -트리순회-
 * 각 Node별 left, right에 대한 idx번호를 가지고 있다.
 * dfs로 Node들을 순회한다.
 * 
 * 메모리 : 14604KB
 * 시간 : 128ms
 * 풀이 시간 : 20M
 */

//출처 : https://www.acmicpc.net/problem/1991
public class Main_B_S1_1991_트리순회 {
	static int N;
	static final int SIZE = 'Z'-'A' + 1;
	static Node[] nodes;
	
	public static void main(String[] args) throws Exception {
		init();
		
		preOrder(0);
		System.out.println();
		
		inOrder(0);
		System.out.println();
		
		postOrder(0);
	}
	
	static void preOrder(int idx) {
		System.out.print(nodes[idx].cur);
		
		if(nodes[idx].l != -1)
			preOrder(nodes[idx].l);
		
		if(nodes[idx].r != -1)
			preOrder(nodes[idx].r);
	}
	
	static void inOrder(int idx) {
		if(nodes[idx].l != -1)
			inOrder(nodes[idx].l);
		
		System.out.print(nodes[idx].cur);
		
		if(nodes[idx].r != -1)
			inOrder(nodes[idx].r);
	}
	
	static void postOrder(int idx) {
		if(nodes[idx].l != -1)
			postOrder(nodes[idx].l);
		
		if(nodes[idx].r != -1)
			postOrder(nodes[idx].r);
		
		System.out.print(nodes[idx].cur);
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String str;
		char c,l,r;
		nodes = new Node[SIZE+1];
		for(int i=0; i<N; ++i) {
			str = br.readLine();
			c = str.charAt(0);
			l = str.charAt(2);
			r = str.charAt(4);
			
			nodes[c-'A'] = new Node(c, l =='.' ? -1 : l-'A', r == '.' ? -1 : r-'A');
		}
	}
	
	static class Node {
		char cur;
		int l,r;
		
		Node(char cur, int l, int r){
			this.cur = cur;
			this.l = l;
			this.r = r;
		}
	}
}
