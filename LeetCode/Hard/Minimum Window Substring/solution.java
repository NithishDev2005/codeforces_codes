class Solution {
    public String minWindow(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }

        HashMap<Character,Integer>need=new HashMap<>();

        for(char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }

        HashMap<Character,Integer>window=new HashMap<>();


        int left=0;
        int formed=0;
        int required=need.size();

        int min_len=Integer.MAX_VALUE;
        int start=0;

        for(int right=0;right<s.length();right++){
            char c=s.charAt(right);

            window.put(c,window.getOrDefault(c,0)+1);

            if(need.containsKey(c)&& window.get(c).intValue()==need.get(c).intValue()){
                formed++;
            }

            while(left<=right&&formed==required){
                if(right-left+1<min_len){
                    min_len=right-left+1;
                    start=left;
                }

                char leftchar=s.charAt(left);

                window.put(leftchar,window.get(leftchar)-1);

                if(need.containsKey(leftchar)&&window.get(leftchar)<need.get(leftchar)){
                    formed--;
                }
                left++;
            }
        }

        if(min_len==Integer.MAX_VALUE){
            return "";
        }
        return s.substring(start,start+min_len);
    }
}