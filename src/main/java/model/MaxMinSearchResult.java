package model;

/**
 * 极大极小搜索结果
 */
public class MaxMinSearchResult {

    //分数
    public final int score;
    //位置
    public final ChessPlace chessPlace;

    public MaxMinSearchResult(int score, ChessPlace chessPlace) {
        this.score = score;
        this.chessPlace = chessPlace;
    }
}
