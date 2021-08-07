import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -전국시대-
 * 1. union-find 응용 문제
 * 
 * 메모리 : 43236KB
 * 시간 : 696ms
 * 풀이 시간 : 45M
 */

//출처 : https://www.acmicpc.net/problem/15809
public class Main_B_G5_15809_전국시대 {
	static int N,M;
	
	static int[] parents, army;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static String solve() {
		StringBuilder sb = new StringBuilder();
		List<Integer> armyCntList = new ArrayList<>();
		for(int i=1; i<=N; ++i) {
			if(parents[i] == -1) {
				armyCntList.add(army[i]);
			}
		}
		
		Collections.sort(armyCntList);
		sb.append(armyCntList.size()+"\n");
		for(Integer cnt : armyCntList) {
			sb.append(cnt+" ");
		}
		sb.setLength(sb.length()-1);
		
		return sb.toString();
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		army = new int[N+1];
		initParents(N);
		
		int armyCnt;
		for(int i=1; i<=N; ++i) {
			armyCnt = Integer.parseInt(br.readLine());
			army[i] = armyCnt;
		}
		
		int flag, countryA, countryB;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			flag = Integer.parseInt(st.nextToken());
			countryA = Integer.parseInt(st.nextToken());
			countryB = Integer.parseInt(st.nextToken());
			
			switch (flag) {
			// Alliance
			case 1:
				union(countryA, countryB);
				break;
				
			// Fight
			case 2:
				fight(countryA, countryB);
				break;
			default:
				// do Nothing
				break;
			}
		}
	}
	
	static void fight(int countryA, int countryB) {
		int ca = getParents(countryA);
		int cb = getParents(countryB);
		
		int armyCntA = army[ca];
		int armyCntB = army[cb];
		
		if(armyCntA < armyCntB) {
			unionTarget(cb, ca);
		}
		else if(armyCntA > armyCntB) {
			unionTarget(ca, cb);
		}
		// same armyCnt
		else {
			destoryCountry(ca, cb);
		}
	}

	static void unionTarget(int parentsCountry, int childCountry) {
		parents[childCountry] = parentsCountry;
		army[parentsCountry] = army[parentsCountry] - army[childCountry];
	}
	
	static void destoryCountry(int countryA, int countryB) {
		// destroy country
		parents[countryA] = -2;
		parents[countryB] = -2;
		
		army[countryA] = 0;
		army[countryB] = 0;
	}
	
	static void initParents(int n) {
		parents = new int[n+1];
		Arrays.fill(parents, -1);
	}
	
	static int getParents(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = getParents(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParents(a);
		int pb = getParents(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		army[pb] += army[pa];
		return true;
	}
}
