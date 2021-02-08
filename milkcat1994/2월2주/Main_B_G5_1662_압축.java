import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * -압축-
 * 1. K(Q) : Q가 K번 반복된다.
 * 2. ')'를 만나면 '('를 만날때 까지 내부 숫자를 빼며 자릿수를 더해주고, K와 곱하여 총 자릿수를 구해준다.
 * 
 * 메모리 : 14864KB
 * 시간 : 140ms
 * 풀이 시간 : 2H
 */

//출처 : https://www.acmicpc.net/problem/1662
public class Main_B_G5_1662_압축 {
	static String str;
	static Stack<MyStr> stack;
	
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		print();
	}
	
	static void solve() {
		int length = str.length();
		char tch;
		for(int i=0; i<length; ++i) {
			tch = str.charAt(i);
			switch (tch) {
			case ')':
				decompression();
				break;
			case '(':
				stack.push(new MyStr(String.valueOf(tch), 1));
				break;
			default:
				stack.push(new MyStr(String.valueOf(tch), 1));
				break;
			}
		}
	}
	
	static void decompression() {
		MyStr ts;
		boolean isClosed=false;
		int digit=0;
		while(!isClosed) {
			ts = stack.peek();
			switch (ts.str) {
			case "(":
				stack.pop();
				isClosed=!isClosed;
				break;
			default:
				digit += stack.pop().digit;
				break;
			}
		}
		
		stack.push(new MyStr("1", Integer.parseInt(stack.pop().str)*digit));
	}
	
	static void print() {
		int res=0;
		while(!stack.isEmpty()){
			res+=stack.pop().digit;
		}
		System.out.println(res);
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stack = new Stack<MyStr>();
		
		str = br.readLine();
	}
	
	static class MyStr {
		String str;
		int digit;
		
		MyStr(String str, int digit){
			this.str = str;
			this.digit = digit;
		}
	}
}
