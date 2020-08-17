package evaluator;

import chessboard.Chessboard;
import model.EvaluateScore;

public interface Evaluator {

    /**
     * 根据当前棋盘状态评估分数，局势越好分数越高
     */
    EvaluateScore evaluate(Chessboard chessboard);
}
