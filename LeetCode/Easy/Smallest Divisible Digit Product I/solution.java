class Solution {
    public int smallestNumber(int n, int t) {
        while(product(n)%t!=0){
            n++;
        }
        return n;
    }

    private int product(int num){
        int p=1;
        while(num!=0){
            p*=num%10;
            num/=10;
        }
        return p;
    }
}