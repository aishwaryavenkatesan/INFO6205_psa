package assignmentreport;


import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.HeapSort;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Random;

public class Assignment_6_Helper {

    private final int runs;
    private final int n;
    private final Integer[] result;


    public Assignment_6_Helper(int runs, int n) {
        this.runs = runs;
        this.n = n;
        this.result = new Integer[n];
        randomArray(n);
    }

    public static void timerForArraySort(String desc, int n) {

    }

    private void randomArray(int n) {
        int low = -n;
        int high = n-1;
        int bound = (high - low) + 1;

        for(int i =0; i < n; i++) {
            Random randInt = new Random();
            int arrNum = randInt.nextInt(bound) + low;
            result[i] = arrNum;
        }
    }

    public void runBenchmarks() {
        sortTimings("heap sort", runs, result);
    }

    private void sortTimings (String description, int runs, Integer[] result) {
        if(description == "heap sort") {
            final Config config = Config.setupConfig("true", "0", "1", "", "");
            Helper<Integer> helper = HelperFactory.create("HeapSort", result.length, config);
            SortWithHelper heapSort = new HeapSort(helper);
            Benchmark_Timer heapSortTimer = new Benchmark_Timer<>(description,null, arr -> heapSort.sort(result), null);
            double timeToSort = heapSortTimer.run(result,runs);
            System.out.println("time to sort array of length " + n + " using " + description + " is" + " " + timeToSort);

        }
    }

    public static void main(String[] args) {

        Assignment_6_Helper obj = new Assignment_6_Helper(1, 10);
        new Assignment_6_Helper(10, 10000).runBenchmarks();

        System.out.println(Arrays.toString(obj.result));

    }

}
