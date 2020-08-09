package model;

import java.util.List;

/**
 * 棋形元数据
 */
public class PatternMetaData {

    /**
     * 黑棋棋形
     */
    private List<ChessPattern> blackPatterns;

    /**
     * 白棋棋形
     */
    private List<ChessPattern> whitePatterns;

    public List<ChessPattern> getBlackPatterns() {
        return blackPatterns;
    }

    public void setBlackPatterns(List<ChessPattern> blackPatterns) {
        this.blackPatterns = blackPatterns;
    }

    public List<ChessPattern> getWhitePatterns() {
        return whitePatterns;
    }

    public void setWhitePatterns(List<ChessPattern> whitePatterns) {
        this.whitePatterns = whitePatterns;
    }
}
