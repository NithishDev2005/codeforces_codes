class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] contains=new boolean[201];

        for(int x:nums){
            contains[x]=true;
        }
        int answer=k;

        while(contains[answer]){
            answer+=k;
        }

        return answer;
    }
}