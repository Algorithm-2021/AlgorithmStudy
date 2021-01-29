/*
 * 사용 메모리 :  KB
 * 코드 동작시간 :  ms
 * 풀이에 걸린 시간 :  h
 */
public class _1_5_백준5427_불_김준원 {
	static int n = 0, m = 0;
	static char[][] a;
	static boolean[][] visit;
	static Q r, f;
	static int res = -1;

	public static void main(String[] args) throws Exception {
		int t, testcase = 0;
		StringBuilder txt = new StringBuilder();
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				testcase = testcase * 10 + t - '0';
		L: for (int tc = 0; tc < testcase; tc++) {
			n = m = 0;
			res = -1;
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				m = m * 10 + t - '0';
			for (t = System.in.read(); t != '\n'; t = System.in.read())
				if ('0' <= t && t <= '9')
					n = n * 10 + t - '0';
			a = new char[n][m];
			visit = new boolean[n][m];
			r = f = null;
			int cx = 0, cy = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					a[i][j] = (char) System.in.read();
					if (a[i][j] == '@') {
						cx = i;
						cy = j;
						visit[i][j] = true;
						a[i][j] = '.';
					} else if (a[i][j] == '*') {
						if (r == null)
							r = f = new Q(i, j, -1);
						else
							f = f.next = new Q(i, j, -1);
					}
				}
				if (System.in.read() == '\r')
					System.in.read();
			}
			if (cx == 0 || cy == 0 || cx == n - 1 || cy == m - 1) {
				txt.append(1).append('\n');
				continue L;
			}
			if (r == null)
				r = f = new Q(cx, cy, 1);
			else
				f = f.next = new Q(cx, cy, 1);
			int x, y, cnt;
			boolean exit = false;

			while (r != null) {
				x = r.x;
				y = r.y;
				cnt = r.cnt;
				r = r.next;
				if (cnt < 0) {
					fire(x - 1, y, cnt - 1);
					fire(x + 1, y, cnt - 1);
					fire(x, y - 1, cnt - 1);
					fire(x, y + 1, cnt - 1);
				} else {
					exit |= move(x - 1, y, cnt + 1);
					exit |= move(x + 1, y, cnt + 1);
					exit |= move(x, y - 1, cnt + 1);
					exit |= move(x, y + 1, cnt + 1);
					if (exit) {
						res = cnt + 1;
						break;
					}
				}
			}
			txt.append(res == -1 ? "IMPOSSIBLE" : res).append('\n');
		}
		System.out.print(txt);
	}

	static void fire(int x, int y, int cnt) {
		if (x >= 0 && x < n && y >= 0 && y < m) {
			switch (a[x][y]) {
			case '#':
			case '*':
				break;
			default:
				a[x][y] = '*';
				if (r == null)
					r = f = new Q(x, y, cnt);
				else
					f = f.next = new Q(x, y, cnt);
				break;
			}

		}
	}

	static boolean move(int x, int y, int cnt) {
		if (x >= 0 && x < n && y >= 0 && y < m && !visit[x][y]) {
			switch (a[x][y]) {
			case '#':
			case '*':
				break;
			default:
				if (x == 0 || y == 0 || x == n - 1 || y == m - 1)
					return true;
				visit[x][y] = true;
				if (r == null)
					r = f = new Q(x, y, cnt);
				else
					f = f.next = new Q(x, y, cnt);
				break;
			}
		}
		return false;
	}

	static class Q {
		int x, y, cnt;
		Q next = null;

		public Q(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}