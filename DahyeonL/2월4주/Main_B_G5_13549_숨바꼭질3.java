package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 : 16564 KB
 * 시간 :  104 ms
 * 풀이 시간 : 1H 20M
 * 	원래 이 문제는 단순한 BFS를 요구하는 문제가 아닙니다. 
 * 	왜냐하면, BFS를 하기 위해서는 모든 간선의 가중치가 동일해야 한다는 전제 조건이 필요하기 때문입니다. 
 * 	이 문제는 가중치가 0인 간선이 있기 때문에 일반적으로는 단순한 BFS를 쓸 수 없으나, 
 * 	문제의 특성 때문에 방문 순서에 따라서 단순 BFS로도 우연히도 항상 정답을 찾을 수 있을 뿐입니다. 
 * 	왜 하필 이 순서로 하면 항상 정답이 나오는가를 증명하는 건 매우 복잡한 일입니다.
 *	이 문제를 보다 일반화된 경우 (가중치가 0인 간선이 있는 경우)에 대해 해결하려면, 즉, 이 문제의 의도대로 풀려면 다음과 같은 방법들을 사용할 수 있습니다.
 *	-다익스트라 알고리즘
 *	-0-1 BFS: 가중치가 0인 간선에 연결된 정점은 큐의 맨 뒤가 아닌 맨 앞에 넣는 방법
 * 	-2를 별도의 간선으로 생각하지 않고, +1이나 -1에 의한 좌표를 큐에 넣을 때 그 좌표의 2의 거듭제곱 배인 좌표들을 전부 큐에 넣는 방법
 */
public class Main_B_G5_13549_숨바꼭질3 {
static boolean visited[];
static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		visited = new boolean[100001];
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		BFS(N,K);
	}
	
	static void BFS(int N, int K) {
		Queue<node> q = new LinkedList<node>();
		q.offer(new node(N, 0));
		visited[N] = true;
		A:while(!q.isEmpty()) {
			node temp = q.poll();
			if(temp.point==K) {
				if(temp.sec<min) {
					min = temp.sec;
				}
				continue A;
			}
			if(temp.sec>min) {
				continue A;
			}
			int tempN = temp.point*2;
			if(temp.point!=0&&tempN<=100000&&!visited[tempN]) {
				q.offer(new node(tempN,temp.sec));
				if(temp.point*2!=K) {
					visited[tempN] =true;
				}
			}
			if(0<=temp.point-1&&!visited[temp.point-1]) {
				q.offer(new node(temp.point-1,temp.sec+1));
				if(temp.point-1!=K) {
				visited[temp.point-1] = true;
				}
			}
			if(temp.point+1<=100000&&!visited[temp.point+1]) {
				q.offer(new node(temp.point+1,temp.sec+1));
				if(temp.point+1!=K) {
					visited[temp.point+1] = true;
				}
			}
			
			
		}
		System.out.println(min);
	}
	
	static class node{
		int point;
		int sec;
		
		public node(int point, int sec) {
			this.point = point;
			this.sec = sec;
		}
	}
}
