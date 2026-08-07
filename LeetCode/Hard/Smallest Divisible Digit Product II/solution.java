class Solution {
    public String smallestNumber(String num, long t) {
        int[] req = new int[4];
        int[] primes = {2, 3, 5, 7};

        // Factorize t
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                req[i]++;
                t /= primes[i];
            }
        }

        // Impossible if t has any other prime factor
        if (t != 1) return "-1";

        int[][] f = {
            {0,0,0,0}, // 0
            {0,0,0,0}, // 1
            {1,0,0,0}, // 2
            {0,1,0,0}, // 3
            {2,0,0,0}, // 4
            {0,0,1,0}, // 5
            {1,1,0,0}, // 6
            {0,0,0,1}, // 7
            {3,0,0,0}, // 8
            {0,2,0,0}  // 9
        };

        int n = num.length();

        // Factors contributed by valid prefix
        int[][] pre = new int[n + 1][4];
        boolean[] valid = new boolean[n + 1];
        valid[0] = true;

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';

            valid[i + 1] = valid[i] && d != 0;

            for (int j = 0; j < 4; j++) {
                pre[i + 1][j] = pre[i][j];

                if (d != 0)
                    pre[i + 1][j] += f[d][j];
            }
        }

        // num itself already works
        if (valid[n] && enough(pre[n], req))
            return num;

        // Change one position from right to left
        for (int pos = n - 1; pos >= 0; pos--) {

            if (!valid[pos])
                continue;

            int cur = num.charAt(pos) - '0';

            for (int d = Math.max(1, cur + 1); d <= 9; d++) {

                int[] rem = new int[4];

                for (int j = 0; j < 4; j++) {
                    rem[j] = Math.max(
                        0,
                        req[j] - pre[pos][j] - f[d][j]
                    );
                }

                int len = n - pos - 1;

                String suffix = buildSuffix(len, rem, f);

                if (suffix != null) {
                    return num.substring(0, pos)
                            + d
                            + suffix;
                }
            }
        }

        // Need a longer number
        int len = n + 1;

        while (true) {
            String ans = buildSuffix(len, req, f);

            if (ans != null)
                return ans;

            len++;
        }
    }

    private String buildSuffix(int len, int[] need, int[][] f) {

        int[] rem = need.clone();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {

            boolean found = false;

            // Try smallest digit first
            for (int d = 1; d <= 9; d++) {

                int[] next = new int[4];

                for (int j = 0; j < 4; j++) {
                    next[j] = Math.max(
                        0,
                        rem[j] - f[d][j]
                    );
                }

                int left = len - i - 1;

                if (possible(next, left)) {
                    sb.append(d);
                    rem = next;
                    found = true;
                    break;
                }
            }

            if (!found)
                return null;
        }

        for (int x : rem)
            if (x > 0)
                return null;

        return sb.toString();
    }

    private boolean possible(int[] r, int len) {

        // 5 and 7 each require their own digit
        if (r[2] + r[3] > len)
            return false;

        int fixed = r[2] + r[3];
        int available = len - fixed;

        int a = r[0];
        int b = r[1];

        int best = Integer.MAX_VALUE;

        // Try number of digit 6's
        for (int six = 0; six <= Math.min(a, b); six++) {

            int x = a - six;
            int y = b - six;

            int cnt = six;

            // powers of 2 → 8,4,2
            cnt += (x + 2) / 3;

            // powers of 3 → 9,3
            cnt += (y + 1) / 2;

            best = Math.min(best, cnt);
        }

        return best <= available;
    }

    private boolean enough(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i])
                return false;
        }

        return true;
    }
}