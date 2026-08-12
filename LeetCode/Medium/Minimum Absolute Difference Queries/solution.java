class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {

        int n = nums.length;

        // prefix[i][x] = number of x's in nums[0 ... i-1]
        int[][] prefix = new int[n + 1][101];

        for (int i = 0; i < n; i++) {

            // Copy previous frequencies
            for (int x = 1; x <= 100; x++) {
                prefix[i + 1][x] = prefix[i][x];
            }

            prefix[i + 1][nums[i]]++;
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int l = queries[q][0];
            int r = queries[q][1];

            int prev = -1;
            int minDiff = Integer.MAX_VALUE;

            // Check every possible value 1...100
            for (int x = 1; x <= 100; x++) {

                // Frequency of x in nums[l...r]
                int freq = prefix[r + 1][x] - prefix[l][x];

                if (freq > 0) {

                    if (prev != -1) {
                        minDiff = Math.min(minDiff, x - prev);
                    }

                    prev = x;
                }
            }

            // If only one distinct value exists
            if (minDiff == Integer.MAX_VALUE) {
                ans[q] = -1;
            } else {
                ans[q] = minDiff;
            }
        }

        return ans;
    }
}