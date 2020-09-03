package search;

import chessboard.Chessboard;
import evaluator.Evaluator;
import evaluator.PatternEvaluator;
import evaluator.PlaceEvaluator;
import model.ChessPlace;
import model.ChessRange;
import model.MaxMinSearchResult;
import utils.ChessTypeEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * 极大极小搜索器
 */
public class MaxMinSearcher implements Searcher {

    //搜索层数
    private final int searchLevel;
    //评估函数
    private static final Evaluator placeEvaluator = new PlaceEvaluator();
    private static final Evaluator patternEvaluator = new PatternEvaluator();

    public MaxMinSearcher(int searchLevel) {
        this.searchLevel = searchLevel;
    }

    @Override
    public ChessPlace search(Chessboard chessboard, ChessTypeEnum searchFor) {

        MaxMinSearchResult result = search(chessboard, 0, searchFor, searchFor);
        return result.chessPlace;
    }

    private MaxMinSearchResult search(Chessboard chessboard, int level, ChessTypeEnum searchFor, ChessTypeEnum turnToMove) {

        //到达搜索层数或者棋局已经结束，直接返回当前棋局评估分
        if (level == searchLevel || chessboard.isComplete() != null) {
            int placeScore = placeEvaluator.evaluate(chessboard).getCountedScore(searchFor);
            int patternScore = patternEvaluator.evaluate(chessboard).getCountedScore(searchFor);
            return new MaxMinSearchResult(placeScore + patternScore, null, new LinkedList<>());
        }

        ChessRange nowChessRange = chessboard.getChessRange();
        //搜索范围在棋局范围扩展3格
        ChessRange searchChessRange = new ChessRange(Math.max(0, nowChessRange.begX - 3), Math.min(14, nowChessRange.endX + 3)
                , Math.max(0, nowChessRange.begY - 3), Math.min(14, nowChessRange.endY + 3));
        List<ChessPlace> blankPlaces = chessboard.getBlankPlaceByRange(searchChessRange);
        //TODO 2020.9.3 暂时不考虑没有棋子可走的情况，后续可以采取措施：1.增加搜索范围；2.如果全棋盘没有棋子可走，直接返回得分为0
        //遍历搜索范围的可下子位置，找出最佳位置，其中：偶数层是MAX层，奇数层是MIN层
        int score;
        ChessPlace place = null;
        MaxMinSearchResult bestResult = null;
        if (level % 2 == 0) {
            score = Integer.MIN_VALUE;
            for (ChessPlace blankPlace : blankPlaces) {
                chessboard.makeAMove(turnToMove, blankPlace.x, blankPlace.y);
                MaxMinSearchResult result = search(chessboard, level + 1, searchFor, turnToMove.getAnotherType());
                if (result.score > score) {
                    score = result.score;
                    place = blankPlace;
                    bestResult = result;
                }
                chessboard.unMove(blankPlace.x, blankPlace.y);
            }
        }
        else {
            score = Integer.MAX_VALUE;
            for (ChessPlace blankPlace : blankPlaces) {
                chessboard.makeAMove(turnToMove, blankPlace.x, blankPlace.y);
                MaxMinSearchResult result = search(chessboard, level + 1, searchFor, turnToMove.getAnotherType());
                if (result.score < score) {
                    score = result.score;
                    place = blankPlace;
                    bestResult = result;
                }
                chessboard.unMove(blankPlace.x, blankPlace.y);
            }
        }

        return new MaxMinSearchResult(score, place, bestResult.pv);
    }
}
