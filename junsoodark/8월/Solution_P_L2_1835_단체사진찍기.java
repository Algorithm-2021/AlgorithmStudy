/*
정확성 테스트

테스트 1 〉	통과 (6343.14ms, 361MB)

time : 1 Hour 0 Minute

풀이
넥퍼를 이용하여 모든 순열을 list에 담은뒤 조건을 하나씩 체크하며
맞지 않는 순열을 제외시켜 list에 최종적으로 남은 갯수를 센다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/1835
package algo_8월;

import java.util.ArrayList;
import java.util.Stack;

public class Solution_P_L2_1835_단체사진찍기 {

	public static void main(String[] args) {
		int n = 2;
		String[] data = { "N~F=0", "R~T>2" };
		System.out.println(solution(n, data));
	}

	public static int solution(int n, String[] data) {
		char arr[] = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
		ArrayList<char[]> list = new ArrayList<>();
		do {
			list.add(arr.clone());
		} while (nextPermutation(arr));
		for (int i = 0; i < n; i++) {
			String str = data[i];
			char[] tmp = str.toCharArray();
			char a = tmp[0];
			char b = tmp[2];
			char c = tmp[3];
			int d = tmp[4] - '0';
			int cnt = 0;
			Stack<Integer> s = new Stack<>();
			for (char[] l : list) {
				boolean finda = true;
				boolean findb = true;
				int posa = 0;
				int posb = 0;
				int cnttmp = 0;
				while (finda || findb) {
					if (l[cnttmp] == a) {
						finda = false;
						posa = cnttmp;
					} else if (l[cnttmp] == b) {
						findb = false;
						posb = cnttmp;
					}
					cnttmp++;
				}
				int gap = Math.abs(posa - posb) - 1;
				if (c == '=') {
					if (gap != d) {
						s.push(cnt);
					}
				} else if (c == '<') {
					if (gap >= d) {
						s.push(cnt);
					}
				} else {
					if (gap <= d) {
						s.push(cnt);
					}
				}
				cnt++;
			}
			while (!s.isEmpty()) {
				int p = s.pop();
				list.remove(p);
			}
			if (list.size() == 0) {
				return 0;
			}
		}
		return list.size();
	}

	public static boolean nextPermutation(char p[]) {
		int n = p.length;
		int i = n - 1;
		while (i > 0 && p[i - 1] >= p[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}

		int j = n - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		char tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = n - 1;
		while (k > i) {
			tmp = p[k];
			p[k] = p[i];
			p[i] = tmp;
			k--;
			i++;
		}

		return true;

	}
}
