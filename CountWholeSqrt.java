package Codility;

public class CountWholeSqrt {
    public int solution(int A,int B){
        int count=0;
        for(int i=A;i<=B;i++) {
            double sqrt = Math.sqrt(i);
            int x = (int) sqrt;
            if(Math.pow(sqrt,2) == Math.pow(x,2)){
                count++;
                System.out.println(count);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountWholeSqrt c=new CountWholeSqrt();
        int op=c.solution(4,17);
        System.out.println(op);

    }
}
