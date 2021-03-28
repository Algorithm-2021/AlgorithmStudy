/*
 * -단어 변환-
 * 테스트 1 〉	통과 (0.04ms, 52.1MB)
 * 테스트 2 〉	통과 (0.09ms, 52.9MB)
 * 테스트 3 〉	통과 (0.49ms, 52.4MB)
 * 테스트 4 〉	통과 (0.05ms, 52.7MB)
 * 테스트 5 〉	통과 (0.02ms, 51.8MB)
 * 
 * 풀이 시간 : 10M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43163
public class Solution_P_L3_43163_단어변환 {
	int minRes=Integer.MAX_VALUE;
	public int solution(String begin, String target, String[] words) {
		
		if(!isInTarget(target, words))
			return 0;
		
		boolean[] isSelected = new boolean[words.length];
		
		dfs(begin, target, isSelected, words, 0);
		return minRes;
	}
	
	void dfs(String begin, String target, boolean[] isSelected, String[] words, int cnt) {
		if(begin.equals(target)) {
			minRes = Math.min(minRes, cnt);
			return;
		}
		
		for(int i=0; i<words.length; ++i) {
			if(isSelected[i]) continue;
			if(!canChange(begin, words[i])) continue;
			
			isSelected[i] = true;
			dfs(words[i], target, isSelected, words, cnt+1);
			isSelected[i] = false;
		}
	}
	
	boolean canChange(String begin, String word) {
		int same=0;
		for(int i=0; i<word.length(); ++i) {
			if(begin.charAt(i) == word.charAt(i))
				same++;
		}
		return same == word.length()-1 ? true : false;
	}
	
	boolean isInTarget(String target, String[] words) {
		// words에 target이 있는지 확인
		boolean isFind = false;
		for(String str : words) {
			if(str.equals(target)) {
				isFind = true;
				break;
			}
		}
		return isFind;
	}

	public static void main(String[] args) {
		Solution_P_L3_43163_단어변환 sol = new Solution_P_L3_43163_단어변환();
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		
		int answer = sol.solution(begin, target, words);
		System.out.println(answer);
	}
}