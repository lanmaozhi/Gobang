import chessboard.BitmapChessBoard;
import chessboard.Chessboard;
import utils.ChessTypeEnum;

/**
 * 简单启动类，命令行对弈
 */
public class SimpleStart {

    public static void main(String[] args) {

        Chessboard chessboard = new BitmapChessBoard();
        chessboard.printChessboard();

        boolean isBlackTurn = true;
        while (chessboard.isComplete() == null) {
            try {
                System.out.println(String.format("本轮是 %s 棋走，请输入坐标： ", isBlackTurn ? "黑" : "白"));
                isBlackTurn = ! isBlackTurn;
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("游戏结束：");
        System.out.println(chessboard.isComplete() == ChessTypeEnum.BLACK ? "黑棋胜！" : "白棋胜！");
    }
}
