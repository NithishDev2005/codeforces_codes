class Solution {
    public int minInsertions(String s) {
        int ope=0,res=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                ope++;
            }

            else{
                if(i+1<s.length()&& s.charAt(i+1)==')'){
                    i++;
                }
                else{
                    res++;
                }

                if(ope>0){
                    ope--;
                }
                else{
                    res++;
                }
            }

            
        }
        return res+ope*2;
    }
}