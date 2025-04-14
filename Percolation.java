public class Percolation {
    private int n;
    private boolean[][] grid;
    private int virtualTop;
    private int virtualBottom;
    private int openSitesCount;
    private WeightedQuickUnionUF uf;
//    private QuickFindUF uf;
// Shift the declaration by comment while choosing which Union-Find algorithm to use

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be greater than 0");
        this.n = n;
        this.grid = new boolean[n][n];
        this.openSitesCount = 0;

        this.uf = new WeightedQuickUnionUF(n * n + 2);
//        this.uf = new QuickFindUF(n * n + 2);
// Shift the variable declaration here either
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;

    }

    public void open(int i, int j) {
        validateIndices(i, j);
        if (grid[i][j]) return;

        grid[i][j] = true;
        openSitesCount++;

        int current = flattenIndex(i, j);

        if (i == 0) {
            uf.union(current, virtualTop);
        }
        if (i == n - 1) {
            uf.union(current, virtualBottom);
        }

        connectIfOpen(i, j, i - 1, j);
        connectIfOpen(i, j, i + 1, j);
        connectIfOpen(i, j, i, j - 1);
        connectIfOpen(i, j, i, j + 1);

    }

    private void connectIfOpen(int i1, int j1, int i2, int j2) {
        if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < n && grid[i2][j2]) {
            uf.union(flattenIndex(i1, j1), flattenIndex(i2, j2));
        }
    }

    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        validateIndices(i, j);
        return isOpen(i, j) && uf.connected(flattenIndex(i, j), virtualTop);
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    private void validateIndices(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Indices out of bounds");
        }
    }

    private int flattenIndex(int i, int j) {
        return i * n + j;
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }


    // Here is only the program testing part
    public static void main(String[] args) {
        int N = 3;
        Percolation perc = new Percolation(N);

        perc.open(0, 0);
        perc.open(1, 0);
        perc.open(2, 0);

        System.out.println("Whether percolate？" + perc.percolates());
        System.out.println("Is (2,0) full？" + perc.isFull(2, 0));
    }
}

