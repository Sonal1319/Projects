package projects.TicTacToe;

import projects.TicTacToe.controller.GameController;
import projects.TicTacToe.model.*;
import projects.TicTacToe.service.winningStrategy.WinningStrategyName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int id = 1;
        List<Player> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to TicTacToe :)");
        System.out.println("Let the game begin!");

        System.out.println("Please enter the dimension for the board");
        int dimension = sc.nextInt();
        System.out.println("Would you like a bot in the game? Y or N");
        String botAns = sc.next();
        if (botAns.equalsIgnoreCase("Y")) {
            Player bot = new Bot(id++, '$', BotDifficultyLevel.HARD);
            players.add(bot);
        }

        while (id < dimension) {
            System.out.println("Please enter the player name:");
            String playerName = sc.next();
            System.out.println("Please enter the player symbol:");
            char symbol = sc.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, symbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }

        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDERONEWINNINGSTRATEGY);

        int playerIndex = -1;
        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current Board Status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            game.getMove().add(movePlayed);
            game.getBoardStatus().add(game.getCurrentBoard());
            Player winner = gameController.checkWinner(game, movePlayed);

            if (winner != null) {
                System.out.println("Winner is: " + winner.getName());
                break;
            }
            if (game.getMove().size() == game.getCurrentBoard().getDimension() * game.getCurrentBoard().getDimension()) {
                System.out.println("Game is draw");
                break;
            }

            System.out.println("Final Board status");
            gameController.displayBoard(game);

        }
    }
}
