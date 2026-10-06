package DropNumberGame;

public class MultiLinkedList{
    public static final int COLUMNS = 5;
    public static final int ROWS = 7;

    // Points to the first dummy column head node
    private Node head;

    // Constructor: Create dummy head nodes for each column, linked horizontally
    public MultiLinkedList(){        
        head = new Node(0, -1, 0);
        Node current = head;
        for(int c = 1; c < COLUMNS; c++){
            Node newCol = new Node(0, -1, c);
            current.nextCol = newCol;
            current = newCol;
        }
    }

    // Returns the dummy head node of the given column
    private Node getColumnHead(int col){
        Node current = head;
        for(int i = 0; i < col; i++){
            current = current.nextCol;
        }
        return current;
    }

    // Counts how many real tiles are in the given column
    public int getColumnCount(int col){
        Node current = getColumnHead(col).down;
        int count = 0;
        while(current != null){
            count++;
            current = current.down;
        }
        return count;
    }

    // Returns true if the column has no more space for new tiles
    public boolean isColumnFull(int col){
        return getColumnCount(col) >= ROWS;
    }

    // Returns true if any column in the grid is full
    public boolean isGameOver(){
        for(int i = 0; i < COLUMNS; i++){
            if(isColumnFull(i)){
                return true;
            }
        }
        return false;
    }

    // Drops a new tile into the bottom of the given column and triggers merge
    public boolean dropTile(int value, int col){
        if(isColumnFull(col)){
            return false;
        }

        Node colHead = getColumnHead(col);
        Node newNode = new Node(value, -1, col);

        if(colHead.down == null){
            // Column is empty, place the tile directly under the dummy head
            colHead.down = newNode;
        }else{
            // Walk to the last  tile and append the new tile
            Node tail = colHead.down;
            while(tail.down != null){
                tail = tail.down;
            }
            tail.down = newNode;
        }

        updateRowPositions(col);
        checkAndMerge(col);
        return true;
    }

  
    // Recalculates row indices so tiles are packed from bottom to top
    private void updateRowPositions(int col){
        Node current = getColumnHead(col).down;
        int row = 0; 

        while(current != null){
        current.row = row;
        row++;
        current = current.down;
        }
    }

    // Walks to the tail and merges adjacent equal tiles upward 
    private void checkAndMerge(int col){
        Node colHead = getColumnHead(col);
        if(colHead.down == null){
            return;
        }
        boolean merged = true;
        while(merged){
            merged = false;

            Node prev = colHead;
            Node curr = colHead.down;
            while(curr != null && curr.down != null){
                prev = curr;
                curr = curr.down;
            }
            // curr is now the tail, prev is the node above it
            if(prev == colHead){
                return;  // only one tile, nothing to merge
            } 
            // Use clear and meaningful variable names
            Node aboveTail = prev;
            Node tail = curr;

            if(aboveTail.value == tail.value){
                // Merge tail into aboveTail by doubling its value and removing tail
                aboveTail.value *= 2;
                aboveTail.down = null;
                updateRowPositions(col);
                merged = true; 
            }
        }
    }

    // Returns the tile value at the given row and column, or 0 if empty
    public int getTileValue(int row, int col){
        Node current = getColumnHead(col).down;
        while(current != null){
            if(current.row == row){
                return current.value;
            }
            current = current.down;
        }
        return 0;
    }     

    // Returns the topmost real tile node of the given column
    public Node getColumnHeadNode(int col){
        return getColumnHead(col).down;
    }
    
    // Prints the grid on the screen from top to bottom with tile values
    public void printGrid(){
        System.out.println("Current Grid:");
        System.out.println("--------------------------------");

        for(int row = ROWS - 1; row >= 0; row--){
            for(int col = 0; col < COLUMNS; col++){
                int val = getTileValue(row, col); 

            if(val == 0){
                System.out.printf("%5s", ".");
            }else{
                System.out.printf("%5d", val);
            }
        }

        System.out.println();
    }

    System.out.println("--------------------------------\n");
    }
}
