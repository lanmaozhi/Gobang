package evaluator;

import chessboard.Chessboard;

public interface Evaluator {

    /**
     * 根据当前棋盘状态评估分数，局势越好分数越高
     */
    long evaluate(Chessboard now);
}
