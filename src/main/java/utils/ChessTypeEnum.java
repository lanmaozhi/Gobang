package utils;

/**
 * <pre>
 * 棋子类型
 * </pre>
 *
 * @author maozhi.lan@meicloud.com
 * @version 1.00.00
 * <p>
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
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
