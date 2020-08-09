package model;

import utils.ChessPatternEnum;

/**
 * 棋形信息
 */
public class ChessPattern {

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
    /**
     * 终止点x
     */
    private int endX;
    /**
     * 终止点y
     */
    private int endY;

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

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}