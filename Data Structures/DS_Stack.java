/**
 * @(#)DS_Stack.java
 * 
 * <h3>	A Stack Data Structure recreation in java. <h3>
 * <p> This project is for learning purposes only, and is not better/more efficient/to be preferred
 * against the pre-defined stack data structure in the java programming language. </p>
 * 
 * @implNote	This stack is designed to be generic so it can be used in <b>any</b> reference types.
 *				sample declarations:
 *				DS_Stack <String> myString = new DS_Stack <String>();
 *				DS_Stack <MyObj> myCustomObject = new DS_Stack <MyObj> (my_length);
 *
 * @author	github.com/jbarry302
 * @version	1.00
 * @since	2021/7/26
 */

public class DS_Stack <T> {
	
	private Object[] stack;
	private final int maxSize;
	private static final int DEFAULT_LENGTH = 1024;
	
	// This pointer will be used to help methods traverse in this stack
	private int pointer = 0;
	
	
	/* Default Constructor length set at 1024 (author preference) */
    public DS_Stack() {
   		this.maxSize = DEFAULT_LENGTH;
   		stack = new Object[maxSize];
    }
    
    /* Constructor for setting a specific size of this stack.
     * @param   maxSize
     *		the maximum capacity that this stack can hold.
     */
    public DS_Stack(int maxSize){
    	if(maxSize < 1) throw new IllegalArgumentException("value must be > 1");
    	
    	this.maxSize = maxSize;
    	stack = new Object[maxSize];
    }
    
    /* Adds an element at the top of this stack.
     * @param       	element
     *	            	the object to be added at the top of the stack
     * @exception	IndexOutOfBoundsException
     *			when you try to call this method and the {@code maxSize} limit is reached.
     */
    public void push(Object element){
    	ensureNonNull();
    	if(pointer >= maxSize)
    		throw new IndexOutOfBoundsException(String.format("Stack is full, trying to add %d element%s to size %d", pointer+1, (pointer == 0? "":"s"),maxSize));
    	
    	stack[pointer++] = element;
    }
    
    /* Removes the current element at the top of this stack.
     * @return		the current element at the top of this stack
     * @exception	IndexOutOfBoundsException
     *			if you try to call this method in an empty stack.
     */
    public Object pop(){
    	ensureNonNull();
    	if(pointer <= 0)
    		throw new IndexOutOfBoundsException("Cannot pop on empty stack");
    		
    	return stack[--pointer];
    }
    
    /* @return		the current element at the top of this stack.
     * @exception	if you try to call this method in an empty stack.*/
    public Object peek(){
    	ensureNonNull();
    	if(this.isEmpty())
    		throw new IndexOutOfBoundsException("Cannot peek on empty stack");
    		
    	return stack[pointer-1];
    }
    
    /* @return	the current size of this stack. */
    public int size(){
    	ensureNonNull();
    	return pointer;
    }
    
    /* @return	true if this stack is empty, false if not. */
    public boolean isEmpty(){
    	ensureNonNull();
    	return maxSize <= 0;
    }
    
    /* @param		value
     *			the element to be searched in the stack
     * @return		the index of the {@param value} if it exists in the stack,
     *			-1 if it does not exist.
     * @exception	IndexOutOfBoundsException
     *			if you try to use this method in an empty stack.*/
    public int indexOf(Object value){
    	ensureNonNull();
    	if(pointer < 1)
    		throw new IndexOutOfBoundsException("Empty Stack.");
    		
    	for(int i = 0; i < pointer; i++){
    		if(stack[i].equals(value)){
    			return i;
    		}
    	}
    	return -1;
    }
    
    /* Utility methods */
    @Override
    public String toString(){
    	if(this == null) return "null";
    	
    	StringBuilder sb = new StringBuilder("[");
    	for(int i = 0; i < pointer; i++){
    		if(i < pointer-1){
    			sb.append((""+stack[i]+", "));
    		} else {
    			sb.append((""+stack[i]));
    		}
    	}
    	return sb.append("]").toString();
    }
    
    /* Ensures that this stack cannot use the possible methods above if declared as null. */
    public void ensureNonNull(){
    	if(this == null)
    		throw new NullPointerException("Stack is null.");
    }
    
}
