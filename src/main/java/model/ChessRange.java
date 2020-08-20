package model;

/**
 * 棋盘范围
 */
public class ChessRange {

    public int begX, endX;
    public int begY, endY;

    public ChessRange() {
    }

    public ChessRange(int begX, int endX, int begY, int endY) {
        this.begX = begX;
        this.endX = endX;
        this.begY = begY;
        this.endY = endY;
    }
}
