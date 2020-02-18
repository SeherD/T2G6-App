import java.util.*;
import Chess.*;
import GameEntities.*;
// Some methods completed. Need more work.
public class Launcher {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        boolean gameOver = false;
        String currentPlayer = "player1";
        Scanner scanner = new Scanner(System.in);
        String input;

        ChessLocation newLocation;
        ChessPiece currentPiece;
        King king;

        while(!gameOver){
            try {
                System.out.println(chessGame.getChessBoard().toString());
                System.out.println(currentPlayer + "'s Turn:");
                System.out.println("M - Move a piece");
                System.out.println("Q - Quit game");
                System.out.println("R - Reset the game");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q") || input.equalsIgnoreCase("QUIT")) {
                    gameOver = true;
                    System.out.println("===== GAME HAS ENDED =====");
                    continue;
                } else if (input.equalsIgnoreCase("R") || input.equalsIgnoreCase("RESTART")) {
                    chessGame = new ChessGame();
                    System.out.println("===== GAME RESTARTED =====");
                    continue;
                } else if (input.equalsIgnoreCase("M") || input.equalsIgnoreCase("MOVE")) {
                    
                    if (currentPlayer.equals("player1")) {
                        king = chessGame.getPlayer1King();
                    } else {
                        king = chessGame.getPlayer2King();
                    }
                    ChessPiece danger = king.check();
                    if (danger != null) {
                        System.out.println("Your King is in Check from piece at: (" + danger.getChessLocation().getRow() + ", " + danger.getChessLocation().getCol() + ").");
                    }

                    currentPiece = getCurrentPiece(chessGame, currentPlayer);
                    newLocation = getNewLocation();

                    if (currentPiece.moveTo(newLocation)) {
                        currentPlayer = (currentPlayer.equalsIgnoreCase("player1")) ? "player2": "player1"; 
                    } else {
                        System.out.println("Move was invalid, try again.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Couldn't parse input.");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException :(, GL Debugging");
                e.printStackTrace();
            }
        }
    }
