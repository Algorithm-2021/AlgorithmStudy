/*
정확성 테스트

테스트 1 〉	통과 (0.04ms, 52.8MB)
테스트 2 〉	통과 (0.04ms, 52.5MB)
테스트 3 〉	통과 (0.04ms, 53MB)
테스트 4 〉	통과 (0.04ms, 52.7MB)
테스트 5 〉	통과 (0.05ms, 52.3MB)
테스트 6 〉	통과 (0.05ms, 55.7MB)
테스트 7 〉	통과 (0.07ms, 52.4MB)
테스트 8 〉	통과 (0.05ms, 51.6MB)
테스트 9 〉	통과 (0.05ms, 51.5MB)
테스트 10 〉	통과 (0.07ms, 54MB)
테스트 11 〉	통과 (0.07ms, 52MB)
테스트 12 〉	통과 (0.08ms, 52.5MB)
테스트 13 〉	통과 (0.10ms, 51.7MB)
테스트 14 〉	통과 (0.10ms, 53.2MB)
테스트 15 〉	통과 (0.22ms, 52.4MB)
테스트 16 〉	통과 (0.21ms, 53.6MB)
테스트 17 〉	통과 (0.38ms, 51.9MB)
테스트 18 〉	통과 (0.31ms, 52.5MB)
테스트 19 〉	통과 (0.40ms, 52MB)
테스트 20 〉	통과 (0.35ms, 53.3MB)

time : 0 Hour 40 Minute

풀이
번호마다 왼손 오른손의 거리를 구하여 계산

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/67256
package algo_5월4주;

public class Solution_P_L1_67256_키패드누르기 {
	public static void main(String[] args) {
		int numbers[] = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";
		System.out.println(solution(numbers, hand));
	}

	public static String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();
		int lefta = 3;
		int leftb = 0;
		int righta = 3;
		int rightb = 2;
		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				sb.append('L');
				lefta = (numbers[i] - 1) / 3;
				leftb = numbers[i] - (lefta * 3) - 1;
			} else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				sb.append('R');
				righta = (numbers[i] - 1) / 3;
				rightb = numbers[i] - (righta * 3) - 1;
			} else {
				int a = (numbers[i] - 1) / 3;
				int b = numbers[i] - (a * 3) - 1;
				if (numbers[i] == 0) {
					a = 3;
					b = 1;
				}
				int leftL = Math.abs(a - lefta) + Math.abs(b - leftb);
				int rightL = Math.abs(a - righta) + Math.abs(b - rightb);
				if (leftL == rightL) {
					if (hand.equals("right")) {
						sb.append('R');
						righta = a;
						rightb = b;
					} else {
						sb.append('L');
						lefta = a;
						leftb = b;
					}
				} else if (leftL > rightL) {
					sb.append('R');
					righta = a;
					rightb = b;
				} else {
					sb.append('L');
					lefta = a;
					leftb = b;
				}
			}
		}
		String answer = sb.toString();
		return answer;
	}
}
