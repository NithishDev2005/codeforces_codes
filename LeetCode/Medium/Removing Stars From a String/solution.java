class Solution {
    public String removeStars(String s) {
        Stack<Character>stk=new Stack<>();

        for(char c:s.toCharArray()){
            if(c=='*'){
                if(!stk.isEmpty()){
                    stk.pop();
                }
            }
                else{
                    stk.push(c);
                }
            
        }

        StringBuilder res =new StringBuilder();
        for(char c:stk){
            res.append(c);
        }

        return res.toString();
    }
    
}