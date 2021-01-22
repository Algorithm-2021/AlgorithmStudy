import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -탑-
 * 1. 왼쪽부터 순서대로 탑의 높이를 Stack에 넣는다.
 * 2. Stack의 top 부분부터 현재 들어오는 높이보다 높은지 확인한다.
 * 3. 현재 들어오는 높이가 높다면 그보다 작은 높이를 가지는 높이는 필요없으므로 pop해준다.
 * 4. 현재 들어오는 높이가 top부분보다 같거나 작다면 레이저를 수신할 수 있다. -> 해당 위치에 대한 탐색을 종료한다.
 * 5. isFind 변수를 이용하여 수신을 할 수 있다면 수신idx를, 수신 하지 못 한다면 0을 StringBuilder에 더해준다.
 * 
 * 메모리 : 100800KB
 * 시간 : 800ms
 * 풀이 시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/2493
public class Main_B_G5_2493_탑 {
	static int N;
	
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		System.out.print(sb.toString());
	}
	
	static void solve() {
		sb = new StringBuilder();
		
		Stack<Tower> stack = new Stack<>();
		int th;
		boolean isFind;
		Tower tt;
		for(int i=1; i<=N; ++i) {
			isFind = false;
			th = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				tt = stack.peek();
				
				// 마지막으로 저장된 높이가 새로 들어오는 높이보다 작다면 마지막 저장 높이는 볼 필요 없음.
				if(tt.h < th) {
					stack.pop();
				}
				else {
					isFind = true;
					break;
				}
			}
			
			if(isFind)
				sb.append(stack.peek().idx+" ");
			else
				sb.append("0 ");
			
			stack.push(new Tower(th, i));
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
	}
	
	static class Tower {
		int h, idx;
		
		Tower(int h, int idx){
			this.h = h;
			this.idx = idx;
		}
	}
}
