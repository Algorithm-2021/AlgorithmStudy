/*
테스트 26 〉	통과 (14.91ms, 55MB)
테스트 27 〉	통과 (13.84ms, 52.9MB)
테스트 28 〉	통과 (19.29ms, 52.8MB)
테스트 29 〉	통과 (17.63ms, 53.3MB)
테스트 30 〉	통과 (14.96ms, 53.6MB)
1H 30
 */
package PROGRAMMERS;

import java.util.LinkedList;

public class Solution_L2_67257_수식최대화_정세린 {

	static class Solution {
		char[] op = { '+', '-', '*' };
		boolean[] visited = new boolean[3];
		char[] selected = new char[3]; // 연산자 우선순위
		long max = -1;
		LinkedList<Long> numList = new LinkedList<>();
		LinkedList<Character> opList = new LinkedList<>();
	
		public long solution(String expression) {
			long answer = 0;
			String num = "";
			for (int i = 0; i < expression.length(); i++) {
				if (!Character.isDigit(expression.charAt(i))) { // op
					opList.add(expression.charAt(i));
					numList.add(Long.parseLong(num));
					num = "";
				} else {
					num = num + expression.charAt(i);
				}
			}
			numList.add(Long.parseLong(num));
			
			permutation(3, 3);
			
			answer = max;
			return answer;
		}
	
		// 수식의 우선순위 정하기
		void permutation(int n, int r) {
			if (r == 0) {
				calculate();
				return;
			}
			for (int i = 0; i < 3; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				selected[r - 1] = op[i];
				permutation(n, r - 1);
				visited[i] = false;
			}
		} // end of permutation
	
		void calculate() {
			int pre = 0;
			LinkedList<Long> numListCopy = (LinkedList<Long>) numList.clone();
			LinkedList<Character> opListCopy = (LinkedList<Character>) opList.clone();
			int idx = 0;
			while (numListCopy.size() > 1) { // 결과가 나올 때 까지
				if (opListCopy.get(idx) == selected[pre]) {
					long result = calc(numListCopy.get(idx), numListCopy.get(idx + 1), opListCopy.get(idx));
					opListCopy.remove(idx);
					numListCopy.remove(idx);
					numListCopy.remove(idx);
					numListCopy.add(idx, result);
				}
				else idx++;
				
				if (idx >= opListCopy.size()) {
					idx = 0;
					pre++;
					if (pre > 3) break;
				}
			}
			max = Math.max(max, Math.abs(numListCopy.get(0)));
	
		} // end of calculate()
	
		long calc(long num1, long num2, char op) {
			switch (op) {
			case '+': return num1 + num2;
			case '-': return num1 - num2;
			case '*': return num1 * num2;
			}
			return 0;
		} // end of calc()
	
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
//		String expression = "100-200*300-500+20";
		String expression = "3+9999-2";
		Long ans = solution.solution(expression);
		System.out.println(ans);
	}

}
