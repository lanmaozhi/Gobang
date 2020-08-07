package chessboard;

import utils.ChessTypeEnum;
import utils.Constants;

import static utils.Constants.ROW_NUM;
import static utils.Constants.COLUMN_NUM;
import static utils.Constants.CROSS_NUM;
import static utils.Constants.MASK_CODES;
import static utils.Constants.LEFT_CROSS_PLACE;
import static utils.Constants.RIGHT_CROSS_PLACE;

public class GobangChessBoard implements Chessboard {

    //棋子行、列、左下-右上、右上-左下
    private final int[][] rows;
    private final int[][] columns;
    private final int[][] leftCrosses;
    private final int[][] rightCrosses;

    //胜利方
    private ChessTypeEnum winSide;

    public GobangChessBoard() {
        this.rows = new int[2][ROW_NUM];
        this.columns = new int[2][COLUMN_NUM];
        this.leftCrosses = new int[2][CROSS_NUM];
        this.rightCrosses = new int[2][CROSS_NUM];
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
    public synchronized boolean makeAMove(ChessTypeEnum chessType, int x, int y) {

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

        return true;
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

    public static void main(String[] args) {

        GobangChessBoard board1 = new GobangChessBoard();
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 14);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 13);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 12);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 11);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 10);

        GobangChessBoard board2 = new GobangChessBoard();
        board2.makeAMove(ChessTypeEnum.WHITE, 1, 1);
        board2.makeAMove(ChessTypeEnum.WHITE, 2, 2);
        board2.makeAMove(ChessTypeEnum.WHITE, 3, 3);
        board2.makeAMove(ChessTypeEnum.WHITE, 4, 4);
        board2.makeAMove(ChessTypeEnum.WHITE, 5, 5);

        return;
    }
}
