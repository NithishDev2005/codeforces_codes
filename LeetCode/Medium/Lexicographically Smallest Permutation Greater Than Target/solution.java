class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];
        
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;
        for (char ch : target.toCharArray()) cnt[ch - 'a']--;

        // Label the outer loop so we can skip directly to the next iteration
        outer:
        for (int i = target.length() - 1; i >= 0; i--) {
            int cur = target.charAt(i) - 'a';
            cnt[cur]++; // Put the character back into the pool

            // 1. Check if prefix is valid. If any count is negative, skip to next 'i'
            for (int x : cnt) {
                if (x < 0) continue outer; 
            }

            // 2. Find the smallest available character strictly greater than 'cur'
            for (int c = cur + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    cnt[c]--; // Use this character
                    
                    // 3. Build and return the result immediately
                    StringBuilder ans = new StringBuilder(target.substring(0, i));
                    ans.append((char) ('a' + c));
                    
                    // Append remaining characters in alphabetical order
                    for (int k = 0; k < 26; k++) {
                        while (cnt[k]-- > 0) {
                            ans.append((char) ('a' + k));
                        }
                    }
                    
                    return ans.toString();
                }
            }
        }
        return "";
    }
}