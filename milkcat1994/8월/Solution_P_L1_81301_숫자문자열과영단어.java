/*
 * -숫자 문자열과 영단어-
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/81301
public class Solution_P_L1_81301_숫자문자열과영단어 {
	
	public int solution(String s) {
		String[] docs = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		for(int i=0; i<10; ++i)
			s = s.replaceAll(docs[i], new Integer(i).toString());
		
		return Integer.parseInt(s);
	}

	public static void main(String[] args) {
		Solution_P_L1_81301_숫자문자열과영단어 sol = new Solution_P_L1_81301_숫자문자열과영단어();
		String s = "one4seveneight";
		int answer = sol.solution(s);
		System.out.println(answer);
	}
}