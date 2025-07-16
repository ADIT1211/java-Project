import java.util.Scanner;

public class Tic{
    static char[][] board = {
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBoard();

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
            int move = scanner.nextInt();

            if (placeMove(move)) {
                printBoard();
                if (checkWinner()) {
                    System.out.println("🎉 Player " + currentPlayer + " wins!");
                    break;
                } else if (isDraw()) {
                    System.out.println("It's a draw!");
                    break;
                }
                switchPlayer();
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    public static void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static boolean placeMove(int position) {
        int row = 0, col = 0;

        switch (position) {
            case 1: row = 0; col = 0; break;
            case 2: row = 0; col = 2; break;
            case 3: row = 0; col = 4; break;
            case 4: row = 2; col = 0; break;
            case 5: row = 2; col = 2; break;
            case 6: row = 2; col = 4; break;
            case 7: row = 4; col = 0; break;
            case 8: row = 4; col = 2; break;
            case 9: row = 4; col = 4; break;
            default: return false;
        }

        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public static boolean checkWinner() {
        // Horizontal, Vertical, Diagonal checks
        return (checkLine(0, 0, 0, 2, 0, 4) ||  // row 1
                checkLine(2, 0, 2, 2, 2, 4) ||  // row 2
                checkLine(4, 0, 4, 2, 4, 4) ||  // row 3
                checkLine(0, 0, 2, 0, 4, 0) ||  // col 1
                checkLine(0, 2, 2, 2, 4, 2) ||  // col 2
                checkLine(0, 4, 2, 4, 4, 4) ||  // col 3
                checkLine(0, 0, 2, 2, 4, 4) ||  // diag 1
                checkLine(0, 4, 2, 2, 4, 0));   // diag 2
    }

    public static boolean checkLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        return (board[r1][c1] == currentPlayer &&
                board[r2][c2] == currentPlayer &&
                board[r3][c3] == currentPlayer);
    }

    public static boolean isDraw() {
        for (int i = 0; i <= 4; i += 2) {
            for (int j = 0; j <= 4; j += 2) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
