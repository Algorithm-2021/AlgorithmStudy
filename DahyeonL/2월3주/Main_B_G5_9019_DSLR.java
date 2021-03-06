/*
 * 메모리 : 300204 kb
 * 시간 : 8276 ms
 * 풀이 시간 : 6H
 */
package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_9019_DSLR {
	static int T, start, result;
	static int[] startS, resultR;
	static boolean isvisit[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			result = Integer.parseInt(st.nextToken());
			startS = changeArrays(start);
			resultR = changeArrays(result);
			node nodeA = new node(start,startS,"");
			node nodeB = new node(result,resultR,"");
			isvisit = new boolean[10000];
			BFS(nodeA,nodeB);
		}
	}
	static void BFS(node nodeA, node nodeB) {
		Queue<node> q = new LinkedList<node>();
		q.offer(nodeA);
		node temp;
		A:while(!q.isEmpty()) {
			temp = q.poll();
			isvisit[temp.a]=true;
			if(temp.a==nodeB.a) {
				System.out.println(temp.str);
				break A;
			}else {
				//D
				node temp2 = new node(temp.a,temp.A,temp.str);
				if(9999<temp2.a*2) {
					temp2.a = (temp2.a*2)%10000;
				}else temp2.a = temp2.a*2;
				temp2.A = changeArrays(temp2.a);
				temp2.str=temp2.str+"D";
				if(!isvisit[temp2.a]) {
					q.offer(temp2);
					isvisit[temp2.a]=true;
				}
				//S
				temp2 = new node(temp.a,temp.A,temp.str);
				if(temp2.a==0) {
					temp2.a = 9999;
				}else {
					temp2.a = temp2.a-1;
				}
				temp2.A = changeArrays(temp2.a);
				temp2.str=temp2.str+"S";
				if(!isvisit[temp2.a]) {
					q.offer(temp2);
					isvisit[temp2.a]=true;
				}
				//L
				temp2 = new node(temp.a,temp.A,temp.str);
				int num = temp2.A[0];
				temp2.A[0] = temp2.A[1];
				temp2.A[1] = temp2.A[2];
				temp2.A[2] = temp2.A[3];
				temp2.A[3] = num;
				temp2.a = changeNum(temp2.A);
				temp2.str=temp2.str+"L";
				if(!isvisit[temp2.a]) {
					q.offer(temp2);
					isvisit[temp2.a]=true;
				}
				//R
				temp2 = new node(temp.a,temp.A,temp.str);
				int num2 = temp2.A[3];
				temp2.A[3] = temp2.A[2];
				temp2.A[2] = temp2.A[1];
				temp2.A[1] = temp2.A[0];
				temp2.A[0] = num2;
				temp2.a = changeNum(temp2.A);
				temp2.str=temp2.str+"R";
				if(!isvisit[temp2.a]) {
					q.offer(temp2);
					isvisit[temp2.a]=true;
				}
			}
		}
	}
	static int[] changeArrays(int a) {
		int[] arrays = new int[4];
		arrays[0] = a/1000;
		arrays[1] = a/100 - arrays[0]*10;
		arrays[2] = a/10 - arrays[0]*100 -arrays[1]*10;
		arrays[3] = a -arrays[0]*1000 -arrays[1]*100 - arrays[2]*10;
		return arrays;
	}
	static int changeNum(int[] arrays) {
		int num =0;
		num = arrays[0]*1000+arrays[1]*100+arrays[2]*10+arrays[3];
		return num;
	}
//	static node D(node node) {
//		if(9999<node.a*2) {
//			node.a = (node.a*2)%10000;
//		}else node.a = node.a*2;
//		node.A = changeArrays(node.a);
//		node.str=node.str+"D";
//		return node;
//	}
//	static node S(node node) {
//		if(node.a==0) {
//			node.a = 9999;
//		}else {
//			node.a = node.a-1;
//		}
//		node.A = changeArrays(node.a);
//		node.str=node.str+"S";
//		return node;
//	}
//
//	static node L(node node) {
//		int temp = node.A[0];
//		node.A[0] = node.A[1];
//		node.A[1] = node.A[2];
//		node.A[2] = node.A[3];
//		node.A[3] = temp;
//		node.a = changeNum(node.A);
//		node.str=node.str+"L";
//		return node;
//	}
//
//	static node R(node node) {
//		int temp = node.A[3];
//		node.A[3] = node.A[2];
//		node.A[2] = node.A[1];
//		node.A[1] = node.A[0];
//		node.A[0] = temp;
//		node.a = changeNum(node.A);
//		node.str=node.str+"R";
//		return node;
//	}
	static class node{
		int a;
		int[] A;
		String str;
		
		public node(int a, int[] A, String str) {
			this.a = a;
			this.A = A.clone();
			this.str = str;
		}
		
	}

}
