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
