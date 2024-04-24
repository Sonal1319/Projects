package projects.TicTacToe.service.winningStrategy;

import projects.TicTacToe.model.Board;
import projects.TicTacToe.model.Move;
import projects.TicTacToe.model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);
}
