package com.example.discoveryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class Test {
    //4

    //10 6 6 0

    class Prob{
        public int solution1(String map){
            int max = 0;
            int curLength = 0;
            Stack<Character> jump = new Stack<>();

            char[] m = map.toCharArray();
            for(int i=0;i<m.length; i++){
               if('C'==m[i]){
                   jump.push(m[i]);
                   curLength++;
               }else if('T' == m[i]){
                   if(!jump.isEmpty()){
                       jump.pop();
                       curLength++;
                   }else{
                       max = Math.max(max, curLength);
                       curLength = 0;
                   }
               }
            }

            if(jump.isEmpty()){
                max = Math.max(max, curLength);
            }
            // 이렇게 체크하면 "CCCCTTT" 케이스 통과못함.
            return max;
        }
        public int solution2(String map){
            int max = 0;
            List<String> list = new ArrayList<>();
            // window 크기
            for(int size=0; size<=map.length(); size++){

                for(int i=0; i+size <= map.length(); i++){
                    String check = map.substring(i, i+size);
//                    System.out.println(check);
                    if(checkMap(check)){
                        list.add(check);
                        break;
                    }
//                    System.out.println(map.substring(i, i+size));
                }
            }

            // 가능한 문자열에서 최대값을 구함.
            return list.stream().max((o1, o2) -> o1.length()-o2.length()).get().length();
        }

        public boolean checkMap(String s){

            char[] cSet = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for(char c : cSet) {
                if (c == 'C') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }
            return stack.isEmpty();
        }

        public void main(String[] args) {
            String[] inputs = new String[]{
                    "CCTTTCTCCTCCTTTTCTCT"
                    ,"CCCTTT"
                    ,"CCCCTTT"
                    ,"C"
            };

            for(String s : inputs){
//            System.out.println(solution1(s));
                System.out.println(solution2(s));
            }
        }
    }

    @org.junit.jupiter.api.Test
    void test(){
        String[] inputs = new String[]{
                "CCTTTCTCCTCCTTTTCTCT"
                ,"CCCTTT"
                ,"CCCCTTT"
                ,"C"
        };
        Prob prob = new Prob();
        for(String s : inputs){
//            System.out.println(prob.solution1(s));
            System.out.println(prob.solution2(s));
        }

    }

}
