class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[] answerList = new int[86400];
        int len = lines.length;
        for(int i =0; i<len ; i++){
            String temp = lines[i];
            int[] list =  findStartEnd(temp);
            int start = list[0];
            int end = list[1];
            //범위따라 넣어주기
            for(int j = start; j<end; j++){
                if(j==86400) break;
                answerList[j]++;
                //변화하는 배열 확인해서 최대값구하기
                System.out.println(answer+" "+answerList[j]);
                if(answer<answerList[j]) answer = answerList[j];
            }
        }
        return answer;
    }
    
    static int[] findStartEnd(String str){
        
        int hour = (str.charAt(11)-'0')*10+(str.charAt(12)-'0');
        int min = (str.charAt(14)-'0')*10+(str.charAt(15)-'0');
        int sec = (str.charAt(17)-'0')*10+(str.charAt(18)-'0');
        float secF = Float.parseFloat(str.substring(17,23));
        float T = Float.parseFloat(str.substring(24,str.length()-1));
        // System.out.println(hour+" "+min+" "+sec+" "+secF+" "+T);
        
        //시작초 구하기
        int start = 0;
        //뒷자리수 없는 초라면
        if((secF-sec)==0) start = hour*60*60+min*60+sec-1;
        else start = hour*60*60+min*60+sec;
        if(start <0 ) start = 0;
        //끝초 구하기
        float mili = secF+T;
        int miliInt = (int)mili;
        // System.out.println(mili+" "+miliInt);
        int end = 0;
        //뒷자리 수가 없다면 
        if((mili-miliInt)==0) {
            end = start-sec+miliInt;
            // System.out.println("뒷자리수 없음");
        }
        else end = start-sec+miliInt+1;
        if(end>86400) end = 86400;
        int[] startEnd = {start, end};
        System.out.println(startEnd[0]+" se "+startEnd[1]);
        
        return startEnd;
        
    }
}
