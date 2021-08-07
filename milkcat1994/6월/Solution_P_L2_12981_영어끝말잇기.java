import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * -영어 끝말잇기-
 * Set으로 중복판단
 * 끝글자, 첫글자 다르다면 해당 위치 따라서 결과 출력
 * 
 * 테스트 14 〉	통과 (0.04ms, 51.9MB)
 * 테스트 15 〉	통과 (0.06ms, 52.2MB)
 * 테스트 16 〉	통과 (0.03ms, 52.8MB)
 * 테스트 17 〉	통과 (0.03ms, 52.2MB)
 * 테스트 18 〉	통과 (0.04ms, 52.8MB)
 * 테스트 19 〉	통과 (0.07ms, 52.3MB)
 * 테스트 20 〉	통과 (0.09ms, 52.9MB)
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12981
public class Solution_P_L2_12981_영어끝말잇기 {
	
	public int[] solution(int n, String[] words) {
		int[] answer = {0, 0};
		
		int wordSize = words.length;
		Set<String> set = new HashSet<String>();
		String str = words[0];
		char tc = str.charAt(str.length()-1);
		set.add(str);
		
		for(int i=1; i<wordSize; ++i) {
			str = words[i];
			if(tc != words[i].charAt(0) || set.contains(str)) {
				answer = new int[] {(i%n)+1, i/n+1};
				break;
			}
			
			set.add(str);
			tc = str.charAt(str.length()-1);
		}
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_12981_영어끝말잇기 sol = new Solution_P_L2_12981_영어끝말잇기();
		int n=3;
		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		int[] answer = sol.solution(n, words);
		System.out.println(Arrays.toString(answer));
	}
}
