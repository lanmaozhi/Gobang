package search;

import chessboard.Chessboard;
import evaluator.Evaluator;
import evaluator.PatternEvaluator;
import evaluator.PlaceEvaluator;
import model.ChessPlace;
import model.ChessRange;
import model.EvaluateScore;
import utils.ChessTypeEnum;

import java.util.List;

/**
 * 评估搜索器，只使用评估函数，不进行搜索
 */
public class EvaluateSearcher implements Searcher {

    private static final Evaluator placeEvaluator = new PlaceEvaluator();
    private static final Evaluator patternEvaluator = new PatternEvaluator();

    @Override
    public ChessPlace search(Chessboard chessboard, ChessTypeEnum searchFor) {

        ChessRange nowChessRange = chessboard.getChessRange();
        //搜索范围在棋局范围扩展3格
        ChessRange searchChessRange = new ChessRange(Math.max(0, nowChessRange.begX - 3), Math.min(14, nowChessRange.endX + 3)
                , Math.max(0, nowChessRange.begY - 3), Math.min(14, nowChessRange.endY + 3));

        //遍历搜索范围的可下子位置，找出最佳位置
        int maxScore = Integer.MIN_VALUE;
        ChessPlace place = null;
        List<ChessPlace> blankPlaces = chessboard.getBlankPlaceByRange(searchChessRange);
        for (ChessPlace blankPlace : blankPlaces) {
            chessboard.makeAMove(searchFor, blankPlace.x, blankPlace.y);
            EvaluateScore placeScore = placeEvaluator.evaluate(chessboard);
            EvaluateScore patternScore = patternEvaluator.evaluate(chessboard);
            int score = placeScore.getCountedScore(searchFor) + patternScore.getCountedScore(searchFor);
            if (score > maxScore) {
                maxScore = score;
                place = blankPlace;
            }
            chessboard.unMove(blankPlace.x, blankPlace.y);
        }

        return place;
    }
}
