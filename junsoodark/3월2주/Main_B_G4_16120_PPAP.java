/*
 * memory : 22452 KB
 * time : 164 ms
 * 
 * solve time : 1 Hour 0 Minute
 * 
 * 풀이
 * P의 갯수를 세면서 A가 나올때 뒤에 P 가 나오는지 확인하여 이전에 P의 갯수를 하나 감소시킨다.
 * 
 * 
 * 
 * 
 */

package algo_3월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_G4_16120_PPAP {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		int pNum = 0;
		if (arr[0] == 'P' && arr.length == 1) {
			System.out.println("PPAP");
			return;
		}
		if (arr[0] == 'A') {
			System.out.println("NP");
			return;
		}
		if (arr.length < 4) {
			System.out.println("NP");
			return;
		}
		if (arr[1] == 'A') {
			System.out.println("NP");
			return;
		}
		if (arr.length % 3 != 1) {
			System.out.println("NP");
			return;
		}
		pNum = 2;
		for (int i = 2; i < arr.length; i++) {
			if (arr[i] == 'P') {
				pNum++;
				continue;
			}
			if (arr[i] == 'A') {
				if (i + 1 >= arr.length) {
					System.out.println("NP");
					return;
				}
				if (arr[i + 1] == 'A') {
					System.out.println("NP");
					return;
				} else if (pNum < 2) {
					System.out.println("NP");
					return;
				} else {
					pNum--;
					i++;
				}
			}
		}
		if (pNum == 1) {
			System.out.println("PPAP");
		} else {
			System.out.println("NP");
		}
	}
}
