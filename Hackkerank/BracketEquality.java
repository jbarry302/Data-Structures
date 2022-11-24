/**
 * @(#)BracketEquality.java
 * 
 * Check if given bracket expr is equal or not.
 *
 * @author github.om/jbarry302
 * @version 1.00 2022/11/25
 */

import java.util.Stack;

public class BracketEquality {

    public static void main(String args[]) {
        Stack<Character> ss  = new Stack<Character>();
        String e = "(({()})))";
		
	for(String input : e.split("")) {
            char c = input.charAt(0);
            switch(c) {
                case '(': 
                case '{': 
                case '[': {
                    ss.push(c);
                    break;
                }
                default: {
                    if(!ss.isEmpty()) {
                        //System.out.println("peekval: " + ss.peek() + " = c: " + c);
                        if(isPartner(ss.peek(), c)) {
                            ss.pop();
                        } else {
                            ss.push(c);
                        }
                    } else ss.push(c);
                    break;
                }
            }
	}
        
        System.out.println(ss.isEmpty());
    }
    
    public static boolean isPartner(char x, char y) {
        if(x == '{') {
            return y == '}';
        }
        if(x == '(') {
            return y == ')';
        }
        if(x == '[') {
            return y == ']';
        }
        return false;
    }
    
    
}
