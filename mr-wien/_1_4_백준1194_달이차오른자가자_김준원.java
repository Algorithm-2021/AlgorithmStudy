/*
 * 사용 메모리 : 12968 KB
 * 코드 동작시간 : 96 ms
 * 풀이에 걸린 시간 : 2 h
 */
public class _1_4_백준1194_달이차오른자가자_김준원 {
	static int n = 0, m = 0;
	static char[][] a;
	static long[][] visit;
	static Q r, f;
	static int res = -1;

	public static void main(String[] args) throws Exception {
		int t;
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			n = n * 10 + t - '0';
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				m = m * 10 + t - '0';
		a = new char[n][m];
		visit = new long[n][128];
		r = f = null;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = (char) System.in.read();
				if (a[i][j] == '0') {
					r = f = new Q(i, j, 0, 0);
					visit[i][0] |= (1L << j);
				}
			}
			if (System.in.read() == '\r')
				System.in.read();
		}
		int x, y, cnt, key;
		while (r != null) {
			x = r.x;
			y = r.y;
			cnt = r.cnt;
			key = r.key;
			r = r.next;
			move(x - 1, y, cnt + 1, key);
			move(x + 1, y, cnt + 1, key);
			move(x, y - 1, cnt + 1, key);
			move(x, y + 1, cnt + 1, key);
			if (res != -1)
				break;
		}
		System.out.print(res);
	}

	static void move(int x, int y, int cnt, int key) {
		if (x >= 0 && x < n && y >= 0 && y < m && ((visit[x][key] & (1L << y)) == 0)) {
			switch (a[x][y]) {
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
				if ((key & (1 << (a[x][y] - 'A'))) != 0) {
					visit[x][key] |= (1L << y);
					if (r == null)
						r = f = new Q(x, y, cnt, key);
					else
						f = f.next = new Q(x, y, cnt, key);
				}
				break;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
				key |= 1 << (a[x][y] - 'a');
			case '.':
			case '0':
				visit[x][key] |= (1L << y);
				if (r == null)
					r = f = new Q(x, y, cnt, key);
				else
					f = f.next = new Q(x, y, cnt, key);
				break;
			case '1':
				res = cnt;
				break;
			default:
				break;
			}

		}
	}

	static class Q {
		int x, y, cnt, key;
		Q next = null;

		public Q(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
	}
}