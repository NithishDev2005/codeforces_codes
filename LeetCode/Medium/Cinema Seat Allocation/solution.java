

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats of each row using bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Rows without reservations can accommodate 2 families
        int ans = (n - map.size()) * 2;

        // Possible family seat blocks
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        for (int mask : map.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;

            if (leftFree && rightFree) {
                ans += 2;
            } else if (leftFree || middleFree || rightFree) {
                ans += 1;
            }
        }

        return ans;
    }
}