public class PercolationStats {
    private double[] threshold;
    private int trial;
    private double[] experimentTimes;

    // Main method
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        trial = T;
        threshold = new double[T];
        experimentTimes = new double[T];

        for (int i = 0; i < T; i++) {
            Stopwatch timer = new Stopwatch();
            Percolation perc = new Percolation(N);
            int open = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    open++;
                }
            }
            threshold[i] = (double) open / (N * N);
            experimentTimes[i] = timer.elapsedTime();
        }

    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(trial);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(trial);
    }

    public double meanTime() {
        return StdStats.mean(experimentTimes);
    }

    public double totalTime() {
        double total = 0.0;
        for (double time : experimentTimes) {
            total += time;
        }
        return total;
    }


    public static void main(String[] args) {
        int N = 200;
        int T = 100;
        Stopwatch totalTimer = new Stopwatch();
        PercolationStats stats = new PercolationStats(N, T);
        double totalTime = totalTimer.elapsedTime();

        System.out.println("Mean: " + stats.mean());
        System.out.println("Standard deviation: " + stats.stddev());
        System.out.println("95% confidence interval: [" + stats.confidenceLow() + ", " + stats.confidenceHigh() + "]");
        System.out.println("Total runtime: " + totalTime + " sec");
        System.out.println("Average runtime per trial: " + stats.meanTime() + " sec");
    }

}


