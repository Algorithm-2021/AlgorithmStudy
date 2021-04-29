package algo_4월4주;

import java.util.*;

public class Solution_P_L2_42890_후보키 {
	public static int solution(String[][] relation) {
		int answer = 0;
		int r = relation.length;
		int c = relation[0].length;
//		for (int i = 0; i < r; i++) {
//			System.out.println(Arrays.toString(relation[i]));
//		}
		boolean unique[][] = new boolean[c][r];
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < c; i++) {
			unique[i][0] = true;
			for (int j = 1; j < r; j++) {
				boolean cur = true;
				for (int k = 0; k < j; k++) {
					if (relation[k][i].equals(relation[j][i])) {
						cur = false;
						unique[i][k] = false;
					}
				}
				unique[i][j] = cur;
			}
		}
		for (int i = 1; i < (1 << c); i++) {
			boolean result[] = new boolean[r];
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if ((i & (1 << k)) > 0) {
						result[j] = result[j] | unique[k][j];
					}
				}
			}
			boolean res = true;
			for (int j = 0; j < r; j++) {
				res = res & result[j];
			}
			if (res && check(ans, i)) {
				ans.add(i);
			}
		}
//		for (int i = 0; i < c; i++) {
//			System.out.println(Arrays.toString(unique[i]));
//		}
		for (Integer integer : ans) {
			System.out.println(integer);
		}
		return ans.size();
	}

	public static boolean check(List<Integer> ans, int now) { // 최소성검사
		for (int i = 0; i < ans.size(); i++) {
			if ((ans.get(i) & now) == ans.get(i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str[][] = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "1" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		System.out.println(solution(str));
	}
}
