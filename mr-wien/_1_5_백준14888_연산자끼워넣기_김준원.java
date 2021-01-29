public class _1_5_백준14888_연산자끼워넣기_김준원 {
	static int n, t, max = 0, min = (1 << 31) - 1;
	static int num[], pmmd[], a[];
	static int visit = 0;

	public static void main(String[] args) throws Exception {
		n = 0;
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				n = n * 10 + t - '0';
		num = new int[n];
		pmmd = new int[n - 1];
		a = new int[n - 1];
		for (int i = 0; i < n; i++)
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				num[i] = num[i] * 10 + t - '0';
		if (t == '\r')
			t = System.in.read();
		for (int i = 1, cnt = 0; i <= 4; i++) {
			int r = 0;
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				r = r * 10 + t - '0';
			for (int j = 0; j < r; j++)
				pmmd[cnt++] = i;
		}
		calc(0);
		System.out.println(max);
		System.out.print(min);
	}

	static void calc(int d) {
		if (d == n - 1) {
			int c = num[0];
			for (int i = 0; i < n - 1; i++) {
				switch (a[i]) {
				case 1:// +
					c += num[i + 1];
					break;
				case 2:// -
					c -= num[i + 1];
					break;
				case 3:// *
					c *= num[i + 1];
					break;
				case 4:///
					c /= num[i + 1];
					break;
				default:
					break;
				}
			}
			max = max < c ? c : max;
			min = min > c ? c : min;
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			if ((visit & (1 << i)) != 0)
				continue;
			visit ^= (1 << i);
			a[d] = pmmd[i];
			calc(d + 1);
			visit ^= (1 << i);
		}
	}
}
