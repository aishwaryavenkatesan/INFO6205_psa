package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class ufClient {

    private static int n;

    public ufClient(int n) {
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

//        n = Integer.parseInt(args[0]);
        m = count(200);
        System.out.println("Number of connections for N value " + 200 + " is" + " " + m);
        m = count(400);
        System.out.println("Number of connections for N value " + 400 + " is" + " " + m);
        m = count(800);
        System.out.println("Number of connections for N value " + 800 + " is" + " " + m);
        m = count(1600);
        System.out.println("Number of connections for N value " + 1600 + " is" + " " + m);
        m = count(3200);
        System.out.println("Number of connections for N value " + 3200 + " is" + " " + m);
        m = count(6400);
        System.out.println("Number of connections for N value " + 6400 + " is" + " " + m);
        m = count(12800);
        System.out.println("Number of connections for N value " + 12800 + " is" + " " + m);
        m = count(25600);
        System.out.println("Number of connections for N value " + 25600 + " is" + " " + m);
    }
}
