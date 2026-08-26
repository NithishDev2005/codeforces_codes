class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            StringBuilder current = new StringBuilder();

            for (int j = i; j < n; j++) {
                current.append(s.charAt(j));

                if (s.charAt(j) == '1') {
                    cnt++;
                }

                if (cnt > k) {
                    break;
                }

                if (cnt == k) {
                    String currentStr = current.toString();

                    if (ans.isEmpty()
                        || currentStr.length() < ans.length()
                        || (currentStr.length() == ans.length()
                            && currentStr.compareTo(ans) < 0)) {

                        ans = currentStr;
                    }
                }
            }
        }

        return ans;
    }
}