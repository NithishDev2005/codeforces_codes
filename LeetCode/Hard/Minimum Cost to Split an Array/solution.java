class Solution {
    public int minCost(int[] nums, int k) {

        int n = nums.length;

        int[] dp = new int[n + 1];

        // dp[i] = minimum cost for first i elements
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            int[] freq = new int[n + 1];

            int trimmedLength = 0;

            // Try all possible starting positions
            for (int j = i; j >= 1; j--) {

                int x = nums[j - 1];

                freq[x]++;

                if (freq[x] == 1) {
                    // Unique element
                    // It will be removed, so add nothing
                }
                else if (freq[x] == 2) {
                    // It was unique before.
                    // Now both occurrences remain.
                    trimmedLength += 2;
                }
                else {
                    // Additional occurrence
                    trimmedLength++;
                }

                int cost = dp[j - 1] + k + trimmedLength;

                dp[i] = Math.min(dp[i], cost);
            }
        }

        return dp[n];
    }
}