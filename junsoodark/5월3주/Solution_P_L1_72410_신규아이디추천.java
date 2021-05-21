/*
정확성 테스트

테스트 1 〉	통과 (0.11ms, 52.9MB)
테스트 2 〉	통과 (24.19ms, 53.3MB)
테스트 3 〉	통과 (13.43ms, 53.4MB)
테스트 4 〉	통과 (0.37ms, 52.4MB)
테스트 5 〉	통과 (0.39ms, 51.7MB)
테스트 6 〉	통과 (0.14ms, 51.4MB)
테스트 7 〉	통과 (0.18ms, 52.2MB)
테스트 8 〉	통과 (0.42ms, 52.3MB)
테스트 9 〉	통과 (12.19ms, 53.3MB)
테스트 10 〉	통과 (0.13ms, 51.5MB)
테스트 11 〉	통과 (0.17ms, 52.1MB)
테스트 12 〉	통과 (0.64ms, 53.2MB)
테스트 13 〉	통과 (13.11ms, 53.9MB)
테스트 14 〉	통과 (0.38ms, 53.6MB)
테스트 15 〉	통과 (2.64ms, 52.1MB)
테스트 16 〉	통과 (0.78ms, 52.5MB)
테스트 17 〉	통과 (1.79ms, 53MB)
테스트 18 〉	통과 (2.34ms, 52.5MB)
테스트 19 〉	통과 (4.46ms, 53.6MB)
테스트 20 〉	통과 (4.70ms, 52.9MB)
테스트 21 〉	통과 (12.12ms, 52.5MB)
테스트 22 〉	통과 (25.46ms, 52.5MB)
테스트 23 〉	통과 (19.87ms, 53MB)
테스트 24 〉	통과 (2.09ms, 52.5MB)
테스트 25 〉	통과 (5.33ms, 53MB)
테스트 26 〉	통과 (6.26ms, 52.4MB)

time : 1 Hour 0 Minute

풀이
문자열을 잘라서 값을 구하며 가장 작은 값을 출력한다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/72410
package algo_5월3주;

public class Solution_P_L1_72410_신규아이디추천 {
	public static void main(String[] args) {
		String input = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(solution(input));
	}

	public static String solution(String new_id) {
		new_id = toLowerCase(new_id);
		new_id = delete(new_id);
		new_id = delDot(new_id);
		new_id = delFHDot(new_id);
		if (new_id.length() > 16) {
			new_id = new_id.substring(0, 15);
		}
		new_id = delFHDot(new_id);
		while (new_id.length() < 3) {
			new_id = new_id + new_id.charAt(new_id.length() - 1);
		}
		return new_id;
	}

	public static String toLowerCase(String str) {
		return str.toLowerCase();
	}

	public static String delete(String str) {
		String regex = "[^a-z0-9-_.]";
		str = str.replaceAll(regex, "");
		return str;
	}

	public static String delDot(String str) {
		while (str.contains("..")) {
			String regex = "\\.\\.";
			str = str.replaceAll(regex, ".");
		}
		return str;
	}

	public static String delFHDot(String str) {
		if (str.length() == 1) {
			if (str.equals(".")) {
				return "a";
			}
			return str;
		}
		if (str.charAt(0) == '.') {
			str = str.substring(1);
		}
		if (str.length() == 1) {
			if (str.equals(".")) {
				return "a";
			}
			return str;
		}
		if (str.charAt(str.length() - 1) == '.') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
}
