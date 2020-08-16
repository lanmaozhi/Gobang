package chessboard;

import model.ChessPattern;
import model.PatternMetaData;
import utils.ChessPatternEnum;
import utils.ChessTypeEnum;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

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

        PatternMetaData patternMetaData = new PatternMetaData();
        patternMetaData.setBlackRowPatterns(getRowColumnPattenMetaData(rows[ChessTypeEnum.BLACK.index], rows[ChessTypeEnum.WHITE.index]));
        patternMetaData.setBlackColumnPatterns(getRowColumnPattenMetaData(columns[ChessTypeEnum.BLACK.index], columns[ChessTypeEnum.WHITE.index]));
        patternMetaData.setBlackLeftCrossPatterns(getCrossPattenMetaData(leftCrosses[ChessTypeEnum.BLACK.index], leftCrosses[ChessTypeEnum.WHITE.index]));
        patternMetaData.setBlackRightCrossPatterns(getCrossPattenMetaData(rightCrosses[ChessTypeEnum.BLACK.index], rightCrosses[ChessTypeEnum.WHITE.index]));
        patternMetaData.setWhiteRowPatterns(getRowColumnPattenMetaData(rows[ChessTypeEnum.WHITE.index], rows[ChessTypeEnum.BLACK.index]));
        patternMetaData.setWhiteColumnPatterns(getRowColumnPattenMetaData(columns[ChessTypeEnum.WHITE.index], columns[ChessTypeEnum.BLACK.index]));
        patternMetaData.setWhiteLeftCrossPatterns(getCrossPattenMetaData(leftCrosses[ChessTypeEnum.WHITE.index], leftCrosses[ChessTypeEnum.BLACK.index]));
        patternMetaData.setWhiteRightCrossPatterns(getCrossPattenMetaData(rightCrosses[ChessTypeEnum.WHITE.index], rightCrosses[ChessTypeEnum.BLACK.index]));

        return patternMetaData;
    }

    //获取行、列位置的元数据信息
    private List<ChessPattern> getRowColumnPattenMetaData(int[] mainRows, int[] otherRows) {

        List<ChessPattern> chessPatterns = new ArrayList<>();
        //由于有六连，所以只循环 Constants.COLUMN_NUM - 5 次
        int rowLoopTimes = Constants.COLUMN_NUM - 5;
        for (int i = 0; i < mainRows.length; i++) {
            int mainRow = mainRows[i];
            if (mainRow == 0) {
                continue;
            }
            int otherRow = otherRows[i];
            for (int j = 0; j < rowLoopTimes; j++) {
                for (ChessPatternEnum patternEnum :ChessPatternEnum.values()){
                    if ((mainRow & patternEnum.limitMask) == patternEnum.pattern
                            && (otherRow & patternEnum.limitMask) == 0) {
                        chessPatterns.add(new ChessPattern(patternEnum, i, j));
                        break;
                    }
                }
                mainRow <<= 1;
                otherRow <<= 1;
            }
            //判断末尾的五个棋子信息
            for (ChessPatternEnum patternEnum :ChessPatternEnum.values()){
                if (patternEnum.bitLength == 5 && (mainRow & patternEnum.limitMask) == patternEnum.pattern
                        && (otherRow & patternEnum.limitMask) == 0) {
                    chessPatterns.add(new ChessPattern(patternEnum, i, rowLoopTimes));
                }
            }
        }

        return chessPatterns;
    }

    //获取对角线位置的元数据信息
    private List<ChessPattern> getCrossPattenMetaData(int[] mainCrosses, int[] otherCrosses) {

        List<ChessPattern> chessPatterns = new ArrayList<>();
        for (int i = 0; i < mainCrosses.length; i++) {
            //
            int rowLength = i <= 14 ? i + 1 : 29 - i;
            //
            int rowLoopTimes = rowLength - 5;
            if (rowLoopTimes < 0) {
                continue;
            }
            int mainRow = mainCrosses[i];
            if (mainRow == 0) {
                continue;
            }
            int otherRow = otherCrosses[i];
            for (int j = 0; j < rowLoopTimes; j++) {
                for (ChessPatternEnum patternEnum :ChessPatternEnum.values()){
                    if ((mainRow & patternEnum.limitMask) == patternEnum.pattern
                            && (otherRow & patternEnum.limitMask) == 0) {
                        chessPatterns.add(new ChessPattern(patternEnum, i, j));
                        break;
                    }
                }
                mainRow <<= 1;
                otherRow <<= 1;
            }
            //判断末尾的五个棋子信息
            for (ChessPatternEnum patternEnum :ChessPatternEnum.values()){
                if (patternEnum.bitLength == 5 && (mainRow & patternEnum.limitMask) == patternEnum.pattern
                        && (otherRow & patternEnum.limitMask) == 0) {
                    chessPatterns.add(new ChessPattern(patternEnum, i, rowLoopTimes));
                }
            }
        }

        return chessPatterns;
    }

    @Override
    public void printChessboard() {

        String[] board = new String[] {
                "┏┳┳┳┳┳┳┳┳┳┳┳┳┳┓",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┣╋╋╋╋╋╋╋╋╋╋╋╋╋┫",
                "┗┻┻┻┻┻┻┻┻┻┻┻┻┻┛"
        };

        System.out.println(" abcdefghijklmno");
        for (int i = 0; i < Constants.COLUMN_NUM; i++) {
            System.out.print((char) (i + 'A'));
            for (int j = 0; j < Constants.COLUMN_NUM; j++) {
                if ((rows[ChessTypeEnum.BLACK.index][i] & MASK_CODES[j]) != 0) {
                    System.out.print('●');
                }
                else if ((rows[ChessTypeEnum.WHITE.index][i] & MASK_CODES[j]) != 0) {
                    System.out.print('○');
                }
                else {
                    System.out.print(board[i].charAt(j));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        BitmapChessBoard board1 = new BitmapChessBoard();
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 13);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 12);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 11);
        board1.makeAMove(ChessTypeEnum.BLACK, 1, 10);
        board1.printChessboard();
        PatternMetaData patternMetaData = board1.getPatternMetaData();

        BitmapChessBoard board3 = new BitmapChessBoard();
        board3.makeAMove(ChessTypeEnum.BLACK, 1, 14);
        board3.makeAMove(ChessTypeEnum.BLACK, 1, 13);
        board3.makeAMove(ChessTypeEnum.BLACK, 1, 12);
        board3.makeAMove(ChessTypeEnum.BLACK, 1, 11);
        board3.printChessboard();
        PatternMetaData patternMetaData1 = board3.getPatternMetaData();

        BitmapChessBoard board2 = new BitmapChessBoard();
        board2.makeAMove(ChessTypeEnum.WHITE, 1, 1);
        board2.makeAMove(ChessTypeEnum.WHITE, 2, 2);
        board2.makeAMove(ChessTypeEnum.WHITE, 3, 3);
        board2.makeAMove(ChessTypeEnum.WHITE, 4, 4);
        board2.makeAMove(ChessTypeEnum.WHITE, 5, 5);
        board2.printChessboard();
        PatternMetaData patternMetaData2 = board2.getPatternMetaData();


        return;
    }
}
