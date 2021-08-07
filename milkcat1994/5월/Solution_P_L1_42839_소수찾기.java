import java.util.HashSet;
import java.util.Set;

/*
 * -소수 찾기-
 * 미리 소수를 구한다면 범위가 1000만이라 모든 60ms가 기본으로 나온다.
 * 최악의 경우로만 생각하지 말 것.
 * 
 * 소수인지 판단을 계속 해나간다면 속도면에서 줄일 수 있다.
 * 
 * 테스트 7 〉	통과 (0.42ms, 53.1MB)
 * 테스트 8 〉	통과 (12.63ms, 55.5MB)
 * 테스트 9 〉	통과 (0.24ms, 53.4MB)
 * 테스트 10 〉	통과 (5.11ms, 53MB)
 * 테스트 11 〉	통과 (1.40ms, 52.3MB)
 * 테스트 12 〉	통과 (1.36ms, 51.9MB)
 * 
 * 풀이시간 : 1H 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42839
public class Solution_P_L1_42839_소수찾기 {
	int numberLength;
	String[] strArr;
	StringBuilder sb = new StringBuilder();
	
	Set<Integer> set = new HashSet<>();
	
	public int solution(String numbers) {
		numberLength = numbers.length();
		
		strArr = numbers.split("");
		
		for(int len=1; len<=numberLength; ++len) {
			permutation(0, len, 0);
		}
		
		return set.size();
	}
	
	void permutation(int selected, int len, int cnt) {
		if(len == cnt) {
			int num = Integer.parseInt(sb.toString());
			if(isPrime(num))
				set.add(num);
			return;
		}
		
		for(int idx=0; idx<numberLength; ++idx) {
			if((1<<idx & selected) > 0) continue;
			
			sb.append(strArr[idx]);
			permutation(selected | 1<<idx, len, cnt+1);
			sb.setLength(sb.length()-1);
		}
	}

	boolean isPrime(int n) {
		if(n <= 1) return false;
		double sq = Math.sqrt(n);
		for(int i=2; i<=sq; ++i) {
			if(n%i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Solution_P_L1_42839_소수찾기 sol = new Solution_P_L1_42839_소수찾기();
		String numbers = "17";
		int answer = sol.solution(numbers);
		System.out.println(answer);
	}
}