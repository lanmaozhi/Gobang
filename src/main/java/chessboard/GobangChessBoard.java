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

    //棋子行、列、左斜对角线、右斜对角线
    private short[][] rows;
    private short[][] columns;
    private short[][] leftCrosses;
    private short[][] rightCrosses;

    //目前已经下的所有棋位的set，采用 (行 * 100 + 列) 放入set内存储
    private Set<Integer> positionSet;

    GobangChessBoard() {
        this.rows = new short[2][ROW_NUM];
        this.columns = new short[2][COLUMN_NUM];
        this.leftCrosses = new short[2][CROSS_NUM];
        this.rightCrosses = new short[2][CROSS_NUM];
        this.positionSet = new HashSet<>();
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
        if (! this.checkChessPositionLegal(x, y)) {
            throw new RuntimeException("棋子位置不合法");
        }

        //走棋
        this.makeAMove(this.rows[chessType.getChessArrayIndex()], this.columns[chessType.getChessArrayIndex()], this.leftCrosses[chessType.getChessArrayIndex()], this.rightCrosses[chessType.getChessArrayIndex()], x, y);

        //放入位置set内
        this.positionSet.add(this.gainChessPosition(x, y));

        return true;
    }

    //根据横纵坐标获取棋子位置标识
    private int gainChessPosition(int x, int y) {

        return x * 100 + y;
    }

    //校验走子合法性
    private boolean checkChessPositionLegal(int x, int y) {

        return x < Constants.ROW_NUM && y < Constants.COLUMN_NUM && ! positionSet.contains(this.gainChessPosition(x, y));
    }

    //走子
    private void makeAMove(short[] row, short[] column, short[] leftCross, short[] rightCross, int x, int y) {

        row[x] &= MASK_CODES.get(y);
        column[y] &= MASK_CODES.get(x);

    }
}




























