class EightQueens {

    // 8x8 two dimensional array representing the chess board
    private static int[][] board = new int[8][8];

    /*
     * Prints the chessboard. Prints "_" if the space is empty.
     * Prints "Q" if the space has a queen.
     */
    private static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            String line = "";
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    line += "_";
                }
                else {
                    line += "Q";
                }
            }
            System.out.println(line);
        }
        System.out.println();
    }

    /*
     * Using recursive backtracking, iterates through the rows
     * top to bottom finding a valid move to place a queen. If
     * it is a valid move, it places the queen and recurses. If
     * not a valid move, continues iterating through the columns.
     * Returns when eight queens have been placed, keeping track 
     * of the number of solutions found.
     */
    private static int solve(int currRow, int solutions) {
        if (currRow == board.length) {
            solutions++;
            printBoard();
            return solutions;
        }
        for (int j = 0; j < board.length; j++) {
            boolean isValidMove = isValid(j, currRow);
            if (isValidMove) {
                board[currRow][j] = 1;
                solutions = solve(currRow + 1, solutions);
                board[currRow][j] = 0;
           }
        }

        return solutions;
    }

    /*
     * Helper function for solve(), passes in the starting row and
     * the number of solutions, which are both 0 at the start.
     */
    private static int solveHelper() {
        return solve(0, 0);
    }

    /*
     * Checks the passed in board x,y position to ensure 
     * no other queen would threaten this move. Checks vertical,
     * horizontal, and both diagonals. Diagonals only check from the current
     * x,y position looking to the top of the board because we are iterating through
     * the rows, and all existing queens are placed above current position.
     */
    private static boolean isValid(int x, int y) {
        
        // Vertical check
        for (int i = 0; i < y; i++) {
            if (board[i][x] == 1) return false;
        }

        // Horizontal check
        for (int i = 0; i < x; i++) {
            if (board[y][i] == 1) return false;
        }

        // Diagonal check, current position to top left
        for (int i = y, j = x; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Diagonal check, current position to top right
        for (int i = y, j = x; i >=0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    /*
     * Main method. Calls solveHelper(), and prints out the return value
     * for the number of solutions found.
     */
    public static void main(String[] args) {
        int solutions = solveHelper();
        String out = String.format("%d solutions", solutions);
        System.out.println(out);
    }
}