/*
 
테스트 1 〉	통과 (0.03ms, 52.8MB)
테스트 2 〉	통과 (0.06ms, 53MB)
테스트 3 〉	통과 (0.39ms, 52.4MB)
테스트 4 〉	통과 (0.04ms, 53MB)
테스트 5 〉	통과 (0.03ms, 53MB)

time : 0 Hour 20 Minute

풀이
dfs방식으로 각각의 단어를 한글자만 변환해서 탐색하면서 찾으면 횟수를 리턴하여 최소값을 찾는다.

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43163
package algo_3월5주;

public class Solution_P_L3_43163_단어변환 {
	static int min = Integer.MAX_VALUE;
	static boolean visit[];

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String words[] = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(solution(begin, target, words));

	}

	public static int solution(String begin, String target, String[] words) {
		visit = new boolean[words.length];
		dfs(begin, target, words, 0);
		if (min == Integer.MAX_VALUE) {
			return 0;
		}
		return min;
	}

	private static void dfs(String begin, String target, String[] words, int i) {
		if (begin.equals(target)) {
			if (min > i) {
				min = i;
			}
			return;
		}
		for (int j = 0; j < words.length; j++) {
			if (canChange(begin, words[j]) && !visit[j]) {
				visit[j] = true;
				dfs(words[j], target, words, i + 1);
				visit[j] = false;
			}
		}
	}

	public static boolean canChange(String s1, String s2) {
		int len = s1.length();
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				cnt++;
			}
		}
		if (cnt == 1) {
			return true;
		}
		return false;
	}
}
