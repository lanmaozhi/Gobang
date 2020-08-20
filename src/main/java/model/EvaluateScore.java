package model;

import utils.ChessTypeEnum;

/**
 * 评估函数分数
 */
public class EvaluateScore {

    private final int blackScore, whiteScore;

    public EvaluateScore(int blackScore, int whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    //获取棋盘上某种棋子的估值
    public int getScore(ChessTypeEnum chessType) {

        if (chessType == null) {
            return 0;
        }

        return chessType == ChessTypeEnum.BLACK ? blackScore : whiteScore;
    }

    //获取对于某种棋子来说，它在整个棋局上的估值
    public int getCountedScore(ChessTypeEnum chessType) {

        if (chessType == null) {
            return 0;
        }

        return chessType == ChessTypeEnum.BLACK ? blackScore - whiteScore: whiteScore - blackScore;
    }
}
