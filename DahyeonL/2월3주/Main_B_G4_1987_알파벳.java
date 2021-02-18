//푼시간 : 40분
//12388	868	
package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1987_알파벳 {
static int R,C,max;
static char[][] map;
static boolean alpabet[];
static int dn[] = {-1,0,1,0}, dm[] = {0,-1,0,1};
public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		alpabet = new boolean[26];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		alpabet[map[0][0]-'A']=true;
		max = Integer.MIN_VALUE;
		DFS(0,0,1);
		System.out.println(max);
	}
	static void DFS(int n,int m,int dep){
		if(max<dep) {
			max = dep;
		}
		int tempN,tempM;
		for (int i = 0; i < 4; i++) {
			tempN = n+dn[i];
			tempM = m+dm[i];
			if(0<=tempN&&tempN<R&&0<=tempM&&tempM<C) {
				if(!alpabet[map[tempN][tempM]-'A']) {
					alpabet[map[tempN][tempM]-'A']=true;
					DFS(tempN,tempM,dep+1);
					alpabet[map[tempN][tempM]-'A']=false;
				}
			}
		}
	}

}
