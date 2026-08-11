class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt=new int[26];

        int left=0;
        

        int max_fre=0;
        int max_len=0;

        for(int right=0;right<s.length();right++){
            char curr=s.charAt(right);

            cnt[curr-'A']++;

            max_fre=Math.max(max_fre,cnt[curr-'A']);

            int window=right-left+1;

            while(window-max_fre>k){
                cnt[s.charAt(left)-'A']--;

                left++;

                window=right-left+1;
            }

            max_len=Math.max(window,max_len);

           
        }
        return max_len;
    }
}