package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.*;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n);
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
        new ThreeSumBenchmark(50, 500, 500).runBenchmarks();
        new ThreeSumBenchmark(20, 1000, 1000).runBenchmarks();
        new ThreeSumBenchmark(10, 2000, 2000).runBenchmarks();
        new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
        new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
        new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;

//        Timer t1 = new Timer();
//    double timeObj =    t1.repeat(n, this.supplier);
      //  System.out.println(t1);

     //   Benchmark_Timer timer = new Benchmark_Timer<>(description, function);
        double testTimeAvg = 0;

        for(int i=0; i<runs; i++)
        {
            double time1 = new Benchmark_Timer<>(description, function).runFromSupplier(supplier, runs);
            Benchmark_Timer timer = new Benchmark_Timer<>(description, function);
            double timeObject = timer.runFromSupplier(supplier, runs);
            testTimeAvg+=timeObject;
        }

        double avgTime = testTimeAvg/runs;

        System.out.println("average time 1 " + avgTime);



        timeLoggers[0].log(avgTime,n);
        timeLoggers[1].log(avgTime, n);




        double totalTime = 0;
        double averageTime = 0;

        for(int i=0; i<runs; i++)
        {
            Stopwatch timerObj = new Stopwatch();

            function.accept(this.supplier.get());
            double timeTakenForLap = timerObj.lap();
            totalTime = totalTime + timeTakenForLap;
         //   System.out.println("time for iteration " + i + " " + timeTakenForLap);
        }

        averageTime = totalTime/runs;
        System.out.println("average time 2 " + averageTime);

        timeLoggers[0].log(averageTime,n);
        timeLoggers[1].log(averageTime, n);
        // FIXME
        // END
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
