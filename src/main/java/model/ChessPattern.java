package model;

import utils.ChessPatternEnum;

/**
 * 棋形信息
 */
public class ChessPattern {

    public ChessPattern(ChessPatternEnum chessPattern, int begX, int begY) {
        this.chessPattern = chessPattern;
        this.begX = begX;
        this.begY = begY;
    }

    /**
     * 棋形
     */
    private ChessPatternEnum chessPattern;
    /**
     * 起始点x
     */
    private int begX;
    /**
     * 起始点y
     */
    private int begY;

    public ChessPatternEnum getChessPattern() {
        return chessPattern;
    }

    public void setChessPattern(ChessPatternEnum chessPattern) {
        this.chessPattern = chessPattern;
    }

    public int getBegX() {
        return begX;
    }

    public void setBegX(int begX) {
        this.begX = begX;
    }

    public int getBegY() {
        return begY;
    }

    public void setBegY(int begY) {
        this.begY = begY;
    }
}
