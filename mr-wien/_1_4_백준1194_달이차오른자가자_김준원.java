import java.util.Arrays;
import java.util.Comparator;

/*
 * 사용 메모리 : 41328KB
 * 코드 동작시간 : 864ms
 * 풀이에 걸린 시간 : 4h ..
 */
public class _1_4_백준1194_달이차오른자가자_김준원 {
	public static void main(String[] args) throws Exception {
		int n = 0, t;
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				n = n * 10 + t - '0';
		int[][] a = new int[n][3];
		int[] colorSum = new int[n + 1];
		int[] res = new int[n];
		int maxSum = 0;
		StringBuilder txt = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				a[i][0] = a[i][0] * 10 + t - '0';
			for (t = System.in.read(); t != '\n'; t = System.in.read())
				if ('0' <= t && t <= '9')
					a[i][1] = a[i][1] * 10 + t - '0';
			a[i][2] = i;
		}
		Arrays.sort(a, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
			}
		});
		for (int i = 0, tmp = 0, tmpColorCnt = 0; i < n;) {
			tmp = maxSum;
			do {
				res[a[i][2]] = tmp - colorSum[a[i][0]];
				maxSum += a[i][1];
				tmpColorCnt += a[i][1];
				if (i + 1 < n && (a[i + 1][1] != a[i][1] || a[i + 1][0] != a[i][0])) {
					colorSum[a[i][0]] += tmpColorCnt;
					tmpColorCnt = 0;
				}
				i++;
			} while (i < n && a[i - 1][1] == a[i][1]);
		}
		for (int i = 0; i < n; i++) {
			txt.append(res[i]).append('\n');
		}
		System.out.print(txt);
	}
}