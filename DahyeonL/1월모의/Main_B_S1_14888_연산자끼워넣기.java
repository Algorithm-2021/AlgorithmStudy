package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S1_14888_연산자끼워넣기 {
static int N, numbers[], operator[],parray[];
static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		operator = new int[4];
		for (int i = 0; i < args.length; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	static void pn(int start_i,int r) { //숫자순열
		if(r==0) {
			print();
			return;
		}
		for (int i = 0; i < N; i++) {
			if(!visit[i]) {
				visit[i]=true;
				parray[N-r] = numbers[i];
				pn(start_i, r-1);
				visit[i]=false;
			}
		}
	}
	static void po() { //연산자순열
		
	}
	
//	int a = Integer.parseInt(st.nextToken());
//	int b = 0;
//	int c = 0;
//	int result =0;
//	for (int i = 0; i <N/2; i++) {
//		b = Integer.parseInt(st.nextToken());
//		c = Integer.parseInt(st.nextToken());
//		result =  op(a, b, c);
//		a = result;
//	}
	static int op(int a, int b, int c) { //연산
		int result =0;
		if(b==0) {
			result = a+c;
		}else if(b==1) {
			result = a-c;
		}else if(b==2) {
			result = a*c;
		}else {
			result = a/c;
		}
		System.out.println(result);
		return result;
	}
	
	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(parray[i]+" ");
		}
		System.out.println();
	
	}
}
