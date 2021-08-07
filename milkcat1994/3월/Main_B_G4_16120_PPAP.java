import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -PPAP-
 * 1. 처음에는 StringBuilder를 이용하여 length를 줄여가는 식으로 풀이하였음.
 * 2. 후에 for문 이용하여 'A'의 나타남 유무에 따른 처리로 풀이하였음.
 * 
 * 메모리 : 20772KB
 * 시간 : 272ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/16120
public class Main_B_G4_16120_PPAP {
	static String originStr;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static String solve() {
		boolean isPossible = true;
		int originStrLength = originStr.length();
		
		int pCnt = 0;
		for(int i=0; i<originStrLength; ++i) {
			switch (originStr.charAt(i)) {
			case 'P':
				pCnt++;
				break;

			case 'A':
				if(pCnt>=2 && i<originStrLength-1 && originStr.charAt(i+1)=='P') {
					pCnt--;
					i++;
				}
				else {
					isPossible = false;
				}
				break;
			default:
				// do Nothing
				break;
			}
		}
		
		return (isPossible && pCnt==1) ? "PPAP" : "NP";
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		originStr = br.readLine();
	}
}
