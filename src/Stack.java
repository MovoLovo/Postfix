public class Stack{

    // Initialize private array to hold data
    private Object[] arr;

    // Initialize cursor to represent top of stack
    private int cursor = -1;

    // Initialize ArrayStack
    public Stack(){
        arr = new Object[10];
    }

    // Manage list expansions
    private void expand(){
        if(cursor + 1 == arr.length){
            Object[] temp = new Object[arr.length * 2];
            for(int i = 0; i < arr.length; i++){
                temp[i] = arr[i];
            }
            arr = temp.clone();
        }
    }

    // Checks if there is no useful data
    public boolean empty() {
        // If cursor is at -1, then the array is clear of all items
        return cursor == -1;
    }

    // Add object to top of stack
    public void push(Object x) {
        // Expands the array is necessary
        expand();

        // Moves the cursor +1 over and adds the item to the array
        arr[++cursor] = x;
    }

    // Remove top object from stack
    public Object pop() {
        // If stack is empty return null
        // Else return object at cursor position
        // and move cursor over -1
        return !empty() ? arr[cursor--] : null;
    }

    // Show top of stack
    public Object peek() {
        // If is empty return null
        // Else show object at cursor position
        return !empty() ? arr[cursor] : null;
    }

    // Returns the 1 based position of the top object in the stack
    public int search(){
        // 1 based means it starts from 1 and not 0, hence the + 1
        return cursor + 1;
    }
}