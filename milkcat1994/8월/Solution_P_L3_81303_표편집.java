import java.util.Stack;

/*
 * -표 편집-
 * 1. LinkedList의 Node를 직접 구현하여 remove, undo 구현
 * 
 * 
 * 테스트 26 〉	통과 (0.52ms, 73.7MB)
 * 테스트 27 〉	통과 (0.56ms, 70MB)
 * 테스트 28 〉	통과 (0.44ms, 58.3MB)
 * 테스트 29 〉	통과 (0.64ms, 57.9MB)
 * 테스트 30 〉	통과 (0.72ms, 59MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (202.12ms, 132MB)
 * 테스트 2 〉	통과 (236.82ms, 131MB)
 * 테스트 3 〉	통과 (231.00ms, 134MB)
 * 테스트 4 〉	통과 (311.97ms, 157MB)
 * 테스트 5 〉	통과 (311.52ms, 158MB)
 * 테스트 6 〉	통과 (314.78ms, 153MB)
 * 테스트 7 〉	통과 (162.15ms, 106MB)
 * 테스트 8 〉	통과 (218.18ms, 106MB)
 * 테스트 9 〉	통과 (343.75ms, 158MB)
 * 테스트 10 〉	통과 (340.12ms, 157MB)
 * 풀이 시간 : 30m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/
public class Solution_P_L3_81303_표편집 {
	
	public String solution(int n, int k, String[] cmd) {
		Node[] nodes = new Node[n];

		for(int i=0; i<n; ++i) {
			nodes[i] = new Node(i);
		}
		
		nodes[0].next = nodes[1];
		for(int i=1; i<n-1; ++i) {
			nodes[i].next = nodes[i+1];
			nodes[i].prev = nodes[i-1];
		}
		nodes[n-1].prev = nodes[n-2];
		
		Stack<Node> stack = new Stack<>();
		
		Node cur = nodes[k];
		String[] strArr;
		int cnt;
		for(String str : cmd) {
			strArr = str.split(" ");
			switch (strArr[0]) {
			case "U":
				cnt = Integer.parseInt(strArr[1]);
				while(--cnt >= 0) {
					cur = cur.prev;
				}
				break;
			case "D":
				cnt = Integer.parseInt(strArr[1]);
				while(--cnt >= 0) {
					cur = cur.next;
				}
				break;
			case "C":
				cur.remove();
				stack.push(cur);
				// 마지막이라면 이전 행 선택
				if(cur.next == null) {
					cur = cur.prev;
				}
				// 마지막이 아니라면 다음 행 선택
				else {
					cur = cur.next;
				}
				break;
			case "Z":
				stack.pop().undo();
				break;
			default:
				// do Nothing
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(Node node : nodes) {
			sb.append(node.isRemove ? 'X' : 'O');
		}
		return sb.toString();
	}

	public class Node {
		Node prev;
		Node next;
		int idx;
		boolean isRemove;
		
		Node(int idx){
			this.idx = idx;
			this.isRemove = false;
		}
		
		public void remove(){
			// 더이상 위로 올라갈 수 없는 경우
			if(this.prev != null) {
				this.prev.next = this.next;
			}
			if(this.next != null) {
				this.next.prev = this.prev;
			}
			this.isRemove = true;
		}
		
		public void undo() {
			if(this.prev != null) {
				this.prev.next = this;
			}
			if(this.next != null) {
				this.next.prev = this;
			}
			this.isRemove = false;
		}
	}
	
	public static void main(String[] args) {
		Solution_P_L3_81303_표편집 sol = new Solution_P_L3_81303_표편집();
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		String answer = sol.solution(n, k, cmd);
		System.out.println(answer);
	}
}