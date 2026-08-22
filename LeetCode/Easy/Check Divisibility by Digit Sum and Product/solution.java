class Solution {
    public boolean checkDivisibility(int n) {
        int digit_sum=0;
        int digit_pro=1;
        int ori=n;

        while(n>0){
            int digit=n%10;
            n/=10;

            digit_sum+=digit;
            digit_pro*=digit;
        }
        return ori%(digit_sum+digit_pro)==0;
    }
}