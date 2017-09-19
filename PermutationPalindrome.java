/*permutation of string is panlindrom or not*/
package GeneralQuestion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PermutationPalindrome {
    public static String check(String s) {
        int odd=0;
        s = s.toLowerCase();//convert into lower case
        s = s.replaceAll(" ", "");//remove white space
        char[] c_arr = s.toCharArray();//convert into char arr

        Arrays.sort(c_arr);//sort array
        int[] count = new int[26];
        for (char c : c_arr) {
            count[c - 'a']++;
            if(count[c-'a']%2==1){
                odd++;
            }
            else {
                odd--;
            }
        }
        if(odd<=1){
            return "Yes";
        }
        return "No";
    }

    public static void main(String[] args) {
        System.out.println(PermutationPalindrome.check("Dodod"));
    }
}
