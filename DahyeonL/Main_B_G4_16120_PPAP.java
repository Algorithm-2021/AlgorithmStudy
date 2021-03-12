package algo_study_2021;
/*
 * 메모리 : 73228 KB
 * 시간 : 696 ms
 * 풀이 시간 : 2H
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_B_G4_16120_PPAP {
static String str;
static Stack<Character> st1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		st1 = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			st1.add((char) str.charAt(i));
		}
		boolean answer = solve();
		if(answer) {
			System.out.println("PPAP");
		}else {
			System.out.println("NP");
		}
	}
	static boolean solve() {
		Stack<Character> st2 = new Stack<Character>();
		StringBuilder sb = new StringBuilder(); 
		int countL = str.length();
		if(countL==1) { //들어온 문자열이 4보다 작을 때 
			if(str.equals("P")) {
				return true;
			}else {
				return false;
			}
		}else if(countL<4) {
			return false;
		}
		
		for (int i = 0; i < 3; i++) {
			char temp = st1.pop();
			sb.append(temp); //3개 초기화
			st2.add(temp);
		}
		while(!st1.isEmpty()) { //한바퀴 다 돌때까지
			char temp = st1.pop();
			sb.append(temp);
			st2.add(temp);
			String dis = sb.toString();
			if(dis.equals("PAPP")) { // PPAP일 경우, 반대이기 때문에 역순
				st2.pop();st2.pop();st2.pop(); //3번 뽑고 -> P로 바꿈 바뀐 P의 전전 단계 부터 다시 검사
				if(2<st2.size()) { // 이미 뽑혀진 갯수가 3개 이상일때
					for (int i = 0; i < 3; i++) { //다시 처음 스택에 넣어준다
						char temp2 = st2.pop();
						st1.add(temp2);
					}
				}else { //3개보다 작을 경우 사이즈 만큼 다시 원래 배열에 넣어준다.
					int count = st2.size();
					for (int i = 0; i < count; i++) {
						char temp2 = st2.pop();
						st1.add(temp2);
					}
				}
				int count2 = st1.size();
				if(3<count2) { //들어온 문자열이 3보다 클 때
					sb.delete(0, 4); //sb 초기화
					for (int i = 0; i < 3; i++) {//3개 초기화
						char temp3 = st1.pop();
						sb.append(temp3); 
						st2.add(temp3);
					}
				}else if(count2==1){
					return true;
				}else {
					return false;
				}
			}else {
				sb.deleteCharAt(0); //제일 처음 들어온 char 삭제
			}
		}
		int size = st2.size();
		sb.delete(0, 4);
		if(size==1) { //길이가 4이상인 str이 들어왔을 때 size가 1이면 남는건 무조건 P밖에 없다.
			return true;
		}else {
			return false;
		}
	}
}
