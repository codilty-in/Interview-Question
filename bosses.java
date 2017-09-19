/*////Create Employee Tree
3//Number of Company
Fred,Karl,Technician,2010--Karl,Cathy,VP,2009--Cathy,NULL,CEO,2007
Adam,Karl,Technician,2010--Bob,Karl,Technician,2012--Cathy,Karl,Technician,2013--Karl,Nancy,Manager,2009--Wendy,Nancy,Technician,2012--Nancy,NULL,CEO,2007
Fred,Cathy,Technician,2010--Nancy,Wendy,Technician,2013--Vince,Karl,VP,2009--Bob,Susan,Manager,2010--Adam,Susan,Technician,2011--Ned,Wendy,Technician,2009--Liam,Wendy,Technician,2007--Dan,Ryan,Director,2008--Carl,Susan,Technician,2010--Ed,Wendy,Technician,2007--Patty,Wendy,Technician,2008--Tom,Susan,Technician,2011--Sam,Susan,Technician,2008--Lilly,Jack,Manager,2007--Amy,Jill,Technician,2012--Wendy,Dan,Manager,2010--Cathy,Vince,Director,2006--Susan,Vince,Director,2009--Betty,Cathy,Manager,2012--Oscar,Betty,Technician,2006--Jill,Dan,Manager,2012--Katie,Jill,Technician,2007--Paul,Bob,Technician,2007--Ryan,Karl,VP,2007--Mary,Lilly,Technician,2013--Matt,Jill,Technician,2007--Karl,NULL,CEO,2005--Jack,Ryan,Director,2009

output:
Case #1
Cathy (CEO) 2007
-Karl (VP) 2009
--Fred (Technician) 2010
Case #2
Nancy (CEO) 2007
-Karl (Manager) 2009
--Adam (Technician) 2010
--Bob (Technician) 2012
--Cathy (Technician) 2013
-Wendy (Technician) 2012
Case #3
Karl (CEO) 2005
-Ryan (VP) 2007
--Dan (Director) 2008
---Jill (Manager) 2012
----Amy (Technician) 2012
----Katie (Technician) 2007
----Matt (Technician) 2007
---Wendy (Manager) 2010
----Ed (Technician) 2007
----Liam (Technician) 2007
----Nancy (Technician) 2013
----Ned (Technician) 2009
----Patty (Technician) 2008
--Jack (Director) 2009
---Lilly (Manager) 2007
----Mary (Technician) 2013
-Vince (VP) 2009
--Cathy (Director) 2006
---Betty (Manager) 2012
----Oscar (Technician) 2006
---Fred (Technician) 2010
--Susan (Director) 2009
---Adam (Technician) 2011
---Bob (Manager) 2010
----Paul (Technician) 2007
---Carl (Technician) 2010
---Sam (Technician) 2008
---Tom (Technician) 2011
*/
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
