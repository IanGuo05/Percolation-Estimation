PERCOLATION ASSIGNMENT README

Yiyuan Guo
2023141520220
Sichuan University-Pittsburgh Institute
=========================================

1. PROJECT DESCRIPTION
----------------------

This program estimates the percolation threshold of N by N grid using Monte Carlo simulation,
comparing the performance of two Union-Find algorithms:
- QuickFindUF
- WeightedQuickUnionUF

2. FILES INCLUDED
-----------------
- Percolation.java          : Main percolation system implementation
- PercolationStats.java     : Statistical calculations and analysis
- QuickFindUF.java          : QuickFind algorithm implementation
- WeightedQuickUnionUF.java : Weighted Quick-Union algorithm implementation
- stdlib.jar                : Standard library dependencies

3. EXPERIMENTAL RESULTS 
-----------------------

3.1 (N=200, T=100)
--- --- --- --- ---
QUICKFINDUF:
- Mean percolation threshold : 0.592
- Standard deviation         : 0.009
- 95% confidence interval    : [0.590, 0.594]
- Average time per trial     : 0.228 sec
- Total runtime              : 22.824 sec

WEIGHTED QUICK-UNION:
- Mean percolation threshold : 0.592
- Standard deviation         : 0.010
- 95% confidence interval    : [0.590, 0.594]
- Average time per trial     : 0.002 sec
- Total runtime              : 0.192 sec

3.2 (N=20, T=100)
--- --- --- --- ---
QUICKFINDUF:
- Mean percolation threshold : 0.589
- Standard deviation         : 0.054
- 95% confidence interval    : [0.577, 0.598]
- Average time per trial     : 1.400E-4 sec
- Total runtime              : 0.014 sec

WEIGHTED QUICK-UNION:
- Mean percolation threshold : 0.596
- Standard deviation         : 0.052
- 95% confidence interval    : [0.585, 0.606]
- Average time per trial     : 9.0E-5 sec
- Total runtime              : 0.009 sec

4. PERFORMANCE ANALYSIS
----------------------
TIME COMPLEXITY:
- QuickFindUF:
  * union()    : O(N^2)
  * connected(): O(1)
  
- WeightedQuickUnionUF:
  * union()    : O(log N)
  * connected(): O(log N)

MEMORY USAGE:
- QuickFindUF       : ~4N^2 bytes
- WeightedQuickUnion: ~8N bytes

5. OBSERVATIONS
---------------
1. Both algorithms produce similar threshold estimates
2. WeightedQuickUnion shows significantly better performance for large N
3. Experimental results match theoretical complexity analysis
4. WeightedQuickUnion is more scalable for large grid sizes

6. SYSTEM REQUIREMENTS
----------------------
- Developed and tested on macOS
- Requires Java 8 or later
- stdlib.jar must be in the same directory