class Solution {
    public long maximumTotalCost(int[] nums) {

        long pos = nums[0];
        long neg = Long.MIN_VALUE / 2;

        for (int i = 1; i < nums.length; i++) {

            long newPos = Math.max(pos, neg) + nums[i];

            long newNeg = pos - nums[i];

            pos = newPos;
            neg = newNeg;
        }

        return Math.max(pos, neg);
    }
}