package algo_4월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static int d[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws Exception {
		Queue<fireBall> q = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		box arr[][] = new box[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			q.add(new fireBall(r, c, mass, dir, speed));
//			System.out.println(r + " " + c + " " + mass + " " + speed + " " + dir);
		}
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					arr[j][k] = new box(0, 0, 0, 0, 0);
				}
			}
			while (!q.isEmpty()) {
				fireBall tmp = q.poll();
//				System.out.println("speed" + tmp.speed);
				tmp.r += d[tmp.dir][0] * tmp.speed;
				tmp.c += d[tmp.dir][1] * tmp.speed;
				if (tmp.r < 0) {
					while (tmp.r < 0) {
						tmp.r += N;
					}
				}
				if (tmp.c < 0) {
					while (tmp.c < 0) {
						tmp.c += N;
					}
				}
				if (tmp.r >= N) {
					while (tmp.r >= N) {
						tmp.r -= N;
					}
				}
				if (tmp.c >= N) {
					while (tmp.c >= N) {
						tmp.c -= N;
					}
				}
//				System.out.println(tmp.r + " " + tmp.c);
				arr[tmp.r][tmp.c].cnt++;
				arr[tmp.r][tmp.c].totDir += tmp.dir % 2;
				arr[tmp.r][tmp.c].totMass += tmp.mass;
				arr[tmp.r][tmp.c].totSpeed += tmp.speed;
				arr[tmp.r][tmp.c].oneSpeed += tmp.speed;
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (arr[j][k].cnt >= 2) {
						if (arr[j][k].totMass < 5) {
							continue;
						}
						int tmpMass = arr[j][k].totMass / 5;
						int tmpSpeed = arr[j][k].totSpeed / arr[j][k].cnt;
						int tmpDir = 0;
						if (arr[j][k].totDir == 0 || arr[j][k].totDir == arr[j][k].cnt) {
							tmpDir = 0;
						} else {
							tmpDir = 1;
						}
						for (int l = 0; l < 4; l++) {
							fireBall in = new fireBall(j, k, tmpMass, (2 * l) + tmpDir, tmpSpeed);
							q.add(in);
						}
					} else if (arr[j][k].cnt == 1) {
						fireBall in = new fireBall(j, k, arr[j][k].totMass, arr[j][k].oneSpeed, arr[j][k].totSpeed);
						q.add(in);
					}
				}
			}
		}
		int answer = 0;
//		System.out.println(q.size());
		while (!q.isEmpty()) {
			fireBall out = q.poll();
			answer += out.mass;
		}
		System.out.println(answer);
	}

	public static class box {
		int totMass; // 도착한 파이어볼의 질량합
		int totDir; // 파이어볼이 칸에 도착했을때 방향이 홀수면 +1 짝수면 +0을해서 합이 0이거나 갯수와 똑같으면 퍼지는 방향은 0, 2, 4, 6
					// 아니면 1, 3, 5, 7로 퍼진다.
		int cnt; // 도착한 파이어볼의 갯수
		int totSpeed; // 도착한 파이어볼의 속력의 총합
		int oneSpeed;

		public box(int totMass, int totDir, int cnt, int totSpeed, int oneSpeed) {
			this.totMass = totMass;
			this.totDir = totDir;
			this.cnt = cnt;
			this.totSpeed = totSpeed;
			this.oneSpeed = oneSpeed;
		}
	}

	public static class fireBall {
		int r;// row
		int c;// column
		int mass;// 질량
		int dir;// 방향
		int speed;// 속력

		public fireBall(int r, int c, int mass, int dir, int speed) {
			this.r = r;
			this.c = c;
			this.mass = mass;
			this.dir = dir;
			this.speed = speed;
		}

	}

}
