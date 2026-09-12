import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // 1. Pack [start, end, weight, original_index] and sort by start time
        int[][] a = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> in = intervals.get(i);
            a[i] = new int[]{in.get(0), in.get(1), in.get(2), i};
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        // 2. DP tables: dpW stores weights, dpId stores sorted chosen indices
        long[][] dpW = new long[n + 1][5];
        int[][][] dpId = new int[n + 1][5][];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) dpId[i][k] = new int[0];
        }

        // 3. Suffix DP
        for (int i = n - 1; i >= 0; i--) {
            // Binary search: first interval starting strictly after current ends
            int l = i + 1, r = n;
            while (l < r) {
                int mid = (l + r) / 2;
                if (a[mid][0] > a[i][1]) r = mid;
                else l = mid + 1;
            }
            int nxt = l;

            for (int k = 1; k <= 4; k++) {
                // Option A: Skip
                dpW[i][k] = dpW[i + 1][k];
                dpId[i][k] = dpId[i + 1][k];

                // Option B: Pick
                long pW = a[i][2] + dpW[nxt][k - 1];
                int[] prev = dpId[nxt][k - 1];
                int[] pId = Arrays.copyOf(prev, prev.length + 1);
                pId[prev.length] = a[i][3];
                Arrays.sort(pId);

                // Compare weights or break ties lexicographically
                if (pW > dpW[i][k] || (pW == dpW[i][k] && Arrays.compare(pId, dpId[i][k]) < 0)) {
                    dpW[i][k] = pW;
                    dpId[i][k] = pId;
                }
            }
        }

        return dpId[0][4];
    }
}