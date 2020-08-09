package chessboard;

import model.PatternMetaData;
import utils.ChessTypeEnum;
import utils.Constants;

import static utils.Constants.ROW_NUM;
import static utils.Constants.COLUMN_NUM;
import static utils.Constants.CROSS_NUM;
import static utils.Constants.MASK_CODES;
import static utils.Constants.LEFT_CROSS_PLACE;
import static utils.Constants.RIGHT_CROSS_PLACE;

/**
 * bitmap 棋盘实现
 * 非线程安全，不要并发访问
 */
public class BitmapChessBoard implements Chessboard, Cloneable {

    //棋子行、列、左下-右上、右上-左下
    private final int[][] rows;
    private final int[][] columns;
    private final int[][] leftCrosses;
    private final int[][] rightCrosses;

    //胜利方
    private ChessTypeEnum winSide;

    public BitmapChessBoard() {
        rows = new int[ChessTypeEnum.values().length][ROW_NUM];
        columns = new int[ChessTypeEnum.values().length][COLUMN_NUM];
        leftCrosses = new int[ChessTypeEnum.values().length][CROSS_NUM];
        rightCrosses = new int[ChessTypeEnum.values().length][CROSS_NUM];

        for (ChessTypeEnum chessType : ChessTypeEnum.values()) {
            //初始化棋盘，可以下子的点为0，不可下子(超出棋盘/已经下同色子)的点为1，黑白两个棋互不干扰，不考虑对方
            //行列 ~ 0111 1111 1111 1111 0000 0000 0000 0000
            int rowBlankFlag = ~ 0x7FFF0000;
            for (int i = 0; i < rows[chessType.index].length; i++) {
                rows[chessType.index][i] = rowBlankFlag;
            }
            for (int i = 0; i < columns[chessType.index].length; i++) {
                columns[chessType.index][i] = rowBlankFlag;
            }
            //斜线 0100 0000 0000 0000 0000 0000 0000 0000，0-14行可放棋子数逐渐增多，15-28行棋子逐渐减少
            int crossFlag = 0x40000000;
            for (int i = 0; i <= 14; i++) {
                leftCrosses[chessType.index][i] = ~ crossFlag;
                crossFlag |= (crossFlag >>> 1);
            }
            crossFlag = 0x40000000;
            for (int i = 28; i >= 15; i--) {
                leftCrosses[chessType.index][i] = ~ crossFlag;
                crossFlag |= (crossFlag >>> 1);
            }
            crossFlag = 0x40000000;
            for (int i = 0; i <= 14; i++) {
                rightCrosses[chessType.index][i] = ~ crossFlag;
                crossFlag |= (crossFlag >>> 1);
            }
            crossFlag = 0x40000000;
            for (int i = 28; i >= 15; i--) {
                rightCrosses[chessType.index][i] = ~ crossFlag;
                crossFlag |= (crossFlag >>> 1);
            }
        }
    }

    @Override
    public ChessTypeEnum isComplete() {

        return winSide;
    }

    private void judgeIsComplete(ChessTypeEnum chessType) {

        if (judgeHasFiveBits(rows[chessType.index]) || judgeHasFiveBits(columns[chessType.index])
                || judgeHasFiveBits(leftCrosses[chessType.index]) || judgeHasFiveBits(rightCrosses[chessType.index])) {
            winSide = chessType;
        }
    }

    private boolean judgeHasFiveBits(int[] ints) {

        //15列，所以用掩码比较11次即可
        int countTimes = 11;
        //掩码是 11111000000000000000000000000000
        int mask = 0xF8000000;
        //开始比较
        for (int anInt : ints) {
            if (anInt == 0) {
                continue;
            }
            for (int i = 0; i < countTimes; i++) {
                int nMask = mask >>> i;
                if ((anInt & nMask) == nMask) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean makeAMove(ChessTypeEnum chessType, int x, int y) {

        if (chessType == null) {
            throw new RuntimeException("无传入棋子类型");
        }
        if (x < 0 || x >= Constants.ROW_NUM || y < 0 || y >= Constants.COLUMN_NUM) {
            throw new RuntimeException("棋子位置不合法");
        }
        if (this.winSide != null) {
            throw new RuntimeException("棋局已结束，不可走子");
        }
        if (judgePlaceHasChess(x, y)) {
            throw new RuntimeException("位置上已经有棋子，不可走子");
        }

        //走棋
        makeAMoveToBitMap(chessType, x, y);

        //计算是否胜利
        judgeIsComplete(chessType);
        return winSide != null;
    }

    //往bitMap内走子
    private void makeAMoveToBitMap(ChessTypeEnum chessType, int x, int y) {

        //行
        rows[chessType.index][x] |= MASK_CODES[y];
        //列
        columns[chessType.index][y] |= MASK_CODES[x];
        //左下-右上
        leftCrosses[chessType.index][LEFT_CROSS_PLACE[x][y][0]] |= MASK_CODES[LEFT_CROSS_PLACE[x][y][1]];
        //右上-左下
        rightCrosses[chessType.index][RIGHT_CROSS_PLACE[x][y][0]] |= MASK_CODES[RIGHT_CROSS_PLACE[x][y][1]];
    }

    //判断位置(x,y)上是否有棋子
    private boolean judgePlaceHasChess(int x, int y) {

        return (rows[ChessTypeEnum.BLACK.index][x] & MASK_CODES[y]) != 0
                || (rows[ChessTypeEnum.WHITE.index][x] & MASK_CODES[y]) != 0;
    }

    @Override
    public void unMove(int x, int y) {

        for (ChessTypeEnum chessType : ChessTypeEnum.values()) {
            //行
            rows[chessType.index][x] &= ~ MASK_CODES[y];
            //列
            columns[chessType.index][y] &= ~ MASK_CODES[x];
            //左下-右上
            leftCrosses[chessType.index][LEFT_CROSS_PLACE[x][y][0]] &= ~ MASK_CODES[LEFT_CROSS_PLACE[x][y][1]];
            //右上-左下
            rightCrosses[chessType.index][RIGHT_CROSS_PLACE[x][y][0]] &= ~ MASK_CODES[RIGHT_CROSS_PLACE[x][y][1]];
        }
    }

    @Override
    public PatternMetaData getPatternMetaData() {








        return null;
    }










    public static void main(String[] args) {

        BitmapChessBoard board1 = new BitmapChessBoard();
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 14);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 13);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 12);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 11);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 10);

        BitmapChessBoard board2 = new BitmapChessBoard();
        board2.makeAMove(ChessTypeEnum.WHITE, 1, 1);
        board2.makeAMove(ChessTypeEnum.WHITE, 2, 2);
        board2.makeAMove(ChessTypeEnum.WHITE, 3, 3);
        board2.makeAMove(ChessTypeEnum.WHITE, 4, 4);
        board2.makeAMove(ChessTypeEnum.WHITE, 5, 5);

        return;
    }
}
