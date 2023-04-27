import java.util.*;

class Game {
    static char[][] board;

    public Game() {
        board = new char[3][3];
        insideboard();
    }

    // creation of the board
    void insideboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // display the board
    static void displayboard() {
        System.out.println("----------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "| ");
            }
            System.out.println();
            System.out.println("----------");
        }
    }

    // placing a sign
    static void Place_A_Sign(int row, int col, char sign) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = sign;

        } else {
            System.out.println("Move not Possible!");

        }
    }

    // Checking for winners-Row,Column.Diagonal
    static boolean Check_Col_Winner() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean Check_Row_Winner() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean Check_Diagonal_winner() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Checking for game draw
    static boolean Check_Draw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

// Parent class for human player and Computer player
abstract class Player {
    String name;
    char sign;

    abstract void To_Make_Move();

    boolean Is_valid_move(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (Game.board[row][col] == ' ') {
                return true;
            }

        }
        return false;
    }
}

class HumanPlayer extends Player {

    HumanPlayer(String name, char sign) {
        this.name = name;
        this.sign = sign;
    }

    void To_Make_Move() {
        Scanner in = new Scanner(System.in);
        int row, col;
        do {
            System.out.println("Enter row and column: ");
            row = in.nextInt();
            col = in.nextInt();
        } while (!Is_valid_move(row, col));
        Game.Place_A_Sign(row, col, sign);
    }

}

class CompPlayer extends Player {

    CompPlayer(String name, char sign) {
        this.name = name;
        this.sign = sign;
    }

    void To_Make_Move() {
        Scanner in = new Scanner(System.in);
        int row, col;
        do {
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!Is_valid_move(row, col));
        Game.Place_A_Sign(row, col, sign);
    }

}

/**
 * tictactoe
 */
public class TicTacToe {
    // Method for human player vs human player
    static void HumanvsHuman(String name1, String name2) {
        HumanPlayer p1 = new HumanPlayer(name1, 'X');
        HumanPlayer p2 = new HumanPlayer(name2, 'O');
        HumanPlayer player;
        player = p1;
        while (true) {
            System.out.println(player.name + " turn");
            player.To_Make_Move();
            Game.displayboard();
            if (Game.Check_Col_Winner() || Game.Check_Row_Winner() || Game.Check_Diagonal_winner()) {
                System.out.println(player.name + " has won");
                break;
            } else if (Game.Check_Draw()) {
                System.out.println("Game is a draw.");
            } else {
                if (player == p1) {
                    player = p2;
                    System.out.println(player.name + " make the next move");
                } else {
                    player = p1;
                }
            }
        }

    }

    // Method for Human player vs Computer player
    static void ComputervsHuman(String name2) {
        HumanPlayer p1 = new HumanPlayer(name2, 'X');
        CompPlayer p2 = new CompPlayer("Computer", 'O');
        Player player;
        player = p1;
        while (true) {
            System.out.println(player.name + " turn");
            player.To_Make_Move();
            Game.displayboard();
            if (Game.Check_Col_Winner() || Game.Check_Row_Winner() || Game.Check_Diagonal_winner()) {
                System.out.println(player.name + "has won");
                break;
            } else if (Game.Check_Draw()) {
                System.out.println("Game is a draw.");
            } else {
                if (player == p1) {
                    player = p2;
                } else {
                    player = p1;
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 1 for Human vs Human and 2 for Human vs Computer game");
        int num = scan.nextInt();
        Game g = new Game();

        if (num == 1) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter player 1 name:");
            String name1 = in.nextLine();
            System.out.println("Enter player 2 name:");
            String name2 = in.nextLine();
            HumanvsHuman(name1, name2);
        } else if (num == 2) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter player 1 name:");
            String name2 = in.nextLine();
            ComputervsHuman(name2);
        } else {
            System.out.println("Wrong Input");
        }

    }
}//// Program ends here.
