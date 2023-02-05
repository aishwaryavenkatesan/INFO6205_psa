package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.threesum.ThreeSumQuadratic;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.SorterBenchmark;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.sort.SortWithHelper;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Assignment3Helper {
    private final int runs;
    private final int n;
    private final int m;

    private Consumer<int[]> consumerArr;
    private Consumer<InsertionSort> insertionSortConsumer;
    private Function functionObj;

    private InsertionSort insertSortArray;

    private InsertionSort<Integer> insertionSortObj = new InsertionSort<>();


    private int[] arrayToSort;
    private ArrayList<Integer> arrayList;

    public Assignment3Helper(int runs, int n, int m) {
        this.runs = runs;
        this.n = n;
        this.m = m;
        this.arrayToSort = new int[n];
    }

    public void runBenchmarks() {
        System.out.println("Benchmark value for n " + n);
        generateArrayAndSort("random", n,m);
        generateArrayAndSort("ordered", n,m);
        generateArrayAndSort("partiallyOrdered",n,m);
        generateArrayAndSort("reverseOrdered",n,m);
    }

    private final static TimeLogger[] timeForSort = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
    };


    public void generateArrayAndSort(final String description, int n, int m) {
        int lowerBound = -m;
        int endVal = m-1;
        int mid = m/2;
        int bound = (endVal - lowerBound) + 1;

        System.out.println("inside generateArray func");
        System.out.println(description);






        switch (description) {
            case "random" :
                for(int i=0; i<n; i++) {
                    Random rand = new Random();
                    int num = rand.nextInt(((endVal - lowerBound) + 1)) + lowerBound;
                    System.out.println(num);
                    arrayToSort[i] = num;
                }
                break;

            case "ordered" :
                for(int i=0; i <n; i++) {
                    Random rand = new Random();
                    int num = rand.nextInt(((endVal - lowerBound) + 1)) + lowerBound;
                    arrayToSort[i] = num;
                    Arrays.sort(arrayToSort);
                }
                break;

            case "partiallyOrdered" :
                for(int i=0; i <n; i++) {
                    Random rand = new Random();
                    int num = rand.nextInt(((endVal - lowerBound) + 1)) + lowerBound;
                    arrayToSort[i] = num;
                    Arrays.sort(arrayToSort, 0, mid);
                }
                break;

            case "reverseOrdered" :
                for(int i=0; i<n; i++) {
                    Random rand = new Random();
                    int num = rand.nextInt(((endVal - lowerBound) + 1)) + lowerBound;
                    arrayToSort[i] = num;
                    Arrays.sort(arrayToSort, mid+1, n-1);
                }
                break;
        }

        consumerArr.accept(arrayToSort);

        Benchmark_Timer obj1 = new Benchmark_Timer<>(("Insertion sort for " + description), consumerArr);
        double timeForSorting = obj1.run(arrayToSort, n);

        System.out.println("Time taken for insertion sort for arrayToSort type " + description + " " + timeForSorting);



    }


    public static void main (String[] args) {

        new Assignment3Helper(100, 250, 250).runBenchmarks();
        new Assignment3Helper(50, 500,500).runBenchmarks();
        new Assignment3Helper(20, 1000,1000).runBenchmarks();
        new Assignment3Helper(10, 2000, 2000).runBenchmarks();
        new Assignment3Helper(5,4000,4000).runBenchmarks();






    }

//    public ArrayList<Integer> generateArray(int runs, int n, int m) {
//
//
//    }
}
