/*
정확성 테스트

테스트 1 〉	통과 (0.17ms, 51.6MB)
테스트 2 〉	통과 (0.23ms, 52.6MB)
테스트 3 〉	통과 (0.16ms, 52.4MB)
테스트 4 〉	통과 (0.19ms, 52.6MB)
테스트 5 〉	통과 (0.23ms, 52.1MB)
테스트 6 〉	통과 (0.20ms, 52.3MB)
테스트 7 〉	통과 (0.19ms, 52.1MB)
테스트 8 〉	통과 (0.16ms, 53.5MB)
테스트 9 〉	통과 (0.18ms, 51.6MB)
테스트 10 〉	통과 (1.25ms, 52.6MB)
테스트 11 〉	통과 (0.21ms, 52.2MB)
테스트 12 〉	통과 (0.17ms, 53MB)
테스트 13 〉	통과 (0.18ms, 52.4MB)
테스트 14 〉	통과 (1.94ms, 52.4MB)
테스트 15 〉	통과 (0.19ms, 52.4MB)
테스트 16 〉	통과 (0.22ms, 54.5MB)
테스트 17 〉	통과 (0.17ms, 52.6MB)
테스트 18 〉	통과 (0.14ms, 51.9MB)
테스트 19 〉	통과 (0.24ms, 52.3MB)
테스트 20 〉	통과 (0.27ms, 53MB)

time : 0 Hour 10 Minute

풀이
시뮬레이션

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12981
package algo_6월3주;

import java.util.Arrays;
import java.util.HashSet;

public class Solution_P_L2_12981_영어끝말잇기 {
	public static void main(String[] args) {
		int n = 5;
		String words[] = { "hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure",
				"establish", "hang", "gather", "refer", "reference", "estimate", "executive" };
		System.out.println(Arrays.toString(solution(n, words)));
	}

	public static int[] solution(int n, String[] words) {
		HashSet<String> hs = new HashSet<>();
		int len = words.length;
		int pos = 1;
		char arr[] = words[0].toCharArray();
		char before = arr[arr.length - 1];
		hs.add(words[0]);
		while (pos < len) {
			if (!hs.add(words[pos])) {
				break;
			}
			char tmpArr[] = words[pos].toCharArray();
			if (tmpArr[0] != before) {
				break;
			}
			before = tmpArr[tmpArr.length - 1];
			pos++;
		}
		int[] answer = new int[2];
		if (pos == len) {
			answer[0] = 0;
			answer[1] = 0;
		} else {
			answer[0] = (pos % n) + 1;
			answer[1] = (pos / n) + 1;
		}
		return answer;
	}

}
