package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_B_G3_10800_컬러볼_무게 {
static int N, list[][], sumlist[],sum,count,point[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N][3];
		sumlist = new int[N];
		if(N<2000) {
			point= new int[N+1];
		}
		else {
			point = new int[2001];
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list[i][0] = i; //순서
			list[i][1] = Integer.parseInt(st.nextToken());//색
			list[i][2] = Integer.parseInt(st.nextToken());//무게
		}

		//무게로 정렬
		Arrays.sort(list, new Comparator<int[]>() {
		    @Override
		    public int compare(int[] o1, int[] o2) {
		    		return o2[2] - o1[2]; //무게기준
		    }
		});
//		for (int i = 0; i < list.length; i++) {
//			for (int j = 0; j < list[i].length; j++) {
//				System.out.print(list[i][j]+" ");
//			}
//			System.out.println();
//		}
		count =1;//경계의 갯수
		point[0]=0;
		for (int i = 0; i < N-1; i++) {
			if(list[i][2]!=list[i+1][2]) {
				point[count]=i+1;//값이 달라지는 구간
				count++;
			}
		}
		point[count]=N;
		for (int i = 0; i <count; i++) {
			for (int j = point[i]; j < point[i+1]; j++) {
//				sum(point[i],N);
				sum = 0;
				for (int k = point[i+1]; k < N; k++) {
					if(list[j][1]!=list[k][1]) {//색이 다르다면
						sum =  sum+list[k][2]; //더하기
					}
				}
				sumlist[list[j][0]] = sum;
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(sumlist[i]);
		}
	}

}
