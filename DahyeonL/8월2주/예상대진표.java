테스트 1 〉	통과 (0.02ms, 56.8MB)
테스트 2 〉	통과 (0.03ms, 57.9MB)
테스트 3 〉	통과 (0.03ms, 58.6MB)
테스트 4 〉	통과 (0.03ms, 68MB)
테스트 5 〉	통과 (0.02ms, 58.3MB)
테스트 6 〉	통과 (0.02ms, 58.2MB)
테스트 7 〉	통과 (0.03ms, 69.5MB)
테스트 8 〉	통과 (0.02ms, 57MB)
테스트 9 〉	통과 (0.01ms, 57.9MB)
테스트 10 〉	통과 (0.02ms, 58.4MB)
테스트 11 〉	통과 (0.03ms, 56.9MB)
테스트 12 〉	통과 (0.02ms, 57.1MB)
테스트 13 〉	통과 (0.02ms, 57.2MB)
테스트 14 〉	통과 (0.03ms, 57.6MB)
테스트 15 〉	통과 (0.03ms, 70.7MB)
테스트 16 〉	통과 (0.03ms, 68.9MB)
테스트 17 〉	통과 (0.03ms, 66.8MB)
테스트 18 〉	통과 (0.02ms, 58MB)
테스트 19 〉	통과 (0.03ms, 57.3MB)
테스트 20 〉	통과 (0.02ms, 67.2MB)
테스트 21 〉	통과 (0.02ms, 57.4MB)
테스트 22 〉	통과 (0.03ms, 57.6MB)
테스트 23 〉	통과 (0.02ms, 58.4MB)
테스트 24 〉	통과 (0.03ms, 68.1MB)
테스트 25 〉	통과 (0.02ms, 58.1MB)
테스트 26 〉	통과 (0.02ms, 72MB)
테스트 27 〉	통과 (0.03ms, 56.8MB)
테스트 28 〉	통과 (0.03ms, 65.8MB)
테스트 29 〉	통과 (0.02ms, 64.5MB)
테스트 30 〉	통과 (0.03ms, 56.9MB)
테스트 31 〉	통과 (0.02ms, 71.1MB)
테스트 32 〉	통과 (0.03ms, 58.7MB)
테스트 33 〉	통과 (0.02ms, 56.9MB)
테스트 34 〉	통과 (0.02ms, 57.5MB)
class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        while(true){
            int newA = findNext(a);
            int newB = findNext(b);
            if(newA==newB) return answer;
            else {
                a = newA;
                b = newB;
                answer++;
            }
        }    
        // return answer;
    }
    
    static int findNext(int n){
        //홀수일때
        if(n%2==1){
            return n/2 + 1;
        }else return n/2;
    }
}
