package chessboard;

import model.ChessPlace;
import model.ChessRange;
import model.PatternMetaData;
import utils.ChessTypeEnum;

import java.util.List;

public interface Chessboard {

    /**
     * 是否已结束
     * 如果已结束返回胜利方，否则返回null
     */
    ChessTypeEnum isComplete();

    /**
     * 走棋(x, y)
     * @param chessType 棋子类型
     * @param x 走子横坐标
     * @param y 走子纵坐标
     * @return 走完后是否胜利
     */
    boolean makeAMove(ChessTypeEnum chessType, int x, int y);

    /**
     * 取消走棋
     */
    void unMove(int x, int y);

    /**
     * 获取棋形元数据
     */
    PatternMetaData getPatternMetaData();

    /**
     * 打印棋盘
     */
    void printChessboard();

    /**
     * 获取当前棋盘的棋子范围，如果还没有下子，返回棋盘中心坐标
     */
    ChessRange getChessRange();

    /**
     * 获取范围内可以落子的棋位
     */
    List<ChessPlace> getBlankPlaceByRange(ChessRange chessRange);

    /**
     * 获取指定棋子的所有位置
     */
    List<ChessPlace> getChessPlace(ChessTypeEnum chessType);
}
