class Solution {
    public int stoneGameV(int[] stoneValue) {
        int dp[][] = new int[stoneValue.length][stoneValue.length];
        for(int i[]:dp){
            Arrays.fill(i,-1);
        }
        return solve(0,stoneValue.length-1,stoneValue,dp);
    }

    public int solve(int i,int j,int arr[],int dp[][]){
        if(i>=j) return 0;

        if(dp[i][j]!=-1)    return dp[i][j];
        int total=0;

        for(int k=i;k<=j;k++){
            total+=arr[k];
        }

        int ans=0;
        int left=0;
        for(int k=i;k<=j;k++){
            left+=arr[k];
            int right=total-left;


            if(left<right){
                ans=Math.max(ans,left+solve(i,k,arr,dp));
            }

            else if(left>right)
            {
                ans=Math.max(ans,right+solve(k+1,j,arr,dp));
            }
            else
            {
                ans=Math.max(ans,left+Math.max(solve(i,k,arr,dp),solve(k+1,j,arr,dp)));
            }

        }
        return dp[i][j]=ans;
    }
}