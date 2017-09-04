package Hackerrank;
/*
Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

        Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?

        Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions required to make  and  anagrams. Any characters can be deleted from either of the strings.*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MakingAnagram {
    public static int numberNeeded(String first, String second) {
        char[] a=first.toCharArray();
        char[] b=second.toCharArray();
        int count=0;

        Arrays.sort(a);
        Arrays.sort(b);

        first=new String(a);
        second=new String(b);
        //    System.out.println(first+" \n"+second);
        int[] alph=new int[26];
        for(int i=0;i<first.length();i++){
            alph[a[i]-'a']++;
            //       System.out.println(alph[i]);
        }

        for(int i=0;i<second.length();i++){
            alph[b[i]-'a']--;
            //   System.out.println(alph[i]);

        }
        for(int i=0;i<26;i++){
            count+=Math.abs(alph[i]);
        }

        return count;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
