import chessboard.BitmapChessBoard;
import chessboard.Chessboard;
import model.ChessPlace;
import search.EvaluateSearcher;
import search.Searcher;
import utils.ChessTypeEnum;

/**
 * 简单启动类，命令行对弈
 */
public class SimpleStart {

    public static void main(String[] args) {

        Chessboard chessboard = new BitmapChessBoard();
        chessboard.printChessboard();

        Searcher searcher = new EvaluateSearcher();
        boolean isBlackTurn = true;
        int round = 0;
        while (chessboard.isComplete() == null) {
            try {
                System.out.println(String.format("本轮是 %s 棋走 ", isBlackTurn ? "黑" : "白"));
                ChessTypeEnum chessType = isBlackTurn ? ChessTypeEnum.BLACK : ChessTypeEnum.WHITE;
                if (round % 2 == 0) {
                    ChessPlace place = searcher.search(chessboard, chessType);
                    chessboard.makeAMove(chessType, place.x, place.y);
                }
                else {
                    System.out.print("请输入行号：");
                    int x = System.in.read() - 'A';
                    System.in.read();
                    System.out.print("请输入列号：");
                    int y = System.in.read() - 'a';
                    System.in.read();
                    chessboard.makeAMove(chessType, x, y);
                }
                chessboard.printChessboard();
                ++round;
                isBlackTurn = ! isBlackTurn;
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("程序异常：" + e.getMessage());
                break;
            }
        }

        if (chessboard.isComplete() != null) {
            System.out.println("游戏结束：");
            System.out.println(chessboard.isComplete() == ChessTypeEnum.BLACK ? "黑棋胜！" : "白棋胜！");
        }
    }
}
