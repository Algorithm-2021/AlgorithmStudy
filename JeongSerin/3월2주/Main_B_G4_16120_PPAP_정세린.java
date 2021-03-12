/*
 * 20504KB
 * 180ms
 * 1H
 * 
 * 문자열 길이가 4이하일 경우 미리 처리 후 시작.
 * 1. 문자열의 길이는 3의배수 + 1이어야 함
 * 2. PP로 시작해서 AP로끝나야함
 * 3. A는 연속으로 나올 수 없음
 * 4. A앞에는 최소 2개 이상의 P가 있어야 한다.
 * 5. A의 개수는 len / 3의 몫
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_G4_16120_PPAP_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		
		// P또는 PPAP를 입력받을 경우
		if (str.equals("P") || str.equals("PPAP")) {
			System.out.println("PPAP");
			return;
		}else if (len <= 4) { // 그 외 문자열 길이가 4이하인 경우
			System.out.println("NP");
			return;
		} 
		
		// 1. 문자열의 길이는 3의배수 + 1이어야 함
		if ((len - 1) % 3 != 0) {
			System.out.println("NP");
			return;
		}
		
		// 2. PP로 시작해서 AP로끝나야함
		if (!(str.substring(0, 2).equals("PP") && str.substring(len - 2).equals("AP"))) {
			System.out.println("NP");
			return;
		}
		
		int cntA = 0;
		int cntP = 0;
		boolean checkDoubleA = false;
		for (int i = 0; i < len; i++) {
			if (str.charAt(i) == 'A') {
				cntA++;
				// 3. A는 연속으로 나올 수 없음
				if (checkDoubleA) { // 연속으로 A가 나옴
					System.out.println("NP");
					return;
				}
				// 4. A앞에는 최소 2개 이상의 P가 있어야 한다.
				cntP -= 2;
				if (cntP < 0) {
					System.out.println("NP");
					return;
				}
				checkDoubleA = true;
			}else {
				cntP++;
				checkDoubleA = false;
			}
		}
		// 5. A의 개수는 len / 3의 몫
		if (cntA != len/3) {
			System.out.println("NP");
			return;
		}
		
		System.out.println("PPAP");
	}

}
