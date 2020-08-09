package model;

import java.util.List;

/**
 * 棋形元数据
 */
public class PatternMetaData {

    /**
     * 黑棋行棋形
     */
    private List<ChessPattern> blackRowPatterns;
    /**
     * 黑棋列棋形
     */
    private List<ChessPattern> blackColumnPatterns;
    /**
     * 黑棋左下-右上棋形
     */
    private List<ChessPattern> blackLeftCrossPatterns;
    /**
     * 黑棋右上-左下棋形
     */
    private List<ChessPattern> blackRightCrossPatterns;

    /**
     * 白棋行棋形
     */
    private List<ChessPattern> whiteRowPatterns;
    /**
     * 白棋列棋形
     */
    private List<ChessPattern> whiteColumnPatterns;
    /**
     * 白棋左下-右上棋形
     */
    private List<ChessPattern> whiteLeftCrossPatterns;
    /**
     * 白棋右上-左下棋形
     */
    private List<ChessPattern> whiteRightCrossPatterns;

    public List<ChessPattern> getBlackRowPatterns() {
        return blackRowPatterns;
    }

    public void setBlackRowPatterns(List<ChessPattern> blackRowPatterns) {
        this.blackRowPatterns = blackRowPatterns;
    }

    public List<ChessPattern> getBlackColumnPatterns() {
        return blackColumnPatterns;
    }

    public void setBlackColumnPatterns(List<ChessPattern> blackColumnPatterns) {
        this.blackColumnPatterns = blackColumnPatterns;
    }

    public List<ChessPattern> getBlackLeftCrossPatterns() {
        return blackLeftCrossPatterns;
    }

    public void setBlackLeftCrossPatterns(List<ChessPattern> blackLeftCrossPatterns) {
        this.blackLeftCrossPatterns = blackLeftCrossPatterns;
    }

    public List<ChessPattern> getBlackRightCrossPatterns() {
        return blackRightCrossPatterns;
    }

    public void setBlackRightCrossPatterns(List<ChessPattern> blackRightCrossPatterns) {
        this.blackRightCrossPatterns = blackRightCrossPatterns;
    }

    public List<ChessPattern> getWhiteRowPatterns() {
        return whiteRowPatterns;
    }

    public void setWhiteRowPatterns(List<ChessPattern> whiteRowPatterns) {
        this.whiteRowPatterns = whiteRowPatterns;
    }

    public List<ChessPattern> getWhiteColumnPatterns() {
        return whiteColumnPatterns;
    }

    public void setWhiteColumnPatterns(List<ChessPattern> whiteColumnPatterns) {
        this.whiteColumnPatterns = whiteColumnPatterns;
    }

    public List<ChessPattern> getWhiteLeftCrossPatterns() {
        return whiteLeftCrossPatterns;
    }

    public void setWhiteLeftCrossPatterns(List<ChessPattern> whiteLeftCrossPatterns) {
        this.whiteLeftCrossPatterns = whiteLeftCrossPatterns;
    }

    public List<ChessPattern> getWhiteRightCrossPatterns() {
        return whiteRightCrossPatterns;
    }

    public void setWhiteRightCrossPatterns(List<ChessPattern> whiteRightCrossPatterns) {
        this.whiteRightCrossPatterns = whiteRightCrossPatterns;
    }
}
