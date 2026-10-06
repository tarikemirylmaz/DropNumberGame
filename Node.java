package DropNumberGame;
/*
 Node class for Multi Linked List.
 Each node represents one tile on the grid.
*/
public class Node{
    int value;      // tile value (2, 4, 8, 16, ...)
    int row;        // row position (0 = top)
    int col;        // column position (0 = leftmost)
    Node down;      // next node below in same column
    Node nextCol;   // pointer to head of next column 
    
    // Constructor
    public Node(int value, int row, int col){
        this.value   = value;
        this.row     = row;
        this.col     = col;
        this.down    = null;
        this.nextCol = null;
    }
}
