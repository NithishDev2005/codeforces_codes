class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result=new ArrayList<>();

        boolean[] cont=new boolean[101];

        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;


        for(int ele:nums){
            min=Math.min(min,ele);
            max=Math.max(max,ele);

            cont[ele]=true;
        }


        for(int i=min;i<=max;i++){
            if(!cont[i]){
                result.add(i);
            }
        }
        return result;
    }
}