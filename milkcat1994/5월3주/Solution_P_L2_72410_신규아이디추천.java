/*
 * -신규아이디추천-
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72410
public class Solution_P_L2_72410_신규아이디추천 {
	public String solution(String new_id) {
		String answer=new_id.toLowerCase();
		answer = answer.replaceAll("[^a-z\\\\d\\\\-\\\\_\\\\.]", "")
				.replaceAll("\\.+", ".")
				.replaceAll("^\\.|\\.$", "");
		answer = answer.length() == 0 ? "a" : answer;
		answer = answer.length() >= 16
				? answer.substring(0, 15).replaceAll("\\.$", "")
						: answer;
		while(answer.length() <= 2)
			answer += answer.charAt(answer.length()-1);
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L2_72410_신규아이디추천 sol = new Solution_P_L2_72410_신규아이디추천();
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		String answer = sol.solution(new_id);
		System.out.println(answer);
	}
}