class Solution {
    public int findIntegers(int n) {

        int[] dp = new int[31];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < 31; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 0;
        int prevBit = 0;

        for (int i = 30; i >= 0; i--) {

            if ((n & (1 << i)) != 0) {

                // Put 0 instead of n's 1
                ans += dp[i];

                // If previous bit was also 1,
                // n contains consecutive 1s
                if (prevBit == 1) {
                    return ans;
                }

                prevBit = 1;

            } else {
                prevBit = 0;
            }
        }

        // n itself is valid
        return ans + 1;
    }
}