class Solution {
    int mod = 1000000007;
    public int numberOfGoodSubarraySplits(int[] nums) {
        int n = nums.length;
        int count=0;
        for(int i=0;i<n;i++)
            {
                if(nums[i]==1)count++;
            }
        if(count==1 || count==0)return count;
        int idx1=0,idx2=0;
        for(int i=0;i<n;i++)
            {
                if(nums[i]==1)
                {
                    idx1=i;
                    break;
                }
            }
        for(int i=n-1;i>=0;i--)
            {
                if(nums[i]==1)
                {
                    idx2=i;
                    break;
                }
            }
            long ans=1;
            int zeroes=0;
        for(int i=idx1+1;i<=idx2;i++)
        {
            if(nums[i]==0)zeroes++;
            else
            {
                ans = (ans*(zeroes+1))%mod;
                zeroes=0;
            }
        }
        return (int)ans;
    }
}