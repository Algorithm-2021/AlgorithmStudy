public class _1_5_백준19238_스타트택시_김준원 {
	static int[][] map;
	static int[] visit;
	static int n, m, t, pCnt, crntPassenger;
	static long fuel;
	static Q r, f;

	public static void main(String[] args) throws Exception {
		n = m = pCnt = crntPassenger = 0;
		fuel = 0;
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			n = n * 10 + t - '0';
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			m = m * 10 + t - '0';
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				fuel = fuel * 10 + t - '0';
		map = new int[n][n];
		visit = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (System.in.read() == '1')
					map[i][j] = 100000;
				t = System.in.read();
			}
			if (t == '\r')
				System.in.read();
		}
		int x, y, move;
		x = y = 0;
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			x = x * 10 + t - '0';
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				y = y * 10 + t - '0';
		r = f = new Q(x - 1, y - 1, 0);
		visit[x - 1] |= (1 << (y - 1));
		for (int i = 0; i < m; i++) {
			x = y = 0;
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				x = x * 10 + t - '0';
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				y = y * 10 + t - '0';
			map[x - 1][y - 1] = ++pCnt;
			x = y = 0;
			for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
				x = x * 10 + t - '0';
			for (t = System.in.read(); t != '\n'; t = System.in.read())
				if ('0' <= t && t <= '9')
					y = y * 10 + t - '0';
			map[x - 1][y - 1] = -pCnt;
		}
		crntPassenger = map[r.x][r.y];
		while (r != null) {
			x = r.x;
			y = r.y;
			move = r.move;
			r = r.next;
			if (fuel - move <= 0) {
				fuel = -1;
				break;
			}
			if (!go(x - 1, y, move))
				if (!go(x, y - 1, move))
					if (!go(x + 1, y, move))
						go(x, y + 1, move);
			if (pCnt == 0)
				break;
		}
		if (pCnt > 0)
			fuel = -1;
		System.out.print(fuel);
	}

	static boolean go(int x, int y, int move) {
		if (x >= 0 && y >= 0 && x < n && y < n && map[x][y] != 100000 && (visit[x] & (1 << y)) == 0) {
			move++;
			visit[x] |= (1 << y);
			if (map[x][y] > 0 && crntPassenger == 0) {
				fuel -= move;
				move = 0;
				crntPassenger = map[x][y];
				map[x][y] = 0;
				for (int i = 0; i < n; visit[i++] = 0)
					;
				r = f = new Q(x, y, move);
				return true;
			} else if (crntPassenger != 0 && map[x][y] == -crntPassenger) {
				pCnt--;
				fuel += move;
				move = 0;
				crntPassenger = 0;
				map[x][y] = 0;
				for (int i = 0; i < n; visit[i++] = 0)
					;
				r = f = new Q(x, y, move);
				return true;
			} else {
				if (r == null)
					r = f = new Q(x, y, move);
				else
					f = f.next = new Q(x, y, move);
			}
		}
		return false;
	}

	static class Q {
		int x, y, move;
		Q next = null;

		public Q(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
}
