/*
 * -컬러볼-
 * 시간초과
 * 메모리 :
 * 시간 : 
 * 풀이 시간 : 3H 30min
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_B_G3_10800_컬러볼 {
	static int N, list[][], sumlist[],sumcolor[],sumAll,samesizecolor[],samesizesum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N][3];
		sumlist = new int[N];
		sumcolor = new int[200001];
		samesizecolor = new int[200001];
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
		    		return o1[2] - o2[2]; //무게기준
		    }
		});
//		for (int i = 0; i < list.length; i++) {
//			for (int j = 0; j < list[i].length; j++) {
//				System.out.print(list[i][j]+" ");
//			}
//			System.out.println();
//		}
		sumlist[list[0][0]]=0; //가장 무게가 작은 공 
		sumAll =list[0][2]; // 가장 무게가 작은 공 더해주기
		sumcolor[list[0][1]] = list[0][2]; // 가장 무게가 작은공의 컬러에 무게 더해주기
		samesizecolor[list[0][1]] = list[0][2];
		samesizesum = list[0][2];
		for (int i = 1; i < N; i++) {
			if(list[i-1][2]==list[i][2]) {//앞의 공과 무게가 같을경우
				sumlist[list[i][0]] = sumAll - sumcolor[list[i][1]] - samesizesum +samesizecolor[list[i][1]];
				samesizecolor[list[i][1]] = samesizecolor[list[i][1]] +list[i][2];
				samesizesum = samesizesum + list[i][2];
			}else {//무게가 다를경우
				Arrays.fill(samesizecolor, 0);
				samesizesum =0;
				sumlist[list[i][0]] = sumAll - sumcolor[list[i][1]];
				samesizecolor[list[i][1]] = list[i][2];
				samesizesum = list[i][2];
			}
			sumAll = sumAll+list[i][2];
			sumcolor[list[i][1]] = sumcolor[list[i][1]]+list[i][2];
		}
		for (int i = 0; i < N; i++) {
			System.out.println(sumlist[i]);
		}
	}
}
