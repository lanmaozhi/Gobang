package chessboard;

import utils.ChessTypeEnum;

/**
 * <pre>
 *
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
public interface Chessboard {

    /**
     * 随机走一步
     */
    void randomChess();

    /**
     * 是否已结束
     */
    int judgeIsComplete();

    /**
     * 走棋(x, y)
     * @param chessType 棋子类型
     * @param x 走子横坐标
     * @param y 走子纵坐标
     * @return 是否成功
     */
    boolean makeAMove(ChessTypeEnum chessType, int x, int y);
}
