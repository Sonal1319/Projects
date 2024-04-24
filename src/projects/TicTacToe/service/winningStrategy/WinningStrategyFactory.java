package projects.TicTacToe.service.winningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategyName name, int dimension) {
        return new OrderOneWinningStrategy(dimension);
    }
}
