/* count the votes and print result of winner.*/
package Hackerrank;

import java.util.*;

public class Voters {
    static String electionWinner(String[] votes) {
        Hashtable<String,Integer> aHash = new Hashtable();
        for(String str : votes){
            if(aHash.containsKey(str)){
                aHash.put(str,aHash.get(str)+1);
            }else{
                aHash.put(str,1);
            }
        }
        ArrayList<Map.Entry<String,Integer>> l = sortValue(aHash);
        return l.get(0).getKey();

    }

    public static ArrayList<Map.Entry<String,Integer>> sortValue(Hashtable<String, Integer> t){

        //Transfer as List and sort it
        ArrayList<Map.Entry<String, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<T>(){

            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().compareTo(o1.getValue())==0){
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o2.getValue().compareTo(o1.getValue());
            }});

        //System.out.println(l);
        return l;
    }
}
