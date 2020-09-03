package model;

import java.util.LinkedList;

/**
 * 极大极小搜索结果
 */
public class MaxMinSearchResult {

    //分数
    public final int score;
    //位置
    public final ChessPlace chessPlace;
    //主要变例（principal variation）
    public final LinkedList<ChessPlace> pv;

    public MaxMinSearchResult(int score, ChessPlace chessPlace, LinkedList<ChessPlace> originPv) {
        this.score = score;
        this.chessPlace = chessPlace;
        this.pv = originPv;
        //将最新的位置加入主要变例
        addPVPlace(chessPlace);
    }

    private void addPVPlace(ChessPlace chessPlace) {

        this.pv.addFirst(chessPlace);
    }
}
