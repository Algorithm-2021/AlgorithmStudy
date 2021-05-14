import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * --
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/77486
public class Solution_P_L3_77486_다단계칫솔판매 {
	
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = null;
		Person[] persons = new Person[enroll.length];
		
		Map<String, Integer> hm = new HashMap<>();
		Person parent;
		for(int idx=0; idx<enroll.length; ++idx) {
			parent = hm.containsKey(referral[idx]) ? persons[hm.get(referral[idx])] : null;
			persons[idx] = new Person(enroll[idx], parent);
			hm.put(enroll[idx], idx);
		}
		
		int price;
		for(int i=0; i<seller.length; ++i) {
			price = amount[i] * 100;
			persons[hm.get(seller[i])].sell(price);
		}
		
		answer = new int[persons.length];
		for(int idx = 0; idx<persons.length; ++idx) {
			answer[idx] = persons[idx].sum;
		}
		return answer;
	}

	class Person{
		Person parent;
		String name;
		int sum;
		
		Person(String name, Person parent){
			this.name = name;
			this.parent = parent;
			this.sum =0;
		}
		
		void sell(int price){
			if(price/10 < 0) {
				this.sum += price;
			}
			else {
				this.sum += price - price/10;
				if(parent != null) {
					parent.sell(price/10);
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution_P_3 sol = new Solution_P_3();
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		int[] answer = sol.solution(enroll, referral, seller, amount);
		System.out.println(Arrays.toString(answer));
	}
}