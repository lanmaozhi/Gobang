package utils;

/**
 * 棋子类型
 */
public enum ChessTypeEnum {

    BLACK(0),
    WHITE(1);

    //棋子数组下标
    public int index;

    ChessTypeEnum(int index) {
        this.index = index;
    }
}
