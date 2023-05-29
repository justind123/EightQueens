class EightQueens {

    private static int[][] board = new int[8][8];

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

    private static int solveHelper() {
        return solve(0, 0);
    }

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

    public static void main(String[] args) {
        int solutions = solveHelper();
        String out = String.format("%d solutions", solutions);
        System.out.println(out);
    }
}