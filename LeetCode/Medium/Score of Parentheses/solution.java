class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stk=new Stack<>();
        stk.push(0);

        for(char c:s.toCharArray()){
            if(c=='('){
                stk.push(0);
            }
            else{
                int v=stk.pop();
                int w=stk.pop();
                stk.push(w+Math.max(2*v,1));
            }
        }
        return stk.pop();
    }
}