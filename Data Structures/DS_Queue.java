/**
 * @(#)DS_Queue.java
 *
 * <h3>	A Queue Data Structure recreation in java. <h3>
 * <p> This project is for learning purposes only, and is not better/more efficient/to be preferred
 * against the pre-defined Queue data structure in the java programming language. </p>
 * 
 * @implNote	This queue is designed to be generic so it can be used in <b>any</b> reference types.
 *		sample declarations:
 *			DS_Queue <String> myString = new DS_Queue <String>();
 *			DS_Queue <MyObj> myCustomObject = new DS_Queue <MyObj> (my_length);
 *
 * @author	github.com/jbarry302
 * @version	1.00
 * @since	2021/8/01
 */
import java.util.*;

public class DS_Queue <T> {
	
	private Object[] queue;
	private final int MAX_SIZE;
	private static final int DEFAULT_LENGTH = 1024;
	
	//shift thresold is set to 30% of the queue operation in both insert and remove
	private final int SHIFT_THRESOLD;
	private boolean clear_flag = false;

	
	//the {@code start_pointer} will be used to represent the elements at the start of this queue (aka rear)
	//the {@code end_pointer} will be used to represent the elements at the end of this queue (aka front)
	private int start_pointer;
	private int end_pointer;
	private int dynamic_length;
	

    /* Default Constructor length set at 1024 (author preference) */
    public DS_Queue() {
    	MAX_SIZE 	= DEFAULT_LENGTH;
    	SHIFT_THRESOLD	= (int)(MAX_SIZE * 0.3);
    	
    	queue		= new Object[MAX_SIZE];
    	start_pointer	= MAX_SIZE-1;
    	end_pointer	= MAX_SIZE-1;
    	dynamic_length	= 0;
    }
    
    /* Constructor for setting a specific size of this queue.
     * @param   MAX_SIZE
     *		the maximum capacity that this queue can hold.
     */
    public DS_Queue(int MAX_SIZE) {
    	if(MAX_SIZE < 1) 
    		throw new IllegalArgumentException("value must be > 0");
    	
    	this.MAX_SIZE	= MAX_SIZE;
    	SHIFT_THRESOLD	= (int)(MAX_SIZE * 0.3);

    	queue		= new Object[MAX_SIZE];
    	start_pointer	= MAX_SIZE-1;
    	end_pointer	= MAX_SIZE-1;
    	dynamic_length	= 0;
    }
    
    
    /* Adds an element at the top of this queue.
     * @param       	element
     *	            	inserts an element at the start of this queue
     * @exception	IndexOutOfBoundsException
     */
    public void insert(Object element){
    	ensureNonNull();
    	if(dynamic_length >= MAX_SIZE) {
    		throw new IndexOutOfBoundsException(String.format("Queue is full, trying to add %d element%s to size %d", dynamic_length+1, (dynamic_length <= 1? "":"s"),MAX_SIZE));
    	}
    	
    	if(start_pointer <= SHIFT_THRESOLD && clear_flag)
    		shiftRight();
    	
    	queue[start_pointer--] = element;
    	++dynamic_length;
    }
    
    /* Removes the current element at the bottom of this queue.
     * @return		the current element at the bottom of this queue.
     * @exception	IndexOutOfBoundsException
     */
    public Object remove() {
    	ensureNonNull();
    	if(dynamic_length < 1) 
    		throw new IndexOutOfBoundsException("Empty Queue.");
    	
    	if(MAX_SIZE - end_pointer >= SHIFT_THRESOLD)
    		clear_flag = true;
    	
    	dynamic_length--;
    	return queue[end_pointer--];
    }
    public Object poll() {
    	return remove();
    }
    
    /* @return		the current element at the bottom of this queue.
     * @exception	IndexOutOfBoundsException
     */
    public Object peekFirst() {
    	ensureNonNull();
    	if(dynamic_length == 0) 
    		throw new IndexOutOfBoundsException("Empty Queue.");	
    	
    	return queue[end_pointer];
    }
    
    /* @return		the current element at the start of this queue.
     * @exception	IndexOutOfBoundsException
     */
    public Object peekLast() {
    	ensureNonNull();
    	if(dynamic_length == 0)
    		throw new IndexOutOfBoundsException("Empty Queue.");
    		
    	return queue[start_pointer+1];
    }
    
    /* @return	true if this queue is full, false if not. */
    public boolean isFull() {
    	ensureNonNull();
    	return dynamic_length >= MAX_SIZE;
    }
    
    /* @return	true if this queue is empty, false if not. */
    public boolean isEmpty() {
    	ensureNonNull();
    	return dynamic_length == 0;
    }
    
    /* @return	the current size of this queue. */
    public int size(){
    	ensureNonNull();
    	return dynamic_length;
    }
    
    /* This method will be used to clear empty spaces in the queue
     * because of how the insert and remove operation is implemented.
     * When the {@code remove()} is called the queue size shrinks but that
     * element is still at the background and we are just not allowed to see it.
     *
     * @implNote	{@code shiftRight()} will not be triggered unless
     *			30% of the queue is left and atleast 30% of the element
     *			in the queue is removed.
     */
    private final void shiftRight() {
    	int length = MAX_SIZE;
    	Object[] duplicate = new Object[length];
	    
    	for(int i = start_pointer+1; i <= end_pointer; i++) {
    		duplicate[--length] = queue[i];
    	}
	    
    	this.queue = duplicate;
    	end_pointer = MAX_SIZE-1;
    	start_pointer = length;
    }
    
    /* Utility methods */
    @Override
    public String toString(){
    	if(this == null) return "null";
    	
    	StringBuilder sb = new StringBuilder("[");
    	
    	//for(int i = start_pointer+1; i <= end_pointer; i++){
    	//	if(i < end_pointer) {
    	//		sb.append(queue[i] + ", ");
    	//	} else  {
    	//		sb.append(queue[i]+"");
    	//	}
    	//}
    	
    	for(int i = end_pointer; i >= start_pointer+1; i--){
    		if(i > start_pointer+1) {
    			sb.append(queue[i] + ", ");
    		} else  {
    			sb.append(queue[i]+"");
    		}
    	}
    	
    	return sb.append("]").toString();
    }
    /* Ensures that this queue cannot use the possible methods above if declared as null. */
    private final void ensureNonNull(){
    	if(this == null) 
    		throw new NullPointerException("Queue is null.");
    }
    
}

