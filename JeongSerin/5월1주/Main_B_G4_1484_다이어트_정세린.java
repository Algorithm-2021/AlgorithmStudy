/*
 * 11620KB
 * 92ms
 * 40m
 * 완탐
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_G4_1484_다이어트_정세린 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		boolean find = false; // 가능한 현재 몸무게 있는지
		
		for (long now = (long) Math.floor(Math.sqrt(G + 1)); now <= Long.MAX_VALUE; now++) {
			if (2 * now - 1 > G) break; // now^2 - (now-1)^2 > G라면 더이상 확인할 필요 없음
			double before = Math.sqrt(now * now - G); // 기억하는몸무게
			if (Math.floor(before) != before || before == 0) continue; // 자연수인지 확인
			
			System.out.println(now);
			find = true;
		} 
		
		if (!find) System.out.println(-1);
	}

}
