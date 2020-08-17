package evaluator;

import chessboard.Chessboard;
import model.ChessPattern;
import model.EvaluateScore;
import model.PatternMetaData;
import utils.ChessPatternEnum;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 棋形评估
 */
public class PatternEvaluator implements Evaluator {

    private static final Map<ChessPatternEnum, Integer> patternScoreMap;
    static {
        Map<ChessPatternEnum, Integer> tempMap = new HashMap<>();
        tempMap.put(ChessPatternEnum.FIVE, 99999);
        tempMap.put(ChessPatternEnum.ALIVE_FOUR, 49999);
        tempMap.put(ChessPatternEnum.SINGLE_FOUR_1, 9999);
        tempMap.put(ChessPatternEnum.SINGLE_FOUR_2, 9999);
        tempMap.put(ChessPatternEnum.SINGLE_FOUR_3, 9999);
        tempMap.put(ChessPatternEnum.SINGLE_FOUR_4, 9999);
        tempMap.put(ChessPatternEnum.SINGLE_FOUR_5, 9999);
        tempMap.put(ChessPatternEnum.ALIVE_THREE_1, 4999);
        tempMap.put(ChessPatternEnum.ALIVE_THREE_2, 4999);
        tempMap.put(ChessPatternEnum.ALIVE_THREE_3, 4999);
        tempMap.put(ChessPatternEnum.ALIVE_THREE_4, 4999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_1, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_2, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_3, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_4, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_5, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_6, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_7, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_8, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_9, 999);
        tempMap.put(ChessPatternEnum.SLEEP_THREE_10, 999);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_1, 499);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_2, 499);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_3, 499);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_4, 499);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_5, 499);
        tempMap.put(ChessPatternEnum.ALIVE_TWO_6, 499);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_1, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_2, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_3, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_4, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_5, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_6, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_7, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_8, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_9, 99);
        tempMap.put(ChessPatternEnum.SLEEP_TWO_10, 99);
        tempMap.put(ChessPatternEnum.ALIVE_ONE_1, 49);
        tempMap.put(ChessPatternEnum.ALIVE_ONE_2, 49);
        tempMap.put(ChessPatternEnum.ALIVE_ONE_3, 49);
        tempMap.put(ChessPatternEnum.ALIVE_ONE_4, 49);
        tempMap.put(ChessPatternEnum.SLEEP_ONE_1, 9);
        tempMap.put(ChessPatternEnum.SLEEP_ONE_2, 9);
        tempMap.put(ChessPatternEnum.SLEEP_ONE_3, 9);
        tempMap.put(ChessPatternEnum.SLEEP_ONE_4, 9);
        tempMap.put(ChessPatternEnum.SLEEP_ONE_5, 9);
        patternScoreMap = Collections.unmodifiableMap(tempMap);
    }

    @Override
    public EvaluateScore evaluate(Chessboard chessboard) {

        PatternMetaData patternMetaData = chessboard.getPatternMetaData();
        int blackScore = countScore(patternMetaData.getBlackRowPatterns(), patternMetaData.getBlackColumnPatterns()
                , patternMetaData.getBlackLeftCrossPatterns(), patternMetaData.getBlackRightCrossPatterns());
        int whiteScore = countScore(patternMetaData.getWhiteRowPatterns(), patternMetaData.getWhiteColumnPatterns()
                , patternMetaData.getWhiteLeftCrossPatterns(), patternMetaData.getWhiteRightCrossPatterns());

        return new EvaluateScore(blackScore, whiteScore);
    }

    private int countScore(List<ChessPattern> rowPatterns, List<ChessPattern> columnPatterns
            , List<ChessPattern> leftCrossPatterns, List<ChessPattern> rightCrossPatterns) {

        int score = 0;
        for (ChessPattern rowPattern : rowPatterns) {
            score += patternScoreMap.get(rowPattern.getChessPattern());
        }
        for (ChessPattern columnPattern : columnPatterns) {
            score += patternScoreMap.get(columnPattern.getChessPattern());
        }
        for (ChessPattern leftCrossPattern : leftCrossPatterns) {
            score += patternScoreMap.get(leftCrossPattern.getChessPattern());
        }
        for (ChessPattern rightCrossPattern : rightCrossPatterns) {
            score += patternScoreMap.get(rightCrossPattern.getChessPattern());
        }

        return score;
    }
}
