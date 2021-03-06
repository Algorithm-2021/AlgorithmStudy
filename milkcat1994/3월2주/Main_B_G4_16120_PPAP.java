import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -PPAP-
 * 
 * 메모리 : 23648KB
 * 시간 : 424ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/16120
public class Main_B_G4_16120_PPAP {
	static String originStr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static String solve() {
		int patternLength = "PPAP".length();
		int originStrLength = originStr.length();
		for(int i=0; i<originStrLength; ++i) {
			sb.append(originStr.charAt(i));
			if(sb.length() >= patternLength) {
				isPPAP();
			}
		}
		
		return sb.toString().equals("P") ? "PPAP" : "NP";
	}
	
	static void isPPAP() {
		int idx = sb.length();
		if(sb.charAt(idx-4) == 'P' && sb.charAt(idx-3) == 'P' && sb.charAt(idx-2) == 'A' && sb.charAt(idx-1) == 'P') {
			sb.setLength(sb.length()-4);
			sb.append('P');
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		originStr = br.readLine();
	}
}
