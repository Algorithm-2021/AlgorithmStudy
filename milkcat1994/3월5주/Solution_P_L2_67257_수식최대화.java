import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * -수식 최대화-
 * 테스트 1 〉	통과 (0.57ms, 52.9MB)
 * 테스트 2 〉	통과 (0.54ms, 53.1MB)
 * 테스트 3 〉	통과 (0.72ms, 52.3MB)
 * 테스트 4 〉	통과 (0.73ms, 52.3MB)
 * 테스트 5 〉	통과 (0.76ms, 53MB)
 * 테스트 6 〉	통과 (0.80ms, 52.1MB)
 * 테스트 7 〉	통과 (0.82ms, 53.1MB)
 * 테스트 8 〉	통과 (0.80ms, 53MB)
 * 테스트 9 〉	통과 (0.89ms, 52.4MB)
 * 테스트 10 〉	통과 (1.91ms, 53.4MB)
 * 테스트 11 〉	통과 (1.00ms, 52.4MB)
 * 테스트 12 〉	통과 (1.81ms, 52.2MB)
 * 테스트 13 〉	통과 (1.01ms, 53.5MB)
 * 테스트 14 〉	통과 (1.12ms, 53.8MB)
 * 테스트 15 〉	통과 (1.36ms, 53MB)
 * 테스트 16 〉	통과 (0.62ms, 52MB)
 * 테스트 17 〉	통과 (0.65ms, 52.3MB)
 * 테스트 18 〉	통과 (0.62ms, 52.5MB)
 * 테스트 19 〉	통과 (0.59ms, 52.4MB)
 * 테스트 20 〉	통과 (0.38ms, 53MB)
 * 테스트 21 〉	통과 (1.18ms, 52MB)
 * 테스트 22 〉	통과 (1.20ms, 52MB)
 * 테스트 23 〉	통과 (0.55ms, 52MB)
 * 테스트 24 〉	통과 (2.50ms, 53.1MB)
 * 테스트 25 〉	통과 (1.49ms, 53.2MB)
 * 테스트 26 〉	통과 (0.49ms, 52.6MB)
 * 테스트 27 〉	통과 (1.23ms, 51.8MB)
 * 테스트 28 〉	통과 (1.25ms, 52MB)
 * 테스트 29 〉	통과 (1.09ms, 52.2MB)
 * 테스트 30 〉	통과 (1.19ms, 53.4MB)
 * 
 * 풀이 시간 : 35M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/67257
public class Solution_P_L2_67257_수식최대화 {
	char[] operatorIdx = {'*', '-', '+'};
	long maxRes = 0;
	
	public long solution(String expression) {
		Queue<Long> num = new LinkedList<>();
		Queue<Character> operator = new LinkedList<>();
		
		for(String str : expression.split("[*\\-+]"))
			num.add(Long.parseLong(str));
		
		for(int i=0; i<expression.length(); ++i) {
			switch (expression.charAt(i)) {
			case '*':
			case '-':
			case '+':
				operator.add(expression.charAt(i));
				break;
			
			default:
				// do nothing
				break;
			}
		}
		
		permutation(num, operator, 0, 0);
		
		return maxRes;
	}
	
	// selected >> *,-,+
	void permutation(Queue<Long> num, Queue<Character> operator, int selected, int cnt) {
		if(cnt == 3) {
			maxRes = Long.max(maxRes, Math.abs(num.poll()));
			return;
		}
		
		// copy
		Deque<Long> numCopy;
		Queue<Character> operatorCopy;
		
		for(int i=0; i<3; ++i) {
			if((selected & 1<<i)>0) continue;
			
			numCopy = new LinkedList<>();
			operatorCopy = new LinkedList<>();
			numCopy.addAll(num);
			operatorCopy.addAll(operator);

			int size = operatorCopy.size();
			while(--size>=0) {
				// 현재 계산할 연산자와 같다면
				if(operatorIdx[i] == operatorCopy.peek()) {
					operatorCopy.poll();
					numCopy.offerFirst(cal(numCopy.poll(), numCopy.poll(), i));
				}
				else {
					numCopy.offer(numCopy.poll());
					operatorCopy.offer(operatorCopy.poll());
				}
			}
			numCopy.offerLast(numCopy.poll());
			permutation(numCopy, operatorCopy, selected | 1<<i, cnt+1);
		}
	}
	
	long cal(long num1, long num2, int idx) {
		switch (idx) {
		case 0:
			return num1 * num2;
		case 1:
			return num1 - num2;
		case 2:
			return num1 + num2;
		default:
			// do nothing
			return -1;
		}
	}


	public static void main(String[] args) {
		Solution_P_L2_67257_수식최대화 sol = new Solution_P_L2_67257_수식최대화();
		String expression = "100-200*300-500+20";
		long answer = sol.solution(expression);
		System.out.println(answer);
	}
}