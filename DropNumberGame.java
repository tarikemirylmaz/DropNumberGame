package DropNumberGame;
// Main entry point for the Drop Number Game.

  public class DropNumberGame{

    // Runs the exact example sequence from the project PDF in console mode
    public static void runConsoleExample(){
        MultiLinkedList game = new MultiLinkedList();
        MoveNode moves = getExampleMoves();

        System.out.println("----- DROP NUMBER GAME -----");
        System.out.println("Grid: 5 columns x 7 rows");
        System.out.println("========================\n");
        System.out.println("Initial state (empty grid):");
        game.printGrid();

        int step = 1;
        MoveNode current = moves;
        while(current != null){
            System.out.println("Step " + step + ": Drop [" + current.value + "] into column " + (current.col + 1));
            boolean success = game.dropTile(current.value, current.col);
            if(!success){
                System.out.println(" Column " + (current.col + 1) + " is FULL. Drop failed.");
            }
            game.printGrid();
            // Game ends when any column reaches 7 tiles
            if(game.isGameOver()){
                System.out.println("GAME OVER:  A column is full !!!");
                break;
            }
            current = current.next;
            step++;
        }
        System.out.println("----- END OF EXAMPLE -----");
    }

    // Builds and returns the  move sequence as a singly linked list of MoveNode
    public static MoveNode getExampleMoves(){
        // Dummy head makes appending easier, real list starts at dummy.next
        MoveNode dummy = new MoveNode(0, 0);
        MoveNode tail  = dummy;

        tail = append(tail, 2, 0);
        tail = append(tail, 2, 3);
        tail = append(tail, 4, 1);
        tail = append(tail, 2, 2);
        tail = append(tail, 4, 4);
        tail = append(tail, 2, 1);
        tail = append(tail, 4, 4);
        tail = append(tail, 8, 0);
        tail = append(tail, 8, 0);
        tail = append(tail, 32, 1);
        tail = append(tail, 2, 2);
        tail = append(tail, 64, 2);
        tail = append(tail, 16, 3);
        tail = append(tail, 64, 1);
        tail = append(tail, 32, 2);
        tail = append(tail, 16, 0);
        tail = append(tail, 16, 4);
        tail = append(tail, 32, 2);
        tail = append(tail, 64, 1);
        tail = append(tail, 8,3);
        tail = append(tail, 4, 3);
        tail = append(tail, 2, 3);
        tail = append(tail, 2, 3);
        tail = append(tail, 2, 1);
        tail = append(tail, 64, 2);
        tail = append(tail, 32, 2);
        tail = append(tail, 16, 2);
        tail = append(tail, 8, 2);
        tail = append(tail, 8, 2);
        tail = append(tail, 4, 1);
        tail = append(tail, 8, 1);
        
        return dummy.next;
    }

    // Appends a new MoveNode to the list tail and returns the new tail
    private static MoveNode append(MoveNode tail, int value, int col){
        tail.next = new MoveNode(value, col);
        return tail.next;
    }
}
