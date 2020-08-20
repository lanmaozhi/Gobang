package search;

import chessboard.Chessboard;
import model.ChessPlace;
import utils.ChessTypeEnum;

/**
 * 搜索函数
 */
public interface Searcher {

    /**
     * 搜索
     * @param chessboard 当前棋局
     * @param searchFor 为什么棋子搜索
     */
    ChessPlace search(Chessboard chessboard, ChessTypeEnum searchFor);
}
