package Leetcode;

public class LongestPrefix {

    public int second(String s){
        int prev = s.charAt(0);
        for(int i = 1;i < s.length();i++){
            int num = s.charAt(i);
            if(prev < num){
                return i;
            }
            prev = num;
        }
        return s.length();

    }
    public int longestPrefix(String s) {

        if (s.length() == 0) return 0;

        int pre;
        int next;
        String prefix=String.valueOf(s.charAt(0));
        int i=0;
        while(i<s.length()){
            pre=s.charAt(i)-'a';
            try {
                if (s.charAt(i + 1) != '\0') {
                    next = s.charAt(i + 1) - 'a';
                    if (pre <= next) {
                        prefix = prefix + String.valueOf(s.charAt(i + 1));
                        i++;

                    } else {
                        break;
                    }
                }
            }catch (Exception e){
                return prefix.length();
            }

        }

        return prefix.length();
    }

    public static void main(String[] args) {
        LongestPrefix a=new LongestPrefix();
        System.out.print(a.longestPrefix("excel"));
    }
}
