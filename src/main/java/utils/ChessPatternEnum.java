package utils;

/**
 * 棋形
 */
public enum ChessPatternEnum {

    //黑子
    BLACK(0),
    //白子
    WHITE(1),
    //空白子(没有下子对棋格)
    BLANK(2);

    //棋子数组下标
    public int index;

    ChessPatternEnum(int index) {

        this.index = index;
    }
}
