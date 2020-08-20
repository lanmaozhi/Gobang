package utils;

/**
 * 棋子类型
 */
public enum ChessTypeEnum {

    //黑子
    BLACK(0),
    //白子
    WHITE(1);

    //棋子数组下标
    public int index;

    ChessTypeEnum(int index) {
        this.index = index;
    }

    public ChessTypeEnum getAnotherType() {

        return this == BLACK ? WHITE : BLACK;
    }
}
