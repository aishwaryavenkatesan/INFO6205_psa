package edu.neu.coe.info6205.union_find;
import java.util.Random;

public class UnionFindClient {
    private static int n;

    public UnionFindClient(int n) {
        this.n = n;
    }

    public static int count(int n) {

        UF_HWQUPC obj = new UF_HWQUPC(n);
        Random rand = new Random();
        int numberOfConnections = 0;
        while(obj.components() > 1) {
            int randInt1 = rand.nextInt(n);
            int randInt2 = rand.nextInt(n);
            if (obj.connected(randInt1, randInt2)) {
                numberOfConnections++;
            } else {
                obj.union(randInt1, randInt2);
            }

        }
        return numberOfConnections;
    }

    public static void main(String args[]) {
        int m = 0;
//        if (args.length == 0) {
//            throw new RuntimeException("no value for n");
//        }

        n = Integer.parseInt(args[0]);
        m = count(500);
        System.out.println("Number of connections for N value " + 500 + " is" + " " + m);
        m = count(1000);
        System.out.println("Number of connections for N value " + 1000 + " is" + " " + m);
        m = count(2000);
        System.out.println("Number of connections for N value " + 2000 + " is" + " " + m);
        m = count(4000);
        System.out.println("Number of connections for N value " + 4000 + " is" + " " + m);
        m = count(8000);
        System.out.println("Number of connections for N value " + 8000 + " is" + " " + m);
        m = count(16000);
        System.out.println("Number of connections for N value " + 16000 + " is" + " " + m);
    }
}
