class Solution {

    public int splitArray(int[] nums, int k) {

        int left = 0;
        int right = 0;

        // Find search range
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                // mid is possible
                // Try to find a smaller answer
                right = mid - 1;
            } else {
                // mid is impossible
                // Need a larger limit
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSplit(int[] nums, int k, int limit) {

        int parts = 1;
        int currentSum = 0;

        for (int num : nums) {

            if (currentSum + num > limit) {

                // Start a new subarray
                parts++;
                currentSum = num;

            } else {

                currentSum += num;
            }
        }

        return parts <= k;
    }
}