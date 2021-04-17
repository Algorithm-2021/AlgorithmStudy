/*
정확성 테스트
테스트 1 〉	통과 (0.07ms, 52.4MB)
테스트 2 〉	통과 (0.06ms, 54.3MB)
테스트 3 〉	통과 (0.06ms, 52.1MB)
테스트 4 〉	통과 (0.08ms, 52MB)
테스트 5 〉	통과 (0.08ms, 52.5MB)
테스트 6 〉	통과 (0.06ms, 52.8MB)
테스트 7 〉	통과 (0.06ms, 52.1MB)
테스트 8 〉	통과 (0.04ms, 52.7MB)
테스트 9 〉	통과 (0.05ms, 53.8MB)
테스트 10 〉	통과 (0.04ms, 51.7MB)
테스트 11 〉	통과 (0.07ms, 52.1MB)
테스트 12 〉	통과 (0.06ms, 51.9MB)
테스트 13 〉	통과 (0.06ms, 52.1MB)
테스트 14 〉	통과 (0.07ms, 52.5MB)
테스트 15 〉	통과 (0.10ms, 52.8MB)
테스트 16 〉	통과 (0.04ms, 52.5MB)

time : 0 Hour 30 Minute

풀이
문자열을 char 배열로 바꾸고 맨처음과 ' '일 경우에서 알파벳이면 대문자로 바꿔준다.

*/

package algo_4월2주;

public class Solution_P_L2_12951_JadenCase문자열만들기 {
	public static void main(String[] args) {
		char a = 'a';
		char z = 'z';
		char A = 'A';
		char Z = 'Z';
		System.out.println((int) a);
		System.out.println((int) z);
		System.out.println((int) A);
		System.out.println((int) Z);
		String out = solution("3people unFollowed me");
		System.out.println(out);
	}

	public static String solution(String s) {
		char arr[] = s.toLowerCase().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				if ((int) arr[0] >= 97 && (int) arr[0] <= 122) {
					arr[0] = (char) (arr[0] - 32);
				}
			} else {
				if (arr[i - 1] == ' ') {
					if ((int) arr[i] >= 97 && (int) arr[i] <= 122) {
						arr[i] = (char) (arr[i] - 32);
					}
				} else {
					continue;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}
}
