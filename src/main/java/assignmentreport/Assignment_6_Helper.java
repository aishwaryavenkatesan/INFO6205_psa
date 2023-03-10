package assignmentreport;


import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.HeapSort;
import edu.neu.coe.info6205.sort.linearithmic.QuickSort_DualPivot;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.SortBenchmark;
import edu.neu.coe.info6205.util.TimeLogger;

import java.io.IOException;
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

    public void runBenchmarks() throws IOException {
        sortTimings("quick sort", runs, result, n);
    }

    private void sortTimings (String sortType, int runs, Integer[] result, int n) {

        if(sortType == "heap sort") {
            final Config config = Config.setupConfig("true", "0", "1", "", "");
            Helper<Integer> helper = HelperFactory.create("HeapSort", result.length, config);
            helper.init(n);
            SortWithHelper heapSort = new HeapSort<>(helper);
            Benchmark_Timer<Object> heapSortTimer = new Benchmark_Timer<>(sortType,null, arr -> heapSort.sort(result), null);
            double timeToSort = heapSortTimer.run(result,runs);
            System.out.println("time to sort array of length " + n + " using " + sortType + " is" + " " + timeToSort);
        }

        if(sortType == "quick sort") {
            final Config config = Config.setupConfig("true", "0", "1", "", "");
            Helper<Integer> helper = HelperFactory.create("quick sort", result.length, config);
            helper.init(n);
            SortWithHelper quickSort = new QuickSort_DualPivot(n, config);
            Benchmark_Timer quickSortTimer = new Benchmark_Timer(sortType,null, arr -> quickSort.sort(result), null);
            double timeToSort = quickSortTimer.run(result,runs);
            System.out.println("time to sort array of length " + n + " using " + sortType + " is" + " " + timeToSort);
        }

    }

    public static void main(String[] args) throws IOException {

        new Assignment_6_Helper(10, 100000).runBenchmarks();

    }

}
