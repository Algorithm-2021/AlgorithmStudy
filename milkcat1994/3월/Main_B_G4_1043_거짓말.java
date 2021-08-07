import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -거짓말-
 * 
 * 풀이시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/1043
public class Main_B_G4_1043_거짓말 {
	static int N,M;
	
	static int[] parents;
	static boolean[] truePersons;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initParent(N);
		truePersons = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		int truePersonCnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<truePersonCnt; ++i) {
			int truePerson = Integer.parseInt(st.nextToken());
			truePersons[truePerson] = true;
		}
		
		List<ArrayList<Integer>> list = new ArrayList<>();
		int firstPerson;
		for(int i=0; i<M; ++i) {
			list.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine(), " ");
			int partyPersonCnt = Integer.parseInt(st.nextToken());
			
			if(partyPersonCnt > 0) {
				firstPerson = Integer.parseInt(st.nextToken());
				list.get(i).add(firstPerson);
				
				int nextPerson;
				while(st.hasMoreTokens()) {
					nextPerson = Integer.parseInt(st.nextToken());
					list.get(i).add(nextPerson);
					union(firstPerson, nextPerson);
				}
			}
		}
		
		int answer = M;
		for(int i=0; i<M; ++i) {
			for(Integer person : list.get(i)) {
				if(truePersons[getParent(person)]) {
					answer--;
					break;
				}
			}
		}
		
		System.out.print(answer);
	}
	
	static void initParent(int n) {
		parents = new int[n+1];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		truePersons[pa] = truePersons[pb] = (truePersons[pa] || truePersons[pb]);
		return true;
	}
}
