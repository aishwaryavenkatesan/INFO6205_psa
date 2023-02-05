package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.*;

public class Assignment3Helper {
    private final int runs;
    private final int n;
    private final int m;

    private InsertionSort<Integer> insertionSortObj = new InsertionSort<>();


    private Integer[] arrayToSort;

    public Assignment3Helper(int runs, int n, int m) {
        this.runs = runs;
        this.n = n;
        this.m = m;
        this.arrayToSort = new Integer[n];
    }

    public void runBenchmarks() {
        System.out.println("Benchmark value for n " + n);
        generateArrayAndSort("random", n, m);
        generateArrayAndSort("ordered", n, m);
        generateArrayAndSort("partiallyOrdered", n, m);
        generateArrayAndSort("reverseOrdered", n, m);
    }
    public void generateArrayAndSort(final String description, int n, int m) {

        System.out.println("inside generateArrayAndSort function");
        System.out.println(description);

        switch (description) {
            case "random" :
                randomOrder(n,m);
                break;

            case "ordered" :
                ordered(n,m);
                break;

            case "partiallyOrdered" :
                partiallyOrdered(n,m);
                break;

            case "reverseOrdered" :
                reverseOrdered(n,m);
                break;
        }

        InsertionSort<Integer> insertionSortObject = new InsertionSort<>();
        Benchmark_Timer benchmark_timerObj = new Benchmark_Timer<>(description, null, arrayToSort -> insertionSortObject.sort((Integer[]) arrayToSort, 0, n),null);
        double timeForSorting = benchmark_timerObj.run(arrayToSort,n);

        System.out.println( " " + " time taken for insertion sort for " + " n value "  + n +  " " + description + " " + "is " + timeForSorting);
    }

    public void randomOrder(int n, int m) {
        int lowerBound = -m;
        int upperBound = m - 1;
        int mid = m / 2;
        int bound = (upperBound - lowerBound) + 1;

        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            int num = rand.nextInt(bound) + lowerBound;
            arrayToSort[i] = num;
        }
    }

    public void ordered( int n, int m) {
        int lowerBound = -m;
        int upperBound = m - 1;
        int mid = m / 2;
        int bound = (upperBound - lowerBound) + 1;

        for(int i=0; i <n; i++) {
            Random rand = new Random();
            int num = rand.nextInt(bound)+ lowerBound;
            arrayToSort[i] = num;
            Arrays.sort(arrayToSort);
        }
    }

    public void partiallyOrdered (int n, int m) {
        int lowerBound = -m;
        int upperBound = m - 1;
        int mid = m / 2;
        int bound = (upperBound - lowerBound) + 1;

        for(int i=0; i <n; i++) {
            Random rand = new Random();
            int num = rand.nextInt(bound) + lowerBound;
            arrayToSort[i] = num;
            Arrays.sort(arrayToSort, 0, mid);
        }
    }

    public void reverseOrdered (int n, int m) {
        int lowerBound = -m;
        int upperBound = m - 1;
        int mid = m / 2;
        int bound = (upperBound - lowerBound) + 1;

        for(int i=0; i<n; i++) {
            Random rand = new Random();
            int num = rand.nextInt(bound) + lowerBound;
            arrayToSort[i] = num;
            Arrays.sort(arrayToSort, mid+1, n-1);
        }

    }



    public static void main (String[] args) {

        new Assignment3Helper(100, 500, 500).runBenchmarks();
        new Assignment3Helper(100, 1000,1000).runBenchmarks();
        new Assignment3Helper(100, 2000,2000).runBenchmarks();
        new Assignment3Helper(50, 4000, 4000).runBenchmarks();
        new Assignment3Helper(50,8000,8000).runBenchmarks();
    }

}
