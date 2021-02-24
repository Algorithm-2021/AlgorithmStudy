package DP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 메모리 16428kb
 * 시간 112ms
 * 푸는시간 2H
 *
 */
public class Main_B_G5_13549_숨바꼭질3 {
	static int n, k, ans;
	static Queue<Point> q;
	static boolean v[];

	
	static class Point{
		int now, cnt;

		public Point(int now, int cnt) {
			this.now = now;
			this.cnt = cnt;
		} 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());		
		v = new boolean[100001];
		q = new LinkedList<>();
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		search();
		System.out.println(ans);

	}

	 private static void search() {
		q.add(new Point(n, 0));
		v[n] = true;
		while (!q.isEmpty()) {
			Point tmp = q.poll();
			int place = tmp.now;
			int cnt = tmp.cnt;
			if (place == k)	ans=cnt;
			if (place <= 50000 && !v[place * 2]) {
				q.add(new Point(place*2, cnt));
				v[place * 2] = true;				
			}
            if (place > 0 && !v[place - 1]) {
				q.add(new Point(place-1, cnt+1));
				v[place - 1] = true;
			}
			if (place < 100000 && !v[place + 1]) {
				q.add(new Point(place+1, cnt+1));
				v[place + 1] = true;
			}
			
		}
	}
}