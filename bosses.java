import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nidhi Desai on 9/18/2017.
 */
public class bosses {
    ArrayList<Person1> ppl_list = new ArrayList<>();

    Hashtable<String,ArrayList<Person1>> graph = new Hashtable<>();
    private static final String FILENAME = "org_chart_sample.in";

    public static void main(String[] args) {
        bosses aBosses = new bosses();
        aBosses.read();

    }

    ArrayList<Person1> read(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String sCurrentLine;
            sCurrentLine = br.readLine();
            int num = Integer.parseInt(sCurrentLine);
            int caseNum=1;
            if (num > 0) {
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println("case #"+caseNum);
                    caseNum++;
                    String[] currList1 = sCurrentLine.split("--");
                    graph = new Hashtable<>();
                    for(String curr: currList1){
                        String[] currList = curr.split(",");
                        int iter=0;
                        Person1 temp=new Person1(currList[iter],currList[iter+1],currList[iter+2],Integer.parseInt(currList[iter+3]));
                        ppl_list.add(temp);
                        String boss = currList[iter+1];
                        if(graph.containsKey(boss)) {
                            graph.get(boss).add(temp);
                            graph.put(boss,graph.get(boss));
                        }
                        else{
                            ArrayList<Person1> aValue = new ArrayList<>();
                            aValue.add(temp);
                            graph.put(boss,aValue);
                        }
                    }
                    DFS_caller(graph);

                }
                //System.out.println(ppl_list);

            }

        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void DFS_caller(Hashtable<String, ArrayList<Person1>> graph) {
        ArrayList<Person1> visited = new ArrayList<>();
        DFS("NULL", visited,0);

    }

    public static String repeat(String s, int n) {
        return Stream.generate(() -> s).limit(n).collect(Collectors.joining(""));
    }

    private void DFS(String person, ArrayList<Person1> visited, int level) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("sum_sample_solution_java.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Person1> ppl = new ArrayList<>();
        if(graph.containsKey(person)) {
            ppl = graph.get(person);

            for (Person1 p : ppl) {
                if (!visited.contains(p)) {
                    try {
                        writer.write(repeat("-", level) + p.name + " (" + p.title + ") " + p.year);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    visited.add(p);
                    DFS(p.name, visited, level + 1);
                }
            }
        }

    }
}


class Person1{
    String name;
    String boss;
    String title;
    int year;

    public Person1(String name, String boss, String title, int year){
        this.name = name;
        this.boss =boss;
        this.title = title;
        this.year = year;
    }

    public String toString(){
        return this.name+" "+this.boss+" "+this.title+" "+this.year;
    }

    public boolean equals(Object obj){
        if (obj instanceof Person1){
            Person1 temp1 = (Person1) obj;
            return this.name.equals(temp1.name) &&
                    this.boss.equals(temp1.boss) &&
                    this.title.equals(temp1.title) &&
                    this.year == temp1.year;
        }
        return false;
    }


}
