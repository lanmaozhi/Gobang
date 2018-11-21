package chessboard;

import utils.ChessTypeEnum;
import utils.Constants;

import java.util.HashSet;
import java.util.Set;

import static utils.Constants.ROW_NUM;
import static utils.Constants.COLUMN_NUM;
import static utils.Constants.CROSS_NUM;
import static utils.Constants.MASK_CODES;

/**
 * <pre>
 *
 * </pre>
 *
 * @author maozhi.lan@meicloud.com
 * @version 1.00.00
 * <p>
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class GobangChessBoard implements Chessboard {

    //是否使用棋子位位置表示
    boolean useBitMap;
    //棋子行、列、左斜对角线、右斜对角线
    private short[][] rows;
    private short[][] columns;
    private short[][] leftCrosses;
    private short[][] rightCrosses;

    //目前已经下的所有棋位的set，采用 (行 * 100 + 列) 放入set内存储
    private Set<Integer> blackPositionSet;
    private Set<Integer> whitePositionSet;

    //胜利方
    private ChessTypeEnum winSide;

    GobangChessBoard(boolean useBitMap) {
        if (useBitMap) {
            this.rows = new short[2][ROW_NUM];
            this.columns = new short[2][COLUMN_NUM];
            this.leftCrosses = new short[2][CROSS_NUM];
            this.rightCrosses = new short[2][CROSS_NUM];
        }
        this.useBitMap = useBitMap;
        this.blackPositionSet = new HashSet<>();
        this.whitePositionSet = new HashSet<>();
    }

    GobangChessBoard() {
        this(false);
    }

    @Override
    public void randomChess() {

    }

    @Override
    public int judgeIsComplete() {
        return 0;
    }

    @Override
    public synchronized boolean makeAMove(ChessTypeEnum chessType, int x, int y) {

        if (chessType == null) {
            throw new RuntimeException("无传入棋子类型");
        }
        if (x >= Constants.ROW_NUM || y >= Constants.COLUMN_NUM) {
            throw new RuntimeException("棋子位置不合法");
        }
        int chessPosition = this.gainChessPosition(x, y);
        if (blackPositionSet.contains(chessPosition) || whitePositionSet.contains(chessPosition)) {
            throw new RuntimeException("走子位置已经存在其它棋子");
        }
        if (this.winSide != null) {
            throw new RuntimeException("棋局已结束，不可走子");
        }

        //走棋
        if (this.useBitMap) {
            this.makeAMoveToBitMap(this.rows[chessType.getChessArrayIndex()], this.columns[chessType.getChessArrayIndex()], this.leftCrosses[chessType.getChessArrayIndex()], this.rightCrosses[chessType.getChessArrayIndex()], x, y);
        }

        //放入位置set内
        this.putChessPositionToSet(chessType, chessPosition);

        //计算是否胜利
        if (this.isCompleted(chessType, x, y)) {
            this.winSide = chessType;
        }

        return true;
    }

    //根据横纵坐标获取棋子位置标识
    private int gainChessPosition(int x, int y) {

        return x * 100 + y;
    }

    //往bitMap内走子
    private void makeAMoveToBitMap(short[] row, short[] column, short[] leftCross, short[] rightCross, int x, int y) {

        row[x] &= MASK_CODES.get(y);
        column[y] &= MASK_CODES.get(x);
        //TODO 对角线走子
    }

    //棋子放入相应的set内
    private void putChessPositionToSet(ChessTypeEnum chessType, int chessPosition) {

        if (ChessTypeEnum.BLACK.equals(chessType)) {
            this.blackPositionSet.add(chessPosition);
        }
        if (ChessTypeEnum.WHITE.equals(chessType)) {
            this.whitePositionSet.add(chessPosition);
        }
    }

    //计算是否胜利
    private boolean isCompleted(ChessTypeEnum chessType, int x, int y) {



        return false;
    }
}




























