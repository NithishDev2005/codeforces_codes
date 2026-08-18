class Solution(object):
    def largestInteger(self, nums, k):
        f=[0]*51
        for x in nums:
            f[x]+=1

        res,n=-1,len(nums)
        for i,c in enumerate(nums):
            if k==n or(f[c]==1 and (k==1 or not i or i+1==n)):
                res=max(res,c)
        return res
        