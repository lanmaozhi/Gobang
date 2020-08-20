package evaluator;

import chessboard.Chessboard;
import model.ChessPlace;
import model.EvaluateScore;
import utils.ChessTypeEnum;

import java.util.List;

/**
 * 位置评估器
 */
public class PlaceEvaluator implements Evaluator {

    private static final int[][] placeScore = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
            {0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0},
            {0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0},
            {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    @Override
    public EvaluateScore evaluate(Chessboard chessboard) {

        int blackScore = 0;
        int whiteScore = 0;
        List<ChessPlace> blackPlaces = chessboard.getChessPlace(ChessTypeEnum.BLACK);
        for (ChessPlace blackPlace : blackPlaces) {
            blackScore += placeScore[blackPlace.x][blackPlace.y];
        }
        List<ChessPlace> whitePlaces = chessboard.getChessPlace(ChessTypeEnum.WHITE);
        for (ChessPlace whitePlace : whitePlaces) {
            whiteScore += placeScore[whitePlace.x][whitePlace.y];
        }

        return new EvaluateScore(blackScore, whiteScore);
    }
}
