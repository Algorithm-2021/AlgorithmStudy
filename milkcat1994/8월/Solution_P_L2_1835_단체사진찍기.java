/*
 * -단체사진 찍기-
 * 1. perm 이용하여 모든 경우의 수 탐색
 * 2. 조건에 따라 처리
 * 
 * * 마지막 != 를 == 로 써서 에러 검수에 오래걸림
 * 
 * 풀이시간 : 40m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1835
public class Solution_P_L2_1835_단체사진찍기 {
	String member = "ACFJMNRT";
	String[] data;
	public int solution(int n, String[] data) {
		StringBuilder sb = new StringBuilder();
		this.data = data;
		
		return permu(sb, 0, 0, member.length());
	}
	
	public int permu(StringBuilder sb, int flag, int cnt, int lastIdx) {
		if(cnt == lastIdx) {
			return check(sb.toString()) ? 1 : 0;
		}
		
		int ans = 0;
		for(int idx=0; idx<lastIdx; ++idx) {
			if((flag & 1<<idx) > 0) continue;
			ans += permu(sb.append(member.charAt(idx)), flag | 1<<idx, cnt+1, lastIdx);
			sb.setLength(sb.length()-1);
		}
		
		return ans;
	}
	
	public boolean check(String str) {
		int idx1, idx2;
		for(String condition : data) {
			idx1 = str.indexOf(condition.charAt(0));
			idx2 = str.indexOf(condition.charAt(2));
			
			switch (condition.charAt(3)) {
			case '<':
				if(Math.abs(idx1-idx2)-1 >= (condition.charAt(4)-'0'))
					return false;
				break;
			case '>':
				if(Math.abs(idx1-idx2)-1 <= (condition.charAt(4)-'0'))
					return false;
				break;
			case '=':
				if(Math.abs(idx1-idx2)-1 != (condition.charAt(4)-'0'))
					return false;
				break;
			default:
				// do Nothing
				break;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution_P_L2_1835_단체사진찍기 sol = new Solution_P_L2_1835_단체사진찍기();
		int n = 2;
		String[] data = {"N~F=0", "R~T>2"};
		int answer = sol.solution(n, data);
		System.out.println(answer);
	}
}