package assignmentreport;
import edu.neu.coe.info6205.union_find.UF_HWQUPC;

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
        m = count(120);
        System.out.println("Number of connections for N value " + 120 + " is" + " " + m);
        m = count(240);
        System.out.println("Number of connections for N value " + 240 + " is" + " " + m);
        m = count(480);
        System.out.println("Number of connections for N value " + 480 + " is" + " " + m);
        m = count(960);
        System.out.println("Number of connections for N value " + 960 + " is" + " " + m);
        m = count(1920);
        System.out.println("Number of connections for N value " + 1920 + " is" + " " + m);
        m = count(3840);
        System.out.println("Number of connections for N value " + 3840 + " is" + " " + m);
        m = count(7680);
        System.out.println("Number of connections for N value " + 7680 + " is" + " " + m);
        m = count(15360);
        System.out.println("Number of connections for N value " + 15360 + " is" + " " + m);
    }
}
