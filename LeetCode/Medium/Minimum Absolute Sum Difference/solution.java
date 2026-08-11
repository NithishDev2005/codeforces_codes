class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        int MOD = 1000000007;

        // Copy nums1 and sort it
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);

        long total = 0;
        int maxSaving = 0;

        for (int i = 0; i < nums1.length; i++) {

            // Original difference
            int current = Math.abs(nums1[i] - nums2[i]);

            total += current;

            // Find first element >= nums2[i]
            int index = lowerBound(sorted, nums2[i]);

            // Check element on right
            if (index < sorted.length) {
                int newDiff =
                    Math.abs(sorted[index] - nums2[i]);

                int saving = current - newDiff;

                maxSaving = Math.max(maxSaving, saving);
            }

            // Check element on left
            if (index > 0) {
                int newDiff =
                    Math.abs(sorted[index - 1] - nums2[i]);

                int saving = current - newDiff;

                maxSaving = Math.max(maxSaving, saving);
            }
        }

        return (int)((total - maxSaving) % MOD);
    }

    private int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}