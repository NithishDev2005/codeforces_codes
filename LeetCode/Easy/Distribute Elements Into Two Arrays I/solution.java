class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer>l1=new ArrayList<>();
        List<Integer>l2=new ArrayList<>();
        l1.add(nums[0]);
        l2.add(nums[1]);
        int n=nums.length;


        for(int i=2;i<n;i++){
            int ll1=l1.get(l1.size()-1);
            int ll2=l2.get(l2.size()-1);
            if(ll1>ll2)
                l1.add(nums[i]);
            else
                l2.add(nums[i]);
        }

        int res[]=new int[n];
        int index=0;
        for(int x:l1)
            res[index++]=x;
        for(int x:l2)
            res[index++]=x;
        return res;
        }
}