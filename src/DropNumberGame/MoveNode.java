package DropNumberGame;
 
// Singly linked list node representing one drop move 
public class MoveNode{
    public int value;
    public int col;
    public MoveNode next;
    
    // Constructor:  to create a move with value and column
    public MoveNode(int value, int col){
        this.value = value;
        this.col   = col;
        this.next  = null;
    }
}
