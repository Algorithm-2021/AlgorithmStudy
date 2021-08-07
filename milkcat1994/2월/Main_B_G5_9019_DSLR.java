import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -DSLR-
 * 1. BFS를 이용하여 목표 숫자가 나온다면 결과를 반환한다.
 * 2. Number Class를 이용하여 현재 숫자와 해당 숫자를 만드는 명령어를 기억한다.
 * 
 * 
 * 메모리 : 302132KB
 * 시간 : 2568ms
 * 풀이 시간 : 1H 30M
 */

//출처 : https://www.acmicpc.net/problem/9019
public class Main_B_G5_9019_DSLR {
	static final int MOD = 10000;
	static int TC, origin, target;
	static boolean[] isVisited;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(--TC>=0) {
			init();
			
			sb.append(solve()+"\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static String solve() {
		Queue<Number> que = new LinkedList<>();
		que.offer(new Number(origin, ""));
		
		Number tn;
		String str;
		while(!que.isEmpty()) {
			tn = que.poll();
			
			str = changeNumber(que, tn);
			if(str != null) {
				return str;
			}
		}
		
		return "x";
	}
	
	static String changeNumber(Queue<Number> que, Number number) {
		Number tn;
		tn = D(number);
		if(tn != null) {
			if(isTarget(tn.num))
				return tn.str;
			isVisited[tn.num] = true;
			que.offer(tn);
		}

		tn = S(number);
		if(tn != null) {
			if(isTarget(tn.num))
				return tn.str;
			isVisited[tn.num] = true;
			que.offer(tn);
		}

		tn = L(number);
		if(tn != null) {
			if(isTarget(tn.num))
				return tn.str;
			isVisited[tn.num] = true;
			que.offer(tn);
		}

		tn = R(number);
		if(tn != null) {
			if(isTarget(tn.num))
				return tn.str;
			isVisited[tn.num] = true;
			que.offer(tn);
		}
		
		return null;
	}
	
	static boolean isTarget(int num) {
		if(num == target)
			return true;
		return false;
	}
	
	static Number D(Number number) {
		int num = number.num;
		num = (num*2)%MOD;
		
		return isVisited[num] ? null : new Number(num, number.str+"D");
	}

	static Number S(Number number) {
		int num = number.num == 0 ? 9999 : number.num-1;
		
		return isVisited[num] ? null : new Number(num, number.str+"S");
	}

	static Number L(Number number) {
		int num = moveL(number.num);
		
		return isVisited[num] ? null : new Number(num, number.str+"L");
	}
	
	static Number R(Number number) {
		int num = moveR(number.num);
		
		return isVisited[num] ? null : new Number(num, number.str+"R");
	}
	
	static int moveL(int num) {
		int res = (num % 1000) * 10;
		res += num/1000;
		return res;
	}

	static int moveR(int num) {
		int res = num / 10;
		res += num % 10 * 1000;
		return res;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		
		origin = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[10000];
	}
	
	static class Number {
		int num;
		String str;
		
		Number(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}
}
