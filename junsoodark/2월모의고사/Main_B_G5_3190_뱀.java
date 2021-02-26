/*
 * memory : 14540 KB
 * time : 108 ms
 * 
 * solve time : 2 Hour 0 Minute
 * 
 * 풀이
 * 시뮬레이션
 * 
 * 
 * 
 * 
 */
package algo_2월모의고사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_3190_뱀 {
	static int N, K, L;
	static int res = 0;
	static int out = 0;
	static boolean end = false;
	static int arr[][];
	// 1은 뱀, 2는 사과, 0은 빈칸
	static int di[] = { 0, 1, 0, -1 }, dj[] = { 1, 0, -1, 0 };
	static int dir = 0;// 초기 방향값
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a - 1][b - 1] = 2;
		}
		q.offer(0);
		arr[0][0] = 1;
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (end) {
				continue;
			}
			int a = Integer.parseInt(st.nextToken());
			int time = a - res;
			String b = st.nextToken();
			for (int j = 0; j < time; j++) {
				if (end) {
					break;
				}
				int size = q.size();
				boolean apple = false;
				for (int k = 0; k < size; k++) {
					int tmp = q.poll();
					int y = tmp / 1000;
					int x = tmp % 1000;
					if (k == 0) {// 머리
						if (y + di[dir] < 0 || y + di[dir] >= N || x + dj[dir] < 0 || x + dj[dir] >= N) {// 벽에 닿은경우
							end = true;
							res++;
							out = res;
//							System.out.println("벽");
							break;
						} else {
							if (arr[y + di[dir]][x + dj[dir]] == 1) {
								end = true;
//								System.out.println("꼬리잡기");
								res++;
								out = res;
								break;
							}
							if (arr[y + di[dir]][x + dj[dir]] == 2) {// 사과를 먹었을때
								apple = true;
//								System.out.println((y + di[dir]) + " " + (x + dj[dir]));
								arr[y + di[dir]][x + dj[dir]] = 1;
								q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
								q.offer(y * 1000 + x);
								continue;
							}
							if (size > 1) {
//								System.out.println((y + di[dir]) + " " + (x + dj[dir]));
								arr[y + di[dir]][x + dj[dir]] = 1;
								q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
								q.offer(y * 1000 + x);
								continue;
							}
//							System.out.println((y + di[dir]) + " " + (x + dj[dir]));
							arr[y][x] = 0;
							arr[y + di[dir]][x + dj[dir]] = 1;
							q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
							continue;
						}
					} else if (k != 0 && k == (size - 1)) {// 꼬리
						if (apple) {
							q.offer(tmp);
						} else {
							arr[y][x] = 0;
						}
						continue;
					} else {// 몸통
						q.offer(tmp);
					}
				}
				res++;
			}
			if (b.equals("D")) {
				dir = (dir + 1) % 4;
			} else {
				if (dir == 0) {
					dir = 3;
				} else {
					dir = dir - 1;
				}
			}
		}
		while (!end) {
			if (end) {
				break;
			}
			int size = q.size();
			boolean apple = false;
			for (int k = 0; k < size; k++) {
				int tmp = q.poll();
				int y = tmp / 1000;
				int x = tmp % 1000;
				if (k == 0) {// 머리
					if (y + di[dir] < 0 || y + di[dir] >= N || x + dj[dir] < 0 || x + dj[dir] >= N) {// 벽에 닿은경우
						end = true;
						res++;
						out = res;
//						System.out.println("벽");
						break;
					} else {
						if (arr[y + di[dir]][x + dj[dir]] == 1) {
							end = true;
//							System.out.println("꼬리잡기");
							res++;
							out = res;
							break;
						}
						if (arr[y + di[dir]][x + dj[dir]] == 2) {// 사과를 먹었을때
							apple = true;
//							System.out.println((y + di[dir]) + " " + (x + dj[dir]));
							arr[y + di[dir]][x + dj[dir]] = 1;
							q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
							q.offer(y * 1000 + x);
							continue;
						}
						if (size > 1) {
//							System.out.println((y + di[dir]) + " " + (x + dj[dir]));
							arr[y + di[dir]][x + dj[dir]] = 1;
							q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
							q.offer(y * 1000 + x);
							continue;
						}
//						System.out.println((y + di[dir]) + " " + (x + dj[dir]));
						arr[y][x] = 0;
						arr[y + di[dir]][x + dj[dir]] = 1;
						q.offer(((y + di[dir]) * 1000) + (x + dj[dir]));
						continue;
					}
				} else if (k != 0 && k == (size - 1)) {// 꼬리
					if (apple) {
						q.offer(tmp);
					} else {
						arr[y][x] = 0;
					}
					continue;
				} else {// 몸통
					q.offer(tmp);
				}
			}
			res++;
		}
		System.out.println(out);
	}
}
