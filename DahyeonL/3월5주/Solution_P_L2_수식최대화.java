package algo_study_2021;
/*
테스트 1 〉통과 (0.25ms, 52MB)
테스트 2 〉통과 (0.36ms, 52.5MB)
테스트 3 〉통과 (0.46ms, 52.8MB)
테스트 4 〉통과 (0.53ms, 52.4MB)
테스트 5 〉통과 (0.58ms, 52.3MB)
테스트 6 〉통과 (0.93ms, 53.3MB)
테스트 7 〉통과 (0.58ms, 52.2MB)
테스트 8 〉통과 (0.51ms, 53.3MB)
테스트 9 〉통과 (0.64ms, 53MB)
테스트 10 〉통과 (0.69ms, 52.4MB)
테스트 11 〉통과 (0.64ms, 52MB)
테스트 12 〉통과 (0.70ms, 52.4MB)
테스트 13 〉통과 (0.77ms, 53.8MB)
테스트 14 〉통과 (0.76ms, 52.4MB)
테스트 15 〉통과 (0.80ms, 52.1MB)
테스트 16 〉통과 (0.34ms, 54.1MB)
테스트 17 〉통과 (0.41ms, 52.4MB)
테스트 18 〉통과 (0.35ms, 52.6MB)
테스트 19 〉통과 (0.23ms, 53.2MB)
테스트 20 〉통과 (0.36ms, 52.3MB)
테스트 21 〉통과 (0.43ms, 52.3MB)
테스트 22 〉통과 (0.44ms, 53.2MB)
테스트 23 〉통과 (0.29ms, 52.4MB)
테스트 24 〉통과 (0.85ms, 51.9MB)
테스트 25 〉통과 (1.05ms, 53.1MB)
테스트 26 〉통과 (0.29ms, 53MB)
테스트 27 〉통과 (0.93ms, 52.7MB)
테스트 28 〉통과 (0.70ms, 53.2MB)
테스트 29 〉통과 (0.64ms, 53MB)
테스트 30 〉통과 (0.69ms, 52.5MB)
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Solution_P_L2_수식최대화 {
static int count;
static boolean isOperater[];
static Queue<Long> qN = new LinkedList<Long>();
static Queue<Character> qO = new LinkedList<Character>();
static Queue<Long> tempQN = new LinkedList<Long>();
static Queue<Character> tempQO = new LinkedList<Character>();

	public static void main(String[] args) {
		String str="50*6-3*2";
		System.out.println(solution(str));
		
	}
	
    static long solution(String expression) {
        long answer = 0;
        StringTokenizer stk=new StringTokenizer(expression,"-+*");
        count = stk.countTokens();
        for (int i = 0; i < count; i++) {
			qN.add(Long.parseLong(stk.nextToken()));
		}
        char temp;
        isOperater = new boolean[3];
        for (int i = 0; i < expression.length(); i++) {
			temp = expression.charAt(i);
			switch (temp) {
			case '-': isOperater[0] =true; qO.add(temp); break;
			case '+': isOperater[1] =true; qO.add(temp); break;
			case '*': isOperater[2] =true; qO.add(temp); break;
			}
        }
        
        for (int i = 0; i < 3; i++) {
			if(!isOperater[i]) {
				continue;
			}else {
				for (int j = 0; j < 3; j++) {
					if(j==i) {
						continue;
					}else {
						for (int k = 0; k < 3; k++) {
							if(k==i||k==j) {
								continue;
							}else {
								tempQN.addAll(qN);
								tempQO.addAll(qO);
								solve(i);solve(j);solve(k);
								long tempA = tempQN.poll();
								tempA = Math.abs(tempA);
								answer = Math.max(answer, tempA);
							}
						}
					}
				}
			}
		}
        return answer;
    }
   
    static long Cal(char i, long a, long b) {
    	switch (i) {
		case '-': return a-b; 
		case '+': return a+b; 
		case '*': return a*b; 
		default: return -1;
		}
    }
    static void solve(int i) {
    	//들어온 oper로만 계산하기 
    	//1. 현재 연산자 큐의 길이 재기 
    	//2. 연산자가 같을경우 계산 아닐경우 뒤로 붙여주기
    	char oper=0;
    	switch (i) {
		case 0 : oper = '-'; break;
		case 1 : oper = '+'; break;
		case 2 : oper = '*'; break;
		}
    	int countOper = tempQO.size();
    	long a = tempQN.poll();
    	long b =0;
    	char op;
    	while(countOper>0) {
    		op = tempQO.poll();
    		b = tempQN.poll();
    		if(op==oper) { // 계산해야하는 연산자라면 계산하기
    			a = Cal(op, a, b);
    		}else {
    			tempQO.add(op);//안하는 연산자면 다시 넣기
    			tempQN.add(a); //계산안하는 숫자
    			a = b;//앞으로 땡기기
    		}
    		countOper--;
    	}
    	tempQN.add(a);
//    	System.out.println(tempQN.toString());
//    	System.out.println(tempQO.toString());
    }
}
